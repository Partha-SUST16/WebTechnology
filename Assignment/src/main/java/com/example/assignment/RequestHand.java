package com.example.assignment;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestHand {

	@GetMapping(value = "/hi")
	public String hello() {
		return "hello";
	}
	@PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String save(@RequestBody Details details) {
		Store.list.add(details);
		return Store.list.get(0).getAddress()+" "+Store.list.get(0).getName()+" "+Store.list.get(0).getPhone();
	}
	@GetMapping(value = "/get")
	public Details get(@RequestParam(name = "id") int id) {
		/*
		 * if(Store.list.size()<id) return null;
		 */
		return Store.list.get(id);
	}
	@GetMapping(value = "/getSize")
	public int getSize() {
		return Store.list.size();
	}
	@GetMapping(value = "/clear")
	public String clear() {
		Store.list.clear();
		return "success";
	}
}
