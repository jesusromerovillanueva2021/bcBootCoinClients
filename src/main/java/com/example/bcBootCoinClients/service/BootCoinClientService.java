package com.example.bcBootCoinClients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bcBootCoinClients.dto.BootCoinClientDto;
import com.example.bcBootCoinClients.kafkaConsumer.Consumer;
import com.example.bcBootCoinClients.repository.BootCoinClientRepository;
import com.example.bcBootCoinClients.utils.AppUtils;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BootCoinClientService {
	@Autowired
	private BootCoinClientRepository repository;
	
	public Flux<BootCoinClientDto> getBootCoinClients(){
		return repository.findAll()
				.map(AppUtils::entityToDto);
	}
	public Mono<BootCoinClientDto> getBootCoinClient(int id){
		return repository.findById(id)
				.map(AppUtils::entityToDto);
	}
	public Mono<BootCoinClientDto> saveBootCoinClient(@RequestBody Mono<BootCoinClientDto> bootMono){
		return bootMono.map(AppUtils::dtoToEntity)
				.flatMap(repository::save)
				.map(AppUtils::entityToDto);
	}
	
	public Mono<BootCoinClientDto> updateBootCoinClient(@RequestBody Mono<BootCoinClientDto> bootMono, int id){
		return repository.findById(id)
				.flatMap(p->bootMono.map(AppUtils::dtoToEntity)
						.doOnNext(e->e.setId(id)))
				.flatMap(repository::save)
				.map(AppUtils::entityToDto);
	}
	
	public void updateAmountPerShopping() {
		double amountShopping=(double)Consumer.messages.get(2)* (double)Consumer.messages.get(1);
		int id=(int)Consumer.messages.get(0);
		Mono<BootCoinClientDto> clientUpdate1=new Mono<BootCoinClientDto>() {
			
			@Override
			public void subscribe(CoreSubscriber<? super BootCoinClientDto> actual) {
				// TODO Auto-generated method stub
				
			}
		};
		repository.findById(id)
		.flatMap(p->clientUpdate1.map(AppUtils::dtoToEntity)
				.doOnNext(e->e.setId(id))
				.doOnNext(e->e.setAmountAvailable(e.getAmountAvailable()-amountShopping)))
		.flatMap(repository::save)
		.map(AppUtils::entityToDto);
	}
	
	public Mono<Void> deleteBootCoinClient(int id){
		return repository.deleteById(id);
	}
}
