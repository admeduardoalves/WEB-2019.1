package com.ufc.br.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ufc.br.model.Pedido;
import com.ufc.br.model.Prato;
import com.ufc.br.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void comprar(Prato prato) {		
		pedidoRepository.save(prato);
	}
	
	public Pedido buscarPorId(Long codigo_pessoa) {
		return pedidoRepository.getOne(codigo_pessoa);
	}

	public List<Pedido> retonarTodosPedidos(){
		return pedidoRepository.findAll();
	}
}
