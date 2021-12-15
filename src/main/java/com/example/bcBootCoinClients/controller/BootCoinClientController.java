package com.example.bcBootCoinClients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bcBootCoinClients.dto.BootCoinClientDto;
import com.example.bcBootCoinClients.service.BootCoinClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("bootcoinclients")
public class BootCoinClientController {
	@Autowired
	private BootCoinClientService service;
	
	@GetMapping
	public Flux<BootCoinClientDto> getBootCoinClients(){
		return service.getBootCoinClients();
	}
	
	@GetMapping("/{id}")
	public Mono<BootCoinClientDto> getBootCoinClient(@PathVariable int id){
		return service.getBootCoinClient(id);
	}
	
	@PostMapping
	public Mono<BootCoinClientDto> saveBootCoinClient(@RequestBody Mono<BootCoinClientDto> booMono){
		return service.saveBootCoinClient(booMono);
	}
	
	@PutMapping("/update/{id}")
	public Mono<BootCoinClientDto> updateBootCoinClient(@RequestBody Mono<BootCoinClientDto> booMono, @PathVariable int id){
		return service.updateBootCoinClient(booMono, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void> deleteBootCoinClient(@PathVariable int id){
		return service.deleteBootCoinClient(id);
	}
}
