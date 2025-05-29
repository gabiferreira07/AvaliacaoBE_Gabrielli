package com.forms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forms.entity.Cliente;
import com.forms.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Módulo Clientes", description = "API DE CONTROLE DE CLIENTES")
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

	private final ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	//Query Methods
	@Operation(summary = "Localiza cliente por nome")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cliente>> buscarClientePorNome(@PathVariable String nome) {
	    List<Cliente> clientes = clienteService.buscarClientePorNome(nome);
	    return ResponseEntity.ok(clientes);
	}
	
	@Operation(summary = "Localiza cliente por id")
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
	    Cliente cliente = clienteService.getClienteById(id);
	    if (cliente != null) {
	        return ResponseEntity.ok(cliente);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@Operation(summary = "Localiza todos clientes")
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes() {
	    List<Cliente> clientes = clienteService.getAllClientes();
	    return ResponseEntity.ok(clientes);
	}

	@Operation(summary = "Posta cliente")
	@PostMapping("/")
	public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid Cliente cliente) {
	    Cliente novoCliente = clienteService.salvarCliente(cliente);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
	}
	
	@Operation(summary = "Atualiza cliente por id")
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
	    Cliente updatedCliente = clienteService.updateCliente(id, cliente);
	    if (updatedCliente != null) {
	        return ResponseEntity.ok(updatedCliente);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@Operation(summary = "Deleta cliente por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id) {
	    boolean deleted = clienteService.deleteCliente(id);
	    if (deleted) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
}
