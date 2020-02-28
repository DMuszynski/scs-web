package pl.dmuszynski.scs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dmuszynski.scs.api.model.Item;
import pl.dmuszynski.scs.api.service.ItemService;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(final ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/items")
    public List<Item> findAll() {
        return itemService.findAll();
    }
}
