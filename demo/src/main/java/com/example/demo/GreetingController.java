package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// add annotation to GreetingController to convert it to a Rest Controller component
@RestController
public class GreetingController {
	
	// final -constant
	// Static 
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	// define a GreetingComponent variable without initialization
	private GreetingComponent g;
	
	// inject/initialization GreetingComponent in the constructor
	@Autowired
	public GreetingController(GreetingComponent g) {
		this.g = g;
	}
	
	// test the greeting component
	@GetMapping("/testgreeting")
	public ResponseEntity<String> getMessage(){
		return ResponseEntity.ok(g.getMessage());
	}

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template,name));
	}
	
	@GetMapping("/add/{num1}/{num2}")
	public Integer addTwoNumber(@PathVariable("num1") Integer num1, @PathVariable("num2") Integer num2) {
	//public Integer addTwoNumber(@PathParm(value = "num1") Integer num1, @PathParm(value = "num2") Integer num2) {
		return num1 + num2;
	}
	
	@GetMapping("/sub/{num1},{num2}")
	public Integer subTwoNumber(@PathVariable("num1") Integer num1, @PathVariable("num2") Integer num2) {
	//public Integer addTwoNumber(@PathParm(value = "num1") Integer num1, @PathParm(value = "num2") Integer num2) {
		return num1 - num2;
	}
}
