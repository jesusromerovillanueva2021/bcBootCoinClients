package com.example.bcBootCoinClients.utils;

import org.springframework.beans.BeanUtils;

import com.example.bcBootCoinClients.dto.BootCoinClientDto;
import com.example.bcBootCoinClients.entity.BootCoinClient;

public class AppUtils {
	public static BootCoinClientDto entityToDto(BootCoinClient bootCoinClient) {
		BootCoinClientDto bootCoinClientDto=new BootCoinClientDto();
		BeanUtils.copyProperties(bootCoinClient, bootCoinClientDto);
		return bootCoinClientDto;
	}
	
	public static BootCoinClient dtoToEntity(BootCoinClientDto bootCoinClientDto) {
		BootCoinClient bootCoinClient=new BootCoinClient();
		BeanUtils.copyProperties(bootCoinClientDto, bootCoinClient);
		return bootCoinClient;
	}
}
