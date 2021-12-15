package com.example.bcBootCoinClients.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.bcBootCoinClients.entity.BootCoinClient;

public interface BootCoinClientRepository extends ReactiveCrudRepository<BootCoinClient, Integer> {

}
