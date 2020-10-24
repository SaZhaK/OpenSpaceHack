package Application.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/console/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/me/**").permitAll()
                .antMatchers("/report/**").permitAll()
                .antMatchers("/reports/**").permitAll()
                .antMatchers("/signup/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/buy/**").permitAll()
                .antMatchers("/app/**").permitAll()
                .antMatchers("/ws/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/upload/**").permitAll()
                .antMatchers("/", "/resources/**").permitAll()
                .antMatchers("/**").hasRole("ADMIN");
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .logoutSuccessUrl("/");

        http.headers().frameOptions().disable();

    }
}