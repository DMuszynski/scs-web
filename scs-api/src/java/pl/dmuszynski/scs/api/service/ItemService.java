package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.Item;

public interface ItemService {
    Item findItemByName(String name);
}
