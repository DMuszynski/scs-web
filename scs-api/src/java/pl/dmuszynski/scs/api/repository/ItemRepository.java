package pl.dmuszynski.scs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.scs.api.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findItemByName(String name);
}
