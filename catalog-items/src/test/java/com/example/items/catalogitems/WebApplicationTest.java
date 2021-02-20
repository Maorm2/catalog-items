package com.example.items.catalogitems;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	private String BASE_PATH = "/items";

	private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void retrieveItemById() throws Exception {
		this.mockMvc.perform(get(BASE_PATH + "/item/{id}/retrieve", "1").contentType(contentType))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void retrieveAllItems() throws Exception {
		this.mockMvc.perform(get(BASE_PATH + "/retrieveitems")).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void addItem() throws Exception {
		String item = "{\r\n" + "    \"name\": \"item1\",\r\n" + "    \"amount\": 11.99,\r\n" + "    \"qty\": 1\r\n"
				+ "\r\n" + "}";

		this.mockMvc.perform(post(BASE_PATH + "/item/add").content(item).contentType(contentType))
				.andExpect(status().isCreated()).andReturn();
	}

	@Test
	public void depositQty() throws Exception {
		this.mockMvc.perform(patch(BASE_PATH + "/item/{id}/deposit/qty", "1").content("1").contentType(contentType))
				.andExpect(status().isOk());

	}

	@Test
	public void withdrawalQty() throws Exception {
		this.mockMvc.perform(patch(BASE_PATH + "/item/{id}/withdrawal/qty", "1").content("1").contentType(contentType))
				.andExpect(status().isOk());

	}

	@Test
	public void modifyItem() throws Exception {
		String itemModify = "{\r\n" + "    \"name\": \"itemModify\",\r\n" + "    \"amount\": 15.99,\r\n"
				+ "    \"qty\": 10\r\n" + "}";

		this.mockMvc.perform(patch(BASE_PATH + "/item/{id}/modify", "1").content(itemModify).contentType(contentType))
				.andExpect(status().isOk());

	}

	@Test
	public void deleteItem() throws Exception {
		this.mockMvc.perform(delete(BASE_PATH + "/item/{id}/delete", "1")).andExpect(status().isOk());

	}

	@Test
	public void badRequestWithdrawalQty() throws Exception {
		this.mockMvc.perform(patch(BASE_PATH + "/item/{id}/withdrawal/qty", "1").content("5").contentType(contentType))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void badRequestModifyItemName() throws Exception {

		String itemModify = "{\r\n" + "    \"name\": \"i\",\r\n" + "    \"amount\": 15.99,\r\n" + "    \"qty\": 10\r\n"
				+ "}";

		this.mockMvc.perform(
				patch(BASE_PATH + "/item/{id}/withdrawal/qty", "1").content(itemModify).contentType(contentType))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void badRequestModifyItemAmount() throws Exception {

		String itemModify = "{\r\n" + "    \"name\": \"itemModify\",\r\n" + "    \"amount\": 0,\r\n"
				+ "    \"qty\": 10\r\n" + "}";

		this.mockMvc.perform(
				patch(BASE_PATH + "/item/{id}/withdrawal/qty", "1").content(itemModify).contentType(contentType))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void badRequestModifyItemQty() throws Exception {

		String itemModify = "{\r\n" + "    \"name\": \"itemModify\",\r\n" + "    \"amount\": 15.99,\r\n"
				+ "    \"qty\": -1\r\n" + "}";

		this.mockMvc.perform(
				patch(BASE_PATH + "/item/{id}/withdrawal/qty", "1").content(itemModify).contentType(contentType))
				.andExpect(status().isBadRequest());
	}

}
