package com.example.furnitureshop.controller;

import com.example.furnitureshop.model.Category;
import com.example.furnitureshop.model.Page;
import com.example.furnitureshop.model.Product;
import com.example.furnitureshop.service.ICategoryService;
import com.example.furnitureshop.service.IProductService;
import com.example.furnitureshop.util.Paging;
import com.example.furnitureshop.util.PhotoUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping(value = "/")
    public String getAllProduct(Model model, @RequestParam(value = "search", required = false) String keySearch, @RequestParam(value = "page", required = false) Integer pageCurrent){
        String strParam = keySearch == null ? "?" : "?search="+keySearch+"&";
        if(pageCurrent == null){
            pageCurrent = 1;
        }
        List<Product> products = null;
        int numberItem;
        Page page = null;
        if(keySearch != null){
            numberItem = iProductService.getCountItemSearch(keySearch);
            page = Paging.pageDivision(numberItem, pageCurrent);
            products = iProductService.searchByName(keySearch, page.getIndexStart(), page.getNumberRecord());
        } else {
            numberItem = iProductService.getCountItem();
            page = Paging.pageDivision(numberItem, pageCurrent);
            products = iProductService.getByPage(page.getIndexStart(), page.getNumberRecord());
        }
        List<Category> categories = iCategoryService.getAllCategory();
        model.addAttribute("categories",categories);
        model.addAttribute("products",products);
        model.addAttribute("numberPage", page.getNumberPage());
        model.addAttribute("pageNow", page.getPageCurrent());
        model.addAttribute("start", page.getIndexStart());
        model.addAttribute("parameter", strParam);
        return "backend/product/index";
    }

    @GetMapping(value = "/create")
    public String create(Model model){
        List<Category> categories = iCategoryService.getAllCategory();
        model.addAttribute("categories",categories);
        model.addAttribute("product", new Product());
        return "backend/product/add";
    }

    @PostMapping(value = "/create")
    public String checkValidateAdd(Model model, RedirectAttributes redirectAttributes, @Validated @ModelAttribute("product") Product product, @RequestParam("image") MultipartFile multipartFile, BindingResult error) throws IOException {
        if(error.hasErrors()){
            model.addAttribute("messageError", "Thêm mới thất bại!");
            List<Category> categories = iCategoryService.getAllCategory();
            model.addAttribute("categories",categories);
            return "backend/product/add";
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        PhotoUploadUtil.savePhoto(fileName, multipartFile);
        product.setImgPoster(fileName);
        if(null != store(product)){
            redirectAttributes.addFlashAttribute("message", "Thêm mới thành công!");
            return "redirect:/admin/product/create";
        } else {
            model.addAttribute("messageError", "Thêm mới thất bại!");
        }
        return "backend/product/add";
    }

    public Product store(Product product){
        return iProductService.storeProduct(product);
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        Product product = iProductService.detailProduct(id);
        List<Category> categories = iCategoryService.getAllCategory();
        model.addAttribute("categories",categories);
        model.addAttribute("product", product);
        model.addAttribute("imgPoster", product.getImgPoster());
        return "backend/product/edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String checkValidateEdit(Model model, RedirectAttributes redirectAttributes, @PathVariable int id, @Validated @ModelAttribute("product") Product product, @RequestParam(value = "image", required = false) MultipartFile multipartFile, BindingResult error) throws IOException {
        if(error.hasErrors()){
            model.addAttribute("messageError", "Thêm mới thất bại!");
            List<Category> categories = iCategoryService.getAllCategory();
            model.addAttribute("categories",categories);
            model.addAttribute("imgPoster", product.getImgPoster());
            return "backend/product/edit";
        }
        if(null != multipartFile){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            if(fileName.equals("")){
                Product productCurrent = iProductService.detailProduct(id);
                product.setImgPoster(productCurrent.getImgPoster());
            } else {
                PhotoUploadUtil.savePhoto(fileName, multipartFile);
                product.setImgPoster(fileName);
            }
        }
        if(null != update(id, product)) {
            redirectAttributes.addFlashAttribute("message", "Sửa dữ liệu thành công!");
            redirectAttributes.addFlashAttribute("imgPoster", product.getImgPoster());
            return "redirect:/admin/product/edit/"+id;
        } else {
            model.addAttribute("messageError", "Sửa dữ liệu thất bại!");
        }
        return "backend/product/edit";
    }

    @PostMapping(value = "/update/{id}")
    public Product update(int id, Product product){
        return iProductService.updateProduct(id, product);
    }

    @GetMapping(value = "/delete/{id}")
    public String remove( RedirectAttributes redirectAttributes, @PathVariable("id") int id){
        if(iProductService.removeProduct(id)){
            redirectAttributes.addFlashAttribute("message", "Xóa dữ liệu thành công!");
        } else {
            redirectAttributes.addFlashAttribute("messageError", "Xóa dữ liệu thất bại!");
        }
        return "redirect:/admin/product/";
    }

}
