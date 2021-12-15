package com.example.bcBootCoinClients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BootCoinClientDto {
	private int id;
	private String fullName;
	private String dni;
	private double amountAvailable;
}
