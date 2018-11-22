package com.administrador.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.administrador.app.auth.handler.LoginSuccessHandler;


/**
 * Clase de configuracion para spring security
 * @author: Ricardo Gonzalez Ceballos
 * @version: 1.0
 */
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoginSuccessHandler successHandler;
	
	 /**
     * El metodo configura define a que rutas se le aplicara seguridad asi como el uso de form login 
     * @param Recibe el parametro http para poder realizar la configuracion basa en web para peticiones http
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**","/open/**").permitAll()
		.antMatchers("/detalle/**").hasAnyRole("USER")
		.antMatchers("/capturaproyecto/**").hasAnyRole("ADMIN")
		.antMatchers("/editarproyecto/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminarproyecto/**").hasAnyRole("ADMIN")
		.antMatchers("/guardar/**").hasAnyRole("ADMIN")
		.antMatchers("/auth/**").hasAnyRole("ADMIN","USER")
		.anyRequest().authenticated()
		.and()
		    .formLogin()
		        .successHandler(successHandler)
		        .loginPage("/login")
		    .permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");

	}

	 /**
     * El metodo configurerGlobal crea dos usuarios en memoria con dos roles diferentes para spring security
     * @param Recibe el parametro build que nos permite realizar autenticacion en memoria 
     */
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		build.inMemoryAuthentication()
		.withUser(users.username("admin").password("password").roles("ADMIN"))
		.withUser(users.username("consulta").password("password").roles("USER"));
	}
	
}
