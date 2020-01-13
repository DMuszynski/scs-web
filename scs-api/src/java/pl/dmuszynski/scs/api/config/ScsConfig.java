package pl.dmuszynski.scs.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ScsConfig {
    @Bean
    public BCryptPasswordEncoder pwdEncrypt(){
        return new BCryptPasswordEncoder();
    }
}
