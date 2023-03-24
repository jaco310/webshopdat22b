package dk.kea.webshopdat22b.controller;

import dk.kea.webshopdat22b.model.Product;
import dk.kea.webshopdat22b.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    ProductRepository productRepository;

    public HomeController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products", productRepository.getAll());
        return "index";
    }

    //fra anchor i index
    @GetMapping("/create")
    public String showCreate(){
        //vis create-siden
        return "create";
    }

    //fra form action
    @PostMapping("/create")
    public String createProduct(@RequestParam("product-name") String newName, @RequestParam("product-price") double newPrice){
        //lave et nyt Product
        Product newProduct = new Product();
        newProduct.setPrice(newPrice);
        newProduct.setName(newName);

        //gem nyt produkt
        productRepository.addProduct(newProduct);

        //tilbage til produktlisten
        return "redirect:/";
    }
    
}
