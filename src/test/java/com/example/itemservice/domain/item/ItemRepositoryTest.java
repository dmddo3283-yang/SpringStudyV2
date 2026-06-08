package com.example.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);

        Item save = itemRepository.save(item);

        Item byId = itemRepository.findById(item.getId());

        assertThat(byId).isEqualTo(save);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> result = itemRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        Item item = new Item("ItemA", 10000, 10);

        Item save = itemRepository.save(item);
        Long itemId = save.getId();

        Item updateItem = new Item("itemB", 20000, 30);

        itemRepository.update(itemId, updateItem);

        Item byId = itemRepository.findById(itemId);

        assertThat(byId.getItemName()).isEqualTo(updateItem.getItemName());
    }
}
