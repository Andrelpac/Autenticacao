package com.example.demo.service;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Autor;
import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository repository;

	@Transactional
	public Image create(Autor autor, MultipartFile file) throws IOException {
		Image imagem = new Image();
		imagem.setMimeType(file.getContentType());
		imagem.setName(file.getName());
		imagem.setData(file.getBytes());
		imagem.setAutor(autor);
		return repository.save(imagem);
	}

	public String createUrl(Integer id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/autor/{id}/image").buildAndExpand(id)
				.toUri();
		return uri.toString();
	}

	@Transactional
	public Image getImage(Integer id) {
		Optional<Image> optional = repository.findByAutorId(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}
}
