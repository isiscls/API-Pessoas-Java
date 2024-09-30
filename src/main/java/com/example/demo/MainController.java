package com.example.demo;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping(path="/pessoas")
	public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDto pessoaDto){
		Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(pessoaDto, pessoa);
		System.out.println(pessoaDto.cpf());
		Pessoa savedPessoa = pessoaRepository.save(pessoa);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
	}

	@GetMapping("/pessoas/{id}")
	public ResponseEntity<Object> getPessoaById(@PathVariable Integer id){
		Optional<Pessoa> foundPessoa = pessoaRepository.findById(id);
		if(foundPessoa.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa foi de base!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(foundPessoa.get());
	}
}



