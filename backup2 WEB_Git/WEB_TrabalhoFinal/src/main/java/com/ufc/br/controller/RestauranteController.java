package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Pessoa;
import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;

@Controller
@Scope("session")
@RequestMapping("/restaurante")
public class RestauranteController {

	@Autowired
	private PratoService pratoService;

	@RequestMapping("/inicio")
	public ModelAndView formIni() {
		List<Prato> pratos = pratoService.retonarTodosPratos();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaDePratos", pratos);
		return mv;
	}

//    @RequestMapping("/formulario")
//    public ModelAndView form() {
//    	ModelAndView mv = new ModelAndView("formulario");
//    	return mv;
//    }

	@RequestMapping("/logar")
	public ModelAndView formLogin() {
		ModelAndView mv = new ModelAndView("Login");
		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		List<Prato> pratos = pratoService.retonarTodosPratos();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaDePratos", pratos);
		return mv;
	}

	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("redirect:/carrinho");
		return mv;
	}

	@RequestMapping("/formulario")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("Formulario");
		mv.addObject(new Pessoa());
		return mv;
	}
}
