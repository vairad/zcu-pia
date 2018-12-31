package cz.zcu.pia.revoloot.web.auth;

import cz.zcu.pia.revoloot.web.ServletNaming;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(ServletNaming.ADMIN + "/**")
                .hasRole("BANKER")
                .antMatchers(ServletNaming.CUSTOMER + "/**")
                .hasRole("CUSTOMER")
                .antMatchers("/*").permitAll()
                .and()
                .formLogin()
                .loginPage(ServletNaming.LOGIN)
                .loginProcessingUrl(ServletNaming.LOGIN_PROCESS)
                .defaultSuccessUrl(ServletNaming.CUSTOMER_DASHBOARD, true)
                .failureUrl(ServletNaming.LOGIN + "?error=true")
                //     .failureHandler(authenticationFailureHandler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl(ServletNaming.LOGOUT_PROCESS)
                .deleteCookies("JSESSIONID")
                .permitAll()
        //   .logoutSuccessHandler(logoutSuccessHandler());
        ;
    }
}
