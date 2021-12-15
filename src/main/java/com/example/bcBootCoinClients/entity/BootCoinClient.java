package com.example.bcBootCoinClients.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("bootcoinclient")
public class BootCoinClient {
	@Id
	private int id;
	private String fullName;
	private String dni;
	private double amountAvailable;
}
