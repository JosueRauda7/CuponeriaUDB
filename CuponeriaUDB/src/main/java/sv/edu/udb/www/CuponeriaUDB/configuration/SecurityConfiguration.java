package sv.edu.udb.www.CuponeriaUDB.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
        http
        .authorizeRequests()

<<<<<<< HEAD
            .antMatchers("/css/**", "/js/**","/empresa/**", "/fonts/**","/images/**" ).permitAll()
            .antMatchers("/css/**", "/js/**","/images/**","/empresa/**","/administrador/**").permitAll()
            
            .antMatchers("/css/**", "/js/**", "/empleado/**", "/fonts/**", "/images/**").permitAll()
            
            //.antMatchers("/css/**", "/js/**", "/login**", "/fonts/**", "/images/**").permitAll()

            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").defaultSuccessUrl("/login")
            .usernameParameter("correo").passwordParameter("password")
            .permitAll();
=======
        .antMatchers("/css/**", "/js/**","/empresa/**", "/fonts/**","/images/**" ).permitAll()
          .antMatchers("/css/**", "/js/**","/images/**","/empresa/**","/administrador/**").permitAll()
        .antMatchers("/css/**", "/js/**", "/empleado/**", "/fonts/**", "/images/**").permitAll()
            .antMatchers("/css/**", "/js/**", "/cliente/**", "/fonts/**", "/images/**").permitAll();
            //.anyRequest().authenticated()
            //.and()
            //.formLogin().loginPage("/login").defaultSuccessUrl("/editoriales/list")
            //.usernameParameter("usuario").passwordParameter("password")
            //.permitAll();
>>>>>>> 295df5adabd9b9f983723d28840c53ce8f2d9a21

    }

}
