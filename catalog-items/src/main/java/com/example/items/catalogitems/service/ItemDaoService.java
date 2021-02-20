package com.example.items.catalogitems.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.items.catalogitems.exception.BadRequestException;
import com.example.items.catalogitems.exception.ItemNotFoundException;
import com.example.items.catalogitems.item.Item;
import com.example.items.catalogitems.model.ItemRepository;

@Component
public class ItemDaoService {

	@Autowired
	private ItemRepository repository;

	public List<Item> findAll() {
		return repository.findAll();
	}

	public ResponseEntity<Object> saveItem(Item item) {
		if (item.getInventoryCode() == null)
			item.setInventoryCode(UUID.randomUUID().toString());

		Item savedItem = repository.save(item);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedItem.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	public void deposit(int id, int qty) {
		Optional<Item> item = repository.findById(id);

		if (item.isPresent()) {
			Item currentItem = item.get();
			int currentQty = currentItem.getQty();
			currentItem.setQty(currentQty + qty);
			repository.save(currentItem);
		}
	}

	public void withdrawal(int id, int qty) {
		Optional<Item> item = repository.findById(id);

		if (item.isPresent()) {
			Item currentItem = item.get();
			int currentQty = currentItem.getQty();

			if (currentQty - qty < 0)
				throw new BadRequestException("There is only " + currentQty + " items available in stock");

			currentItem.setQty(currentQty - qty);
			repository.save(currentItem);
		}
	}

	public void modify(int id, Item item) {
		Optional<Item> selectediItem = repository.findById(id);

		if (selectediItem.isPresent()) {
			selectediItem.get().setName(item.getName());
			selectediItem.get().setAmount(item.getAmount());
			selectediItem.get().setQty(item.getQty());

			repository.save(selectediItem.get());
		}

	}

	public Optional<Item> findOne(int id) {
		Optional<Item> item = repository.findById(id);

		if (!item.isPresent())
			throw new ItemNotFoundException("id not found: " + id);

		return item;
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
