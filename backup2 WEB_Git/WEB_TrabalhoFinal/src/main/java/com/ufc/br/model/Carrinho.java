package com.ufc.br.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Carrinho {
	
	private Long codigo_cliente;
	
	private List<Prato> pratos = new ArrayList<>();
	
	public Carrinho(Long codigo_cliente, List<Prato> pratos) {
		super();
		this.codigo_cliente = codigo_cliente;
		this.pratos = pratos;
	}

	public Object toCompra() {
		return null;
	}

	public Long getCodigo_cliente() {
		return codigo_cliente;
	}

	public void setCodigo_cliente(Long codigo_cliente) {
		this.codigo_cliente = codigo_cliente;
	}

	public List<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(List<Prato> pratos) {
		this.pratos = pratos;
	}
}
