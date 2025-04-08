package com.hedef.ahmed.producttracker.controller;


import com.hedef.ahmed.producttracker.entity.Provider;
import com.hedef.ahmed.producttracker.entity.product.Clothing;
import com.hedef.ahmed.producttracker.entity.product.Electronics;
import com.hedef.ahmed.producttracker.entity.product.Product;
import com.hedef.ahmed.producttracker.repositories.ProductRepos.ClothingRepo;
import com.hedef.ahmed.producttracker.repositories.ProductRepos.ElectronicsRepo;
import com.hedef.ahmed.producttracker.repositories.ProductRepos.ProductRepo;
import com.hedef.ahmed.producttracker.repositories.ProviderRepo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("products")
@Slf4j
public class ProductController {

    ProductRepo productRepo;
    ElectronicsRepo electronicsRepo;
    ClothingRepo clothingRepo;
    ProviderRepo providerRepo;

    public ProductController(ProductRepo productRepo, ElectronicsRepo electronicsRepo, ClothingRepo clothingRepo,ProviderRepo providerRepo) {
        this.electronicsRepo = electronicsRepo;
        this.productRepo = productRepo;
        this.clothingRepo = clothingRepo;
        this.providerRepo = providerRepo;
    }

    @GetMapping(path = "/ping")
    public String ping() {
        return "ProductViews/ElectronicsForm";
    }

    @GetMapping(path = "/user/ListProducts")
    public String ListProducts(Model model,
                               @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                               @RequestParam(name = "size", defaultValue = "5") int size,
                               @RequestParam(name = "key", defaultValue = "") String key) {
        Page<Product> products = productRepo.findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(key,key, PageRequest.of(pageNumber, size));
        System.out.println(products.getTotalElements());

        model.addAttribute("products", products);
        model.addAttribute("pages", new int[products.getTotalPages()]);
        model.addAttribute("key", key);
        model.addAttribute("currentPage", pageNumber);
        System.out.println(products.getTotalElements());
        return "AllProducts";
    }

    @GetMapping(path = "electronics")
    public String ListElectronics(Model model,
                                  @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                  @RequestParam(name = "size", defaultValue = "5") int size,
                                  @RequestParam(name = "key", defaultValue = "") String key) {
        Page<Electronics> products = electronicsRepo.findProductByNameContainsIgnoreCase(key, PageRequest.of(pageNumber, size));
        model.addAttribute("products", products);
        model.addAttribute("pages", new int[products.getTotalPages()]);
        model.addAttribute("key", key);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("type", "Electronics");
        return "ListPerCategory";
    }

    @GetMapping(path = "/user/clothing")
    public String ListClothings(Model model,
                                @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                @RequestParam(name = "size", defaultValue = "5") int size,
                                @RequestParam(name = "key", defaultValue = "") String key) {
        Page<Clothing> products = clothingRepo.findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(key,key, PageRequest.of(pageNumber, size));
        model.addAttribute("products", products);
        model.addAttribute("pages", new int[products.getTotalPages()]);
        model.addAttribute("key", key);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("type", "Clothing");
        return "ProductViews/ListClothing";
    }

    @GetMapping(path = "/admin/addProductForm")
    public String addProductForm(Model model,
                                 @RequestParam(name="pageNumber") int pageNumber,
                                 @RequestParam(name="key")String key) {
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("product", new Product());
        model.addAttribute("key", key);
        List<Provider> providers=providerRepo.findAll();
        model.addAttribute("providers", providers);

        return "AddProductForm";
    }

    @PostMapping(path = "/admin/addProduct")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
                             @RequestParam(name="pageNumber") int pageNumber,
                             @RequestParam(name="key")String key,
                             Model model) {
//        log.info("updating  product"+product.getId());
        if (result.hasErrors()) {
            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("key", key);
            model.addAttribute("providers", providerRepo.findAll());
            return "AddProductForm"; // Show form again with errors
        }
        productRepo.save(product);
        return "redirect:/products/user/ListProducts?pageNumber="+pageNumber+"&key="+key;
    }
    //update product
    @GetMapping(path="/admin/updateProductFrom")
    public String updateProductForm(Model model,
                                    @RequestParam(name="id")long id,
                                    @RequestParam(name="pageNumber" ,defaultValue = "0")int pageNumber,
                                    @RequestParam(name="key")String key){
        Product product=productRepo.findById(id).orElse(new Product());
        model.addAttribute("product",product);
        model.addAttribute("key",key);
        model.addAttribute("pageNumber",pageNumber);
        List<Provider> providers=providerRepo.findAll();
        model.addAttribute("providers", providers);

        return "updateProductForm";
    }
//    @GetMapping(path="updateProduct")
//    public String updateProduct(@ModelAttribute("product") Product product,@RequestParam(name="pageNumber") int pageNumber){
//
//    }

    //clothing
    @GetMapping(path = "/admin/addClothingForm")
    public String addClothingForm(Model model,
                                 @RequestParam(name="pageNumber" ,defaultValue = "0") int pageNumber) {
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("clothing", new Clothing());
        List<Provider> providers=providerRepo.findAll();
        model.addAttribute("providers", providers);
        return "ProductViews/AddClothingForm";
    }
    @PostMapping(path = "/admin/addClothing")
    public String addClothing(@Valid @ModelAttribute("clothing") Clothing product,BindingResult result,
                              @RequestParam(name="pageNumber") int pageNumber,
                              @RequestParam(name="key" ,defaultValue = "") String key,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("key", key);
            model.addAttribute("providers", providerRepo.findAll());
            return "ProductViews/AddClothingForm";}
        clothingRepo.save(product);
        return "redirect:/products/user/clothing?pageNumber="+pageNumber;
    }

    @GetMapping(path="/admin/deleteProduct")
    public String deleteProduct(@ModelAttribute("id")Long id,
                                @RequestParam(name="pageNumber") int pageNumber,
                                @RequestParam(name="productNumber") int productNumber,
                                @RequestParam(name="key")String key) {
            productRepo.deleteById(id);
            productNumber--;

            if(productNumber==0 && pageNumber>0) {
                pageNumber-=1;
            }else if (productNumber == 0) {
                pageNumber=0;
            }
            return "redirect:/products/user/ListProducts?pageNumber="+pageNumber+"&key="+key;
    }
    @GetMapping(path="/admin/deleteCloth")
    public String deleteCloth(@ModelAttribute("id")Long id,
                                @RequestParam(name="pageNumber") int pageNumber,
                                @RequestParam(name="productNumber") int productNumber) {
            productRepo.deleteById(id);
            productNumber--;

            if(productNumber==0 && pageNumber>0) {
                pageNumber-=1;
            }else if (productNumber == 0) {
                pageNumber=0;
            }
            return "redirect:/products/user/clothing?pageNumber="+pageNumber;
    }

    @GetMapping(path="/admin/updateClothingFrom")
    public String updateClothingForm(Model model,
                                    @RequestParam(name="id")long id,
                                    @RequestParam(name="pageNumber" ,defaultValue = "0")int pageNumber){
        Clothing product=clothingRepo.findById(id).orElse(new Clothing());
        model.addAttribute("product",product);
        model.addAttribute("pageNumber",pageNumber);
        List<Provider> providers=providerRepo.findAll();
        model.addAttribute("providers", providers);

        return "ProductViews/updateClothingForm";
    }

}
