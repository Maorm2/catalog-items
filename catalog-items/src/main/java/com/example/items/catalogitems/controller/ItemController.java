package com.example.items.catalogitems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.items.catalogitems.item.Item;
import com.example.items.catalogitems.service.ItemDaoService;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemDaoService service;

	@GetMapping("/retrieveitems")
	public List<Item> retrieveAllItems() {
		return service.findAll();
	}

	@PostMapping("/item/add")
	public ResponseEntity<Object> createItem(@Validated @RequestBody Item item) {
		return service.saveItem(item);
	}

	@PatchMapping("/item/{id}/deposit/qty")
	public void depositQty(@PathVariable int id, @RequestBody int qty) {
		service.deposit(id, qty);
	}

	@PatchMapping("/item/{id}/withdrawal/qty")
	public void withdrawalQty(@PathVariable int id, @RequestBody int qty) {
		service.withdrawal(id, qty);
	}

	@PatchMapping("/item/{id}/modify")
	public void modifyItem(@PathVariable int id, @RequestBody Item item) {
		service.modify(id, item);
	}

	@GetMapping("/item/{id}/retrieve")
	public Optional<Item> retrieveItem(@PathVariable int id) {
		return service.findOne(id);
	}

	@DeleteMapping("/item/{id}/delete")
	public void deleteItem(@PathVariable int id) {
		service.deleteById(id);
	}
}
