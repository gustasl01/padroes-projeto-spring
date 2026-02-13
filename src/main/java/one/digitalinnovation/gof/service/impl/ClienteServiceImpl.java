package one.digitalinnovation.gof.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Cliente não encontrado com ID: " + id));
	}

	@Override
	public void inserir(Cliente cliente) {
		if (cliente == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo");
		}
		if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
			throw new IllegalArgumentException("CEP é obrigatório");
		}
		salvarClienteComCep(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Cliente clienteBd = clienteRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Cliente não encontrado com ID: " + id));
		
		if (cliente == null) {
			throw new IllegalArgumentException("Cliente não pode ser nulo");
		}
		if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
			throw new IllegalArgumentException("CEP é obrigatório");
		}
		
		clienteBd.setNome(cliente.getNome());
		salvarClienteComCep(clienteBd);
	}

	@Override
	public void deletar(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new NoSuchElementException("Cliente não encontrado com ID: " + id);
		}
		clienteRepository.deleteById(id);
	}

	private void salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		
		cep = cep.replaceAll("[^0-9]", "");
		if (cep.length() != 8) {
			throw new IllegalArgumentException("CEP inválido. Deve conter 8 dígitos");
		}
		
		final String cepFormatado = cep;
		Endereco endereco = enderecoRepository.findById(cepFormatado).orElseGet(() -> {
			try {
				Endereco novoEndereco = viaCepService.consultarCep(cepFormatado);
				if (novoEndereco == null || novoEndereco.getCep() == null) {
					throw new IllegalArgumentException("CEP não encontrado na base do ViaCEP");
				}
				enderecoRepository.save(novoEndereco);
				return novoEndereco;
			} catch (Exception e) {
				throw new IllegalArgumentException("Erro ao consultar CEP: " + e.getMessage());
			}
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}
}
