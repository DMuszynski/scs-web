package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findAllByCategoryId(final Long categoryId);
}
