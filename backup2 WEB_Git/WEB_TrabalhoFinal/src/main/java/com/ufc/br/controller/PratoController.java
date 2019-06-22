package com.ufc.br.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ufc.br.model.Prato;
import com.ufc.br.service.PratoService;

@Controller
@RequestMapping("/prato")
public class PratoController {
	
	@Autowired
	private PratoService pratoService;
	
	@RequestMapping("/cadastrarPratos")
	public ModelAndView formPratosAdm() {
		ModelAndView mv = new ModelAndView("cadastrarPratos");
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	
	@PostMapping("/cadastroDePratos")
	public ModelAndView cadastrarPrato(@Validated Prato prato, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		ModelAndView mv = new ModelAndView("cadastrarPratos");		
		if(result.hasErrors()) 
			return mv; //retorna pra página de form e nem tenta salvar
		
		pratoService.cadastrarPrato(prato,imagem);
		mv.addObject("mensagem", "Prato Cadastrado com sucesso");
		return mv;
	}
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/index");
		return mv;
	}
	
	@RequestMapping("/inicio")
	public ModelAndView inicio() {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/inicio");
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/login");
		return mv;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView fomulario() {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/formulario");
		return mv;
	}
	
	
	@GetMapping("/ListarPratos")
	public ModelAndView ListarPratos() {
		List<Prato> prato = pratoService.retonarTodosPratos();
		ModelAndView mv = new ModelAndView("ListarPratos");
		mv.addObject("ListaPratos", prato);
	
		return mv;
	}
	
    @RequestMapping("/excluir/{codigo}")
    public ModelAndView excluirPrato(@PathVariable Long codigo) {
    	pratoService.excluirPrato(codigo);
    	ModelAndView mv = new ModelAndView("/listarPratos");
    	return mv;
    }
	
    @RequestMapping("/atualizar/{codigo}")
    public ModelAndView atualizarPrato(@PathVariable Long codigo) {
    	Prato prato = pratoService.buscarPorId(codigo);
    	ModelAndView mv = new ModelAndView("cadastrarPratos");
    	mv.addObject("prato", prato);
    	return mv;
    	
    }
  
	@GetMapping("/pratos/adicionar")
	public ModelAndView add(Prato prato) {
		ModelAndView mv = new ModelAndView("/pratos/pratoForm");
		mv.addObject("prato", prato);
		return mv;
	}
	
	
//	@RequestMapping("/formulario")
//	public ModelAndView form() {
//		ModelAndView mv = new ModelAndView("Formulario");
//		mv.addObject(new Pessoa());
//		return mv;
//	}
	
//	@RequestMapping("/inicio")
//	public ModelAndView formIni() {
//		List<Prato> pratos = pratoService.retonarTodosPratos();
//		ModelAndView mv = new ModelAndView("index");
//		mv.addObject("listaDePratos", pratos);
//		return mv;
//	}
	


}
