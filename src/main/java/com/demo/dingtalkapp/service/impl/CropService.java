package com.demo.dingtalkapp.service.impl;

import org.springframework.stereotype.Service;

import com.demo.dingtalkapp.service.inter.ICropService;

@Service
public class CropService implements ICropService {

	@Override
	public String getCropIdByCropCode(String com) {
		//假装查了数据库返回cropId
		return "dingnigefeiblablazaijian";
	}

}
