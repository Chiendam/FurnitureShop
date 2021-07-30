package com.example.furnitureshop.controller;

import com.example.furnitureshop.model.Category;
import com.example.furnitureshop.model.Page;
import com.example.furnitureshop.model.User;
import com.example.furnitureshop.service.IUserService;
import com.example.furnitureshop.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping(value = "/test")
    public String get(){
        return "backend/dashboard";
    }

    @GetMapping(value = "/")
    public String getAllUser(Model model, @RequestParam(value = "search", required = false) String keySearch, @RequestParam(value = "page", required = false) Integer pageCurrent){
        String strParam = keySearch == null ? "?" : "?search="+keySearch+"&";
        if(pageCurrent == null){
            pageCurrent = 1;
        }
        List<User> users = null;
        int numberItem;
        Page page = null;
        if(keySearch != null){
            numberItem = iUserService.getCountItemSearch(keySearch);
            page = Paging.pageDivision(numberItem, pageCurrent);
            users = iUserService.searchByName(keySearch, page.getIndexStart(), page.getNumberRecord());
        } else {
            numberItem = (int)iUserService.getLengthUser();
            page = Paging.pageDivision(numberItem, pageCurrent);
            users = iUserService.getByPage(page.getIndexStart(), page.getNumberRecord());
        }
        System.out.println(page.getPageCurrent());
        model.addAttribute("numberPage", page.getNumberPage());
        model.addAttribute("pageNow", page.getPageCurrent());
        model.addAttribute("start", page.getIndexStart());
        model.addAttribute("parameter", strParam);
        model.addAttribute("users",users);
        return "backend/user/index";
    }

    @GetMapping(value = "/page/{pageNumber}")
    public String getByPage(Model model, @PathVariable int pageNumber){
        int numberItem = (int)iUserService.getLengthUser();
        Page page = Paging.pageDivision(numberItem, pageNumber);
        List<User> users = iUserService.getByPage(page.getIndexStart(), page.getNumberRecord());
        model.addAttribute("users",users);
        model.addAttribute("pageNow", page.getPageCurrent());
        model.addAttribute("numberPage", page.getNumberPage());
        model.addAttribute("start", page.getIndexStart());
        return "backend/user/index";
    }


    @PostMapping(value = "/create")
    public String save(Model model, RedirectAttributes redirectAttributes,  @Validated @ModelAttribute("user") User user, BindingResult error){
        if(error.hasErrors()){
            model.addAttribute("messageError", "Thêm mới thất bại!");
            return "backend/user/add";
        }
        if(!iUserService.checkEmailAdd(user.getEmail())){
            model.addAttribute("messageError", "Thêm mới thất bại!");
            error.rejectValue("email","user","Email này đã được đăng ký cho tài khoản khác!");
            return "backend/user/add";
        }
        if(null != store(user)) {
            redirectAttributes.addFlashAttribute("message", "Thêm mới thành công!");
            return "redirect:/admin/user/create";
        } else {
            model.addAttribute("messageError", "Thêm mới thất bại!");
        }
        return "backend/user/add";
    }

//    @PostMapping(value = "/store")
    public User store(User user){
        return iUserService.storeUser(user);
    }

    @GetMapping(value = "/create")
    public String create(Model model){

        model.addAttribute("user",new User());
        return "backend/user/add";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        User user = iUserService.detailUser(id);
        model.addAttribute("user", user);
        model.addAttribute("roleId", user.getRole());
        return "backend/user/edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String checkValidateEdit(Model model, RedirectAttributes redirectAttributes, @PathVariable int id, @Validated @ModelAttribute("user") User user, BindingResult error){
        System.out.println(user.getRole()+"role");
        if(error.hasErrors()){
            model.addAttribute("messageError", "Sửa dữ liệu thất bại!");
            return "backend/user/edit";
        }
        if(!iUserService.checkEmailEdit(user.getEmail(), id)){
            model.addAttribute("messageError", "Sửa dữ liệu thất bại!");
            error.rejectValue("email","user","Email này đã được đăng ký cho tài khoản khác!");
            return "backend/user/edit";
        }
        if(null != update(id, user)) {
            redirectAttributes.addFlashAttribute("message", "Sửa dữ liệu thành công!");
            return "redirect:/admin/user/edit/"+id;
        } else {
            model.addAttribute("messageError", "Sửa dữ liệu thất bại!");
        }
        return "backend/user/edit";
    }

    @PostMapping(value = "/update/{id}")
    public User update(int id, User user){
        return iUserService.updateUser(id,user);
    }

    @GetMapping(value = "/delete/{id}")
    public String remove( RedirectAttributes redirectAttributes, @PathVariable("id") int id){
        if(iUserService.removeUser(id)){
            redirectAttributes.addFlashAttribute("message", "Xóa dữ liệu thành công!");
        } else {
            redirectAttributes.addFlashAttribute("messageError", "Xóa dữ liệu thất bại!");
        }
        return "redirect:/admin/user/";
    }

}
