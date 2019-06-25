package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping("/comprar/{codigo}")
	public ModelAndView comprar(@PathVariable Prato prato) {
		pedidoService.comprar(prato);
		ModelAndView mv = new ModelAndView("redirect:/pessoa/index");
		return mv;
	}

//	@RequestMapping("/carrinho")
//	public ModelAndView carrinho() {
//		ModelAndView mv = new ModelAndView("carrinho");
//		return mv;
//	}

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/index");
		return mv;
	}

	@RequestMapping("/logar")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/login");
		return mv;
	}
}
