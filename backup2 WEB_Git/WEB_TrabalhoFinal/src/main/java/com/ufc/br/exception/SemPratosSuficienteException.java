package com.ufc.br.exception;

import java.util.Optional;

import com.ufc.br.model.Prato;

@SuppressWarnings("serial")
public class SemPratosSuficienteException extends Exception {

	private static final String DEFAULT_MESSAGE = "Sem pratos suficientes no cozinha.";

	public SemPratosSuficienteException() {
		super(DEFAULT_MESSAGE);
	}

	public SemPratosSuficienteException(Optional<Prato> prato) {
		super(String.format("Sem pratos %s suficientes no cozinha. Apenas %d restantes.", prato.get().getNome(),
				prato.get().getQuantidade()));
	}
}