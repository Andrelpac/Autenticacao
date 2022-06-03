package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Autor;
import com.example.demo.model.AutorDTO;
import com.example.demo.model.Image;
import com.example.demo.security.JWTUtil;
import com.example.demo.service.AutorService;
import com.example.demo.service.ImageService;

@RestController
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	AutorService service;

	@Autowired
	ImageService imageService;

	@Autowired
	JWTUtil jwtUtil;

	@GetMapping("/ola")
	public String digaOla(@RequestHeader(required = true, name = "Authorization") String token) throws Exception {
		if (!jwtUtil.getCreditials(token).equals("andre")) {
			throw new Exception();
		}
		return "olá";
	}

	@PostMapping
	public void create(@RequestPart Autor autor, @RequestParam MultipartFile file) throws IOException {
		service.create(autor, file);
	}

	@GetMapping
	public AutorDTO getAutorFindByEmail(@RequestParam String email) {
		return service.getAutorDTO(email);
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
		Image imagem = imageService.getImage(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", imagem.getMimeType());
		headers.add("content-lenght", String.valueOf(imagem.getData().length));
		return new ResponseEntity<>(imagem.getData(), headers, HttpStatus.OK);
	}
}
