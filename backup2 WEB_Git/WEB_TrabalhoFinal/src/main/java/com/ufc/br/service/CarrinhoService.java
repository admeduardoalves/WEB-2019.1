package com.ufc.br.service;

import com.ufc.br.model.Prato;
import com.ufc.br.repository.carrinhoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;


@Service
@Component
@SessionScope
public class CarrinhoService {
	
	@Autowired
	private carrinhoRepository carrinhoRepository;
	
	private List<Prato> pratos = new ArrayList<>();

	public void adicionar(Prato prato) {
		carrinhoRepository.save(prato);

	}	

	public List<Prato> listar() {
		return pratos;
	}

	public void remover(Prato prato) {
		pratos.remove(prato);
	}

	public Integer quantidade() {
		return pratos.size();
	}

	public double total() {
		double total = 0;
		for (Prato prato : pratos) {
			total += prato.getPreco();
		}
		return total;
	}

	public Object buscarPorId(Long codigo) {
		return null;
	}
}