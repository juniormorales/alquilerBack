package com.back.alquiler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.alquiler.service.ImagenPropiedadService;

@RestController
@RequestMapping("/api/img-prop")
public class ImagenPropiedadController {
	
	@Autowired
	ImagenPropiedadService service;
	
}
