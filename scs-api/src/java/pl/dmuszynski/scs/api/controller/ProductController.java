package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.scs.api.model.Product;
import pl.dmuszynski.scs.api.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/categories/products")
    public List<Product> findAll() {
        return this.productService.findAll();
    }

    @GetMapping(value = "/categories/{id}/products")
    public List<Product> findAllByCategoryId(@PathVariable(value = "id") Long id) {
        return this.productService.findAllByCategoryId(id);
    }
}
