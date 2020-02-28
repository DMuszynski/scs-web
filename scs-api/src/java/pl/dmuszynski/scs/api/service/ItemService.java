package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item findItemById(Long id);
    Item findItemByName(String name);
}
