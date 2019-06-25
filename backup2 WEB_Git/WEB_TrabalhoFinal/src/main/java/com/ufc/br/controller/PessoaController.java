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

import com.ufc.br.model.Pessoa;
import com.ufc.br.service.PessoaService;
//import com.ufc.br.model.Prato;
//import com.ufc.br.service.PratoService;
//import org.springframework.web.bind.annotation.PutMapping;
//import com.ufc.br.repository.PessoaRepository;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
//	@Autowired
//	private PratoService pratoService;

	@PostMapping("/salvar")
	public ModelAndView cadastrar(@Validated Pessoa pessoa, BindingResult result,
			@RequestParam(value = "imagem") MultipartFile imagem) {
		ModelAndView mv = new ModelAndView("Formulario");

		if (result.hasErrors()) {
			return mv; // retorna pra página de form e nem tenta salvar
		}

		pessoaService.cadastrar(pessoa, imagem);
		mv.addObject("mensagem", "Pessoa Cadastrada com sucesso");

		return mv;
	}

	@GetMapping("/listar")
	public ModelAndView listarPessoas() {
		List<Pessoa> pessoas = pessoaService.retonarTodasAsPessoas();
		ModelAndView mv = new ModelAndView("PaginaListagem");
		mv.addObject("listaDePessoas", pessoas);

		return mv;
	}

	@RequestMapping("/excluir/{codigo}")
	public ModelAndView excluirPessoa(@PathVariable Long codigo) {
		pessoaService.excluirPessoa(codigo);
		ModelAndView mv = new ModelAndView("/listar");
		return mv;
	}

	@RequestMapping("/atualizar/{codigo}")
	public ModelAndView atualizarPessoa(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaService.buscarPorId(codigo);
		ModelAndView mv = new ModelAndView("Formulario");
		mv.addObject("pessoa", pessoa);
		return mv;

	}

	@RequestMapping("/formulario")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("Formulario");
		mv.addObject(new Pessoa());
		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/index");
		return mv;
	}

	@RequestMapping("/logar")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/login");
		return mv;
	}

	@RequestMapping("/administrador")
	public ModelAndView formAdm() {
		ModelAndView mv = new ModelAndView("administracao");
		return mv;
	}

	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("redirect:/carrinho");
		return mv;
	}

	@RequestMapping("/cadastrarPratos")
	public ModelAndView cadastrarPratos() {
		ModelAndView mv = new ModelAndView("redirect:/prato/cadastrarPratos");
		return mv;
	}

//	@RequestMapping("/inicio")
//	public ModelAndView formIni() {
//		List<Prato> pratos = pratoService.retonarTodosPratos();
//		ModelAndView mv = new ModelAndView("index");
//		mv.addObject("listaDePratos", pratos);
//		return mv;
//	}
//		
//	 
//    @RequestMapping("/logar")
//    public ModelAndView formLogin() {
//    	ModelAndView mv = new ModelAndView("Login");
//    	return mv;
//    }
//    
//	@PostMapping("/cadastroDePratos")
//	public ModelAndView cadastrarPrato(@Validated Prato prato, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
//		ModelAndView mv = new ModelAndView("cadastrarPratos");		
//		if(result.hasErrors()) 
//			return mv; //retorna pra página de form e nem tenta salvar
//		
//		pratoService.cadastrarPrato(prato,imagem);
//		mv.addObject("mensagem", "Prato Cadastrado com sucesso");
//		return mv;
//	}
//	
//	@RequestMapping("/cadastrarPratos")
//	public ModelAndView formPratosAdm() {
//		ModelAndView mv = new ModelAndView("cadastrarPratos");
//		mv.addObject("prato", new Prato());
//		return mv;
//	}
//		
//	@RequestMapping("/carrinho")
//	public ModelAndView carrinho() {
//		ModelAndView mv = new ModelAndView("carrinho");
//		return mv;
//	}
//	
//	@RequestMapping("/index")
//	public ModelAndView index() {
//		List<Prato> pratos = pratoService.retonarTodosPratos();
//		ModelAndView mv = new ModelAndView("index");
//		mv.addObject("listaDePratos", pratos);
//		return mv;
//		}
//	
//    @RequestMapping("/comprar/{codigo}")
//    public ModelAndView comprarPrato(@PathVariable Long codigo) {
//    	pratoService.comprarPrato(codigo);
//    	ModelAndView mv = new ModelAndView("redirect:/pessoa/index");
//    	return mv;
//    }   
}
