package com.hkprogrammer.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkprogrammer.algafood.models.Cliente;
import com.hkprogrammer.algafood.services.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {

	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
	}
	
	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		Cliente joao = new Cliente("João", "joao@email.com", "23231232");
		
		ativacaoClienteService.ativar(joao);
		
		return "Hello";
	}
	
}
