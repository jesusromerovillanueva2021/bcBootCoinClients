package com.example.bcBootCoinClients.kafkaConsumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.bcBootCoinClients.dto.BootCoinClientDto;
import com.example.bcBootCoinClients.repository.BootCoinClientRepository;
import com.example.bcBootCoinClients.utils.AppUtils;

import reactor.core.publisher.Mono;

@Service
public class Consumer {
	public static List messages=new ArrayList();
	@Autowired
	private BootCoinClientRepository repository;
	
	@KafkaListener(topics = "topic-service2", groupId = "group_id")
	public void consumeMessage(String message) {
		messages.add(message);
		showMessages();
		messages.clear();
	}
	
	public Mono<BootCoinClientDto> updateAmountPerShopping(Mono<BootCoinClientDto> clientUpdate) {
		double amountShopping=(double)Consumer.messages.get(2)* (double)Consumer.messages.get(1);
		int id=(int)Consumer.messages.get(0);

		return repository.findById(id)
		.flatMap(p->clientUpdate.map(AppUtils::dtoToEntity)
				.doOnNext(e->e.setId(id))
				.doOnNext(e->e.setAmountAvailable(e.getAmountAvailable()-amountShopping)))
		.flatMap(repository::save)
		.map(AppUtils::entityToDto);	
	}
	
	public void showMessages() {
		System.out.println("---QUANTITY AND AMOUNT_BOOT_COIN---");
		messages.forEach(System.out::println);
	}
}
