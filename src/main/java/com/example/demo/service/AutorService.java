package com.example.demo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Autor;
import com.example.demo.model.AutorDTO;
import com.example.demo.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	AutorRepository repository;

	@Autowired
	ImageService service;

	@Autowired
	BCryptPasswordEncoder bCrypt;

	public void create(Autor autor, MultipartFile file) throws IOException {
		autor.setSenha(bCrypt.encode(autor.getSenha()));
		Autor savedAutor = repository.save(autor);
		service.create(savedAutor, file);
	}

	public Autor getAutor(String email) {
		Optional<Autor> optional = repository.findByEmail(email);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public AutorDTO getAutorDTO(String email) {
		Autor autor = this.getAutor(email);
		AutorDTO autorDTO = new AutorDTO();
		autorDTO.setNome(autor.getNome());
		autorDTO.setId(autor.getId());
		autorDTO.setUrl(service.createUrl(autor.getId()));
		return autorDTO;
	}
}
