package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import com.ufc.br.service.CarrinhoService;
import com.ufc.br.service.PratoService;

@Controller
public class carrinhoController {
	
	@Autowired
	private CarrinhoService carrinhoService;

	@Autowired
	private PratoService pratoService;

	@GetMapping("/carrinho")
	public ModelAndView carrinho() {
		System.out.println("carrinho");
		ModelAndView modelAndView = new ModelAndView("carrinho");
		modelAndView.addObject("produtos", carrinhoService.listar());
		modelAndView.addObject("total", carrinhoService.total());
		return modelAndView;
	}

	@GetMapping("/adicionar/{codigo}")
	public ModelAndView add(@PathVariable("codigo") Long codigo) {
		System.out.println("add carrinho");
		carrinhoService.adicionar(pratoService.buscarPorId(codigo));
		System.out.println(pratoService.buscarPorId(codigo));
		return new ModelAndView("redirect:/restaurante/carrinho");
	}

	@GetMapping("/remover/{id}")
	public ModelAndView remove(@PathVariable("id") Long codigo) {
		carrinhoService.remover(pratoService.buscarPorId(codigo));
		return carrinho();
	}
}
