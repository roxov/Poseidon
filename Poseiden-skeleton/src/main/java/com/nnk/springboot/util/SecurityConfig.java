package com.nnk.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.nnk.springboot.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

//	 @Override
//	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	        auth.inMemoryAuthentication()
//	         .passwordEncoder(encoder)
//	         .withUser("spring")
//	         .password(encoder.encode("secret"))
//	         .roles("USER");
//	    }

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//
//	private class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//		@Override
//		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//				Authentication authentication) throws IOException, ServletException {
//			response.setStatus(HttpServletResponse.SC_OK);
//		}
//	}
//
//	@Bean
//	public AuthenticationProvider getProvider() {
//		AppAuthProvider provider = new AppAuthProvider();
//		provider.setUserDetailsService(userDetailsService);
//		return provider;
//	}
}
