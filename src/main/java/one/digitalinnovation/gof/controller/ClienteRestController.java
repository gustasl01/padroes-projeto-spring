package one.digitalinnovation.gof.controller;

import jakarta.validation.Valid;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;

@Tag(name = "Clientes", description = "API de gerenciamento de clientes")
@RestController
@RequestMapping("clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@Operation(summary = "Buscar todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
	})
	@GetMapping
	public ResponseEntity<Iterable<Cliente>> buscarTodos() {
		return ResponseEntity.ok(clienteService.buscarTodos());
	}

	@Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente específico pelo seu ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente encontrado"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

	@Operation(summary = "Inserir novo cliente", description = "Cadastra um novo cliente com CEP integrado ao ViaCEP")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
	})
	@PostMapping
	public ResponseEntity<Cliente> inserir(@Valid @RequestBody Cliente cliente) {
		clienteService.inserir(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}

	@Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(cliente);
	}

	@Operation(summary = "Deletar cliente", description = "Remove um cliente do sistema")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
