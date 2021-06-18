package com.example.furnitureshop.controller;

import com.example.furnitureshop.model.Category;
import com.example.furnitureshop.model.Page;
import com.example.furnitureshop.service.ICategoryService;
import com.example.furnitureshop.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping(value = "/")
    public String getAllCategory(Model model, @RequestParam(value = "search", required = false) String keySearch, @RequestParam(value = "page", required = false) Integer pageCurrent){
        String strParam = keySearch == null ? "?" : "?search="+keySearch+"&";
        if(pageCurrent == null){
            pageCurrent = 1;
        }
        List<Category> categories = null;
        int numberItem;
        Page page = null;
        if(keySearch != null){
            numberItem = iCategoryService.getCountItemSearch(keySearch);
            page = Paging.pageDivision(numberItem, pageCurrent);
            categories = iCategoryService.searchByName(keySearch, page.getIndexStart(), page.getNumberRecord());
        } else {
            numberItem = (int)iCategoryService.getLengthCategory();
            page = Paging.pageDivision(numberItem, pageCurrent);
            categories = iCategoryService.getByPage(page.getIndexStart(), page.getNumberRecord());
        }
        System.out.println(page.getPageCurrent());
        model.addAttribute("categories",categories);
        model.addAttribute("numberPage", page.getNumberPage());
        model.addAttribute("pageNow", page.getPageCurrent());
        model.addAttribute("start", page.getIndexStart());
        model.addAttribute("parameter", strParam);
        return "backend/category/index";
    }

    @GetMapping(value = "/create")
    public String create(Model model){
        model.addAttribute("category", new Category());
        return "backend/category/add";
    }

    @PostMapping(value = "/create")
    public String checkValidateAdd(Model model, RedirectAttributes redirectAttributes, @Validated @ModelAttribute("category") Category category, BindingResult error){
        if(error.hasErrors()){
            model.addAttribute("messageError", "Thêm mới thất bại!");
            return "backend/category/add";
        }
        if(!iCategoryService.checkNameAdd(category.getName())){
            model.addAttribute("messageError", "Thêm mới thất bại!");
            error.rejectValue("name","category","Tên thể loại này đã tồn tại!");
            return "backend/category/add";
        }
        if(null != store(category)) {
            redirectAttributes.addFlashAttribute("message", "Thêm mới thành công!");
            return "redirect:/admin/category/create";
        } else {
            model.addAttribute("messageError", "Thêm mới thất bại!");
        }
        return "backend/category/add";
    }

    @PostMapping(value = "/store")
    public Category store(Category category){
        return iCategoryService.storeCategory(category);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        Category category = iCategoryService.detailCategory(id);
        model.addAttribute("category", category);
        return "backend/category/edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String checkValidateEdit(Model model, RedirectAttributes redirectAttributes, @PathVariable int id, @Validated @ModelAttribute("category") Category category, BindingResult error){
        if(error.hasErrors()){
            model.addAttribute("messageError", "Sửa dữ liệu thất bại!");
            return "backend/category/edit";
        }
        if(!iCategoryService.checkNameEdit(category.getName(), id)){
            model.addAttribute("messageError", "Sửa dữ liệu thất bại!");
            error.rejectValue("name","category","Tên thể loại này đã tồn tại!");
            return "backend/category/edit";
        }
        if(null != update(id, category)) {
            redirectAttributes.addFlashAttribute("message", "Sửa dữ liệu thành công!");
            return "redirect:/admin/category/edit/"+id;
        } else {
            model.addAttribute("messageError", "Sửa dữ liệu thất bại!");
        }
        return "backend/category/edit";
    }

    @PostMapping(value = "/update/{id}")
    public Category update(int id, Category category){
        return iCategoryService.updateCategory(id, category);
    }

    @GetMapping(value = "/delete/{id}")
    public String remove( RedirectAttributes redirectAttributes, @PathVariable("id") int id){
        if(iCategoryService.removeCategory(id)){
            redirectAttributes.addFlashAttribute("message", "Xóa dữ liệu thành công!");
        } else {
            redirectAttributes.addFlashAttribute("messageError", "Xóa dữ liệu thất bại, Thể loại này đã được sử dụng không thể xóa!");
        }
        return "redirect:/admin/category/";
    }

}
