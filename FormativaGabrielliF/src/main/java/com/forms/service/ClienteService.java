package com.forms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forms.entity.Cliente;
import com.forms.repository.ClienteRepository;

@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;
	public List<Cliente> buscarClientePorNome(String nome){
		return clienteRepository.findByNome(nome);
	}
	
	public Cliente getClienteById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElse(null);
	}
	
	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente updateCliente(Long id, Cliente updatedCliente) {
		Optional<Cliente> existingCliente = clienteRepository.findById(id);
		if(existingCliente.isPresent()) {
			updatedCliente.setId(id);
			return clienteRepository.save(updatedCliente);
		}
		return null;
	}

	public boolean deleteCliente(Long id) {
		Optional<Cliente> existingCliente = clienteRepository.findById(id);
		if(existingCliente.isPresent()) {
			clienteRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
