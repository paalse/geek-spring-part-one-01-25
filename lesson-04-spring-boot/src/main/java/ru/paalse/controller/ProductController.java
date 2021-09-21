package ru.paalse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.paalse.persist.Product;
import ru.paalse.persist.ProductRepository;
import ru.paalse.service.ProductRepr;
import ru.paalse.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model, @RequestParam("productnameFilter") Optional<String> productnameFilter,
                           @RequestParam("priceminFilter") Optional<String> pricemineFilter,
                           @RequestParam("pricemaxFilter") Optional<String> pricemaxFilter) {
        logger.info("List page request");

        List<ProductRepr> products;
        if(productnameFilter.isPresent() && !productnameFilter.get().isEmpty()) {
            products = productService.findWithFilter(productnameFilter.get());
        } else {
            products = productService.findAll();
        }

        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("product", productService.findById(id));
        return ("product_form");
    }

    @PostMapping("/update")
    public String update(@Valid ProductRepr product, BindingResult result) {
        logger.info("Update end point requested");

        if(result.hasErrors()) {
            return ("product_form");
        }

        logger.info("Update product with id {}", product.getId());
        productService.save(product);

        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model){
        logger.info("Create new product requested");

        model.addAttribute("product", new ProductRepr());
        return ("product_form");
    }

    @DeleteMapping("/{id}")
    public String remove (@PathVariable("id") long id) {
        logger.info("Delete product with id {}", id);

        productService.delete(id);
        return ("redirect:/product");
    }
}
