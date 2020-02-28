package pl.dmuszynski.scs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.scs.api.model.Item;
import pl.dmuszynski.scs.api.repository.ItemRepository;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findAll() {
        return this.itemRepository.findAll();
    }

    @Override
    public Item findItemById(Long id) {
        return this.itemRepository.findItemById(id);
    }

    @Override
    public Item findItemByName(String name) {
        return itemRepository.findItemByName(name);
    }
}
