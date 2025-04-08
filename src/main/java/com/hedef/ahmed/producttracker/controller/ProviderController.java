package com.hedef.ahmed.producttracker.controller;


import com.hedef.ahmed.producttracker.entity.Provider;
import com.hedef.ahmed.producttracker.entity.product.Clothing;
import com.hedef.ahmed.producttracker.entity.product.Electronics;
import com.hedef.ahmed.producttracker.entity.product.Product;
import com.hedef.ahmed.producttracker.repositories.ProviderRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("provider")
public class ProviderController {

    ProviderRepo providerRepo;
    public ProviderController(ProviderRepo providerRepo){
        this.providerRepo=providerRepo;

    }

    @GetMapping(path = "ping")
    public String test(){
        return "index";
    }


    @GetMapping(path = "/ListProviders")
    public String ListProviders(Model model,
                               @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                               @RequestParam(name = "size", defaultValue = "5") int size,
                               @RequestParam(name = "key", defaultValue = "") String key) {
        Page<Provider> providers = providerRepo.findProviderByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrVilleContainingIgnoreCase(key,key,key, PageRequest.of(pageNumber, size));
        model.addAttribute("providers", providers);
        model.addAttribute("pages", new int[providers.getTotalPages()]);
        model.addAttribute("key", key);
        model.addAttribute("currentPage", pageNumber);
        return "ProviderViews/ListProviders";
    }

    @GetMapping(path = "/addProviderForm")
    public String addProviderForm(Model model,@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
        model.addAttribute("provider", new Provider());
        model.addAttribute("pageNumber", pageNumber);
        return "ProviderViews/AddProviderForm";
    }

    @PostMapping(path ="/addProvider" )
    public String addProvider(@ModelAttribute Provider provider, @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
        providerRepo.save(provider);
        return "redirect:/provider/ListProviders?pageNumber=" + pageNumber;
    }

    @GetMapping(path="/deleteProvider")
    public String deleteProvider(@ModelAttribute("id")Long id,
                                @RequestParam(name="pageNumber") int pageNumber,
                                @RequestParam(name="productNumber") int productNumber,
                                 @RequestParam(name = "key", defaultValue = "") String key) {
        providerRepo.deleteById(id);
        productNumber--;

        if(productNumber==0 && pageNumber>0) {
            pageNumber-=1;
        }else if (productNumber == 0) {
            pageNumber=0;
        }
        return "redirect:/provider/ListProviders?pageNumber="+pageNumber;
    }
    @GetMapping(path ="updateProvider")
    public String updateProvider(@RequestParam long  id, @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber, Model model) {
        Provider provider = providerRepo.findById(id).orElse(new Provider());
        model.addAttribute("provider", provider);
        model.addAttribute("pageNumber", pageNumber);
        return "/ProviderViews/updateProviderForm";
    }




}
