package com.example.items.catalogitems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.items.catalogitems.controller.ItemController;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private ItemController controller;

	@Test
	void contextLoads() {

		assertThat(controller).isNotNull();
	}

}
