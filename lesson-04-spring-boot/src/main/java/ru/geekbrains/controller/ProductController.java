package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.service.ProductRepr;
import ru.geekbrains.service.ProductService;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("productnameFilter") Optional<String> productnameFilter,
                           @RequestParam("pricefromFilter") Optional<BigDecimal> pricefromFilter,
                           @RequestParam("pricetoFilter") Optional<BigDecimal> pricetoFilter,
                           @RequestParam("page") Optional<Integer> page,         // Для пагенации, страница
                           @RequestParam("size") Optional<Integer> size) {        // Для пагенации, размер
        logger.info("List page requested.");

        List<ProductRepr> products;
        products = productService.findWithFilter(productnameFilter.filter(s->!s.isEmpty()).orElse(null),
                pricefromFilter.orElse(null), pricetoFilter.orElse(null));

        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("product", productService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @PostMapping("/update")
    public String update(@Valid ProductRepr product, BindingResult result) {
        logger.info("Update endpoint requested.");

        // Проводим валидацию
        if(result.hasErrors()) {
            return  "product_form";
        }

        logger.info("Updating product with id {}", product.getId());
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new product request.");

        model.addAttribute("product", new ProductRepr());
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Delete product with id {}", id);

        productService.delete(id);
        return "redirect:/product";
    }
}