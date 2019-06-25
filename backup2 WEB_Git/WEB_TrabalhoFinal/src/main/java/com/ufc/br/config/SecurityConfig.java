package com.ufc.br.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ufc.br.security.UserDetailsServiceImplementacao;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImplementacao userDetailsImplementacao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()

				.antMatchers("/").permitAll()
				// Somente uma pessoa com o papel ADMIN pode acessar essa URI

				// .antMatchers("/prato/cadastrarPratos").permitAll() //retorna apenas html
				// .antMatchers("/prato/cadastroDePratos").permitAll() //recebe o prato

				.antMatchers("/prato/cadastroDePratos").hasRole("ADMIN").antMatchers("/prato/ListarPratos")
				.hasRole("ADMIN").antMatchers("/pessoa/listar").permitAll().antMatchers("/pessoa/salvar").permitAll()
				.antMatchers("/pessoa/index").permitAll().antMatchers("/restaurante/formulario").permitAll()

				.antMatchers("/restaurante/administrador").hasRole("ADMIN")

				.antMatchers("/restaurante/index").permitAll().antMatchers("/restaurante/inicio").permitAll()

				.antMatchers("/carrinho").permitAll().anyRequest().authenticated()

				.and().formLogin().loginPage("/restaurante/logar").permitAll().defaultSuccessUrl("/restaurante/inicio")

				.and().logout().logoutSuccessUrl("/restaurante/logar?logout").permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/img/**");
	}

//  Pegar usuario logado	
//	Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	UserDetails user = (UserDetails) auth;
//			
//	Pessoa pessoa = pessoaService.buscaPorLogin(user.getUsername())
}
