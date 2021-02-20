package com.example.items.catalogitems.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.items.catalogitems.item.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
