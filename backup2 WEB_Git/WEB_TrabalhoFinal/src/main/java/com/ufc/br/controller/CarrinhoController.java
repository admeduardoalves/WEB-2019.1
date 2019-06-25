package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.exception.SemPratosSuficienteException;
import com.ufc.br.repository.PratoRepository;
import com.ufc.br.service.CarrinhoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carinhoService;

	@Autowired
	private PratoRepository pratoRepository;

	@GetMapping
	public ModelAndView carrinho() {
		ModelAndView modelAndView = new ModelAndView("/carrinho");
		modelAndView.addObject("pratos", carinhoService.getPratosCarrinho());
		modelAndView.addObject("total", carinhoService.getTotal().toString());
		return modelAndView;
	}

	@GetMapping("/adicionar/{codigo}")
	public ModelAndView adicionar(@PathVariable("codigo") Long codigo) {
		pratoRepository.findById(codigo).ifPresent(carinhoService::adicionar);
		return carrinho();
	}

	@GetMapping("/remover/{codigo}")
	public ModelAndView removver(@PathVariable("codigo") Long codigo) {
		pratoRepository.findById(codigo).ifPresent(carinhoService::remover);
		return carrinho();
	}

	@GetMapping("/finalizar")
	public ModelAndView finalizar() {
		try {
			carinhoService.finalizar();
		} catch (SemPratosSuficienteException e) {
			return carrinho().addObject("outOfStockMessage", e.getMessage());
		}

		// Lógica ao finalizar o carrinho

		// Como utilizar o HashMap:
		// https://www.devmedia.com.br/hashmap-java-trabalhando-com-listas-key-value/29811
		return carrinho();
	}
}
