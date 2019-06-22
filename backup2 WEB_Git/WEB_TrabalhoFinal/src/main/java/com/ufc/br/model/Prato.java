package com.ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Prato  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@NotBlank(message = "Preencha o nome do prato")
	private String nome;
	
	@NotNull(message = "Preencha o valor do prato")
	@DecimalMin(value = "0.00")
	private double preco;
	
	private int qtd;
	
	public Prato() {
		
	}
		
	public Prato(Long codigo, @NotBlank(message = "Preencha o nome do prato") String nome,
			@NotBlank(message = "Preencha o valor do prato") @DecimalMin("0.00") double preco) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
	}
	
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
}
