package com.huseyinaydin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        MySimpleUrlAuthenticationSuccessHandler handler = applicationContext.getBean("myAuthenticationSuccessHandler",MySimpleUrlAuthenticationSuccessHandler.class);
        MyLogoutSuccessHandler handler2 = applicationContext.getBean("myLogoutSuccessHandler",MyLogoutSuccessHandler.class);
        SessionRegistry sessionRegistry = applicationContext.getBean("sessionRegistry",SessionRegistry.class);
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll()
                .antMatchers("/uploads/**").permitAll()
                .and().formLogin().successHandler(handler)
                .and().logout().logoutSuccessHandler(handler2)
                .and().formLogin().and().rememberMe();
                //.and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry);
        /*http.logout()
                .addLogoutHandler(new CustomLogoutHandler());*/

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("huso")
                .password("123")
                .roles("USER")
                .and()
                .withUser("ali")
                .password("123")
                .roles("ADMIN");
    }


}
