package com.erp.config;
/**
 * @author Satyam Kumar
 *
 */

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.erp.auth.CustomLogoutSuccessHandler;
import com.erp.auth.HttpAuthenticationEntryPoint;
import com.erp.auth.JwtAuthTokenFilter;
import com.erp.auth.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthTokenFilter jwtAuthFilter;

	@Autowired
	private CustomLogoutSuccessHandler customLogouthandler;

	@Autowired
	private JWTLoginFilter jwtLogin;

	@Autowired
	DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsServiceBean;

	@Autowired
	private HttpAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	public void configAuthentication(final AuthenticationManagerBuilder auth) throws Exception {
		final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsServiceBean);
		provider.setPasswordEncoder(passwordEncoder());
		provider.afterPropertiesSet();
		auth.authenticationProvider(provider);
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// UI Elements
		http.authorizeRequests().antMatchers("/api/v0/check-jwt-token/*").permitAll();
        // Allow access to swagger
		http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**")
				.permitAll();
		
		 // App status
        http.authorizeRequests().antMatchers("/api/v0/app-status").permitAll();
        // clear cache
		http.authorizeRequests().antMatchers("/api/v0/cache/evict-cache").permitAll();
		// All Other API
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        // http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.logout().logoutUrl("/api/v0/logout").logoutSuccessHandler(customLogouthandler);
        http.addFilterBefore(jwtLogin, UsernamePasswordAuthenticationFilter.class).addFilterBefore(jwtAuthFilter,
                UsernamePasswordAuthenticationFilter.class);
	}

}
