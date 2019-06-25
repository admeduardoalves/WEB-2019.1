package com.ufc.br.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.ufc.br.exception.SemPratosSuficienteException;
import com.ufc.br.model.Prato;
import com.ufc.br.repository.PratoRepository;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CarrinhoService {

	@Autowired
	private PratoRepository pratoRepository;

	private Map<Prato, Integer> pratos = new HashMap<>();

	public void adicionar(Prato prato) {
		if (pratos.containsKey(prato)) {
			pratos.replace(prato, pratos.get(prato) + 1);
		} else {
			pratos.put(prato, 1);
		}
	}

	public void remover(Prato prato) {
		if (pratos.containsKey(prato)) {
			if (pratos.get(prato) > 1)
				pratos.replace(prato, pratos.get(prato) - 1);
			else if (pratos.get(prato) == 1) {
				pratos.remove(prato);
			}
		}
	}

	public Map<Prato, Integer> getPratosCarrinho() {
		return Collections.unmodifiableMap(pratos);
	}

	public void finalizar() throws SemPratosSuficienteException {
		Optional<Prato> prato;
		for (Map.Entry<Prato, Integer> entry : pratos.entrySet()) {
			prato = pratoRepository.findById(entry.getKey().getCodigo());
			if (prato.get().getQuantidade() < entry.getValue())
				throw new SemPratosSuficienteException(prato);
			entry.getKey().setQuantidade(prato.get().getQuantidade() - entry.getValue());
		}
		pratoRepository.saveAll(pratos.keySet());
		// pratoRepository.flush();
		// pratoRepository.clear();
	}

	public BigDecimal getTotal() {
		return pratos.entrySet().stream()
				.map(entry -> entry.getKey().getPreco().multiply(BigDecimal.valueOf(entry.getValue())))
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}
}