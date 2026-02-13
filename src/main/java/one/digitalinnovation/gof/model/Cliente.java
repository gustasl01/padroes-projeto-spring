package one.digitalinnovation.gof.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Representa um cliente com seus dados pessoais e endereço")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(description = "ID único do cliente", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	@Schema(description = "Nome completo do cliente", example = "João Silva")
	private String nome;
	
	@NotNull(message = "Endereço é obrigatório")
	@ManyToOne
	@Schema(description = "Endereço do cliente")
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
