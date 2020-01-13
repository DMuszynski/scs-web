package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}
