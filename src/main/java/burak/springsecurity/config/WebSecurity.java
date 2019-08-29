package burak.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                    .antMatchers("/","/home").permitAll()
                    .antMatchers("/user/**").hasAnyRole("USER")
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/manager/**").hasAnyRole("MANAGER")
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .addLogoutHandler(logoutHandler)
                    //.logoutSuccessUrl("/?logout")
                .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}p").roles("USER")
                .and()
                .withUser("admin").password("{noop}p").roles("ADMIN","USER")
                .and()
                .withUser("manager").password("{noop}p").roles("MANAGER","USER")
                .and()
                .withUser("burak").password("{noop}p").roles("MANAGER","USER");
    }
}
