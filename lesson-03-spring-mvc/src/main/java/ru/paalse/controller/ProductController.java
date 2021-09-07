package ru.paalse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.paalse.persist.Product;
import ru.paalse.persist.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        logger.info("List page request.");

        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("product", productRepository.findById(id));
        return ("product_form");
    }

    @PostMapping("/update")
    public String update(Product product) {
        logger.info("Update end point requested");

        if (product.getId() != null) {
            logger.info("Update product with id {}", product.getId());
            productRepository.update(product);
        } else {
            logger.info("Create new product with id {}", product.getId());
            productRepository.insert(product);
        }
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model){

        model.addAttribute("product", new Product());
        return ("product_form");
    }

    @GetMapping("/{id}/delete")
    public String remove (@PathVariable("id") long id) {
        logger.info("Delete product with id {}", id);

        productRepository.delete(id);

        return ("redirect:/product");
    }
}
