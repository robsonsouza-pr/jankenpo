package br.com.btgtest.jokenpo.scopes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class ScopesConfig {
	
    @Bean
    @ApplicationScope
    public UserStorage storedUsers() {
        return new UserStorage();
    }
    
    @Bean
    @ApplicationScope
    public MoveStorage storedMoves() {
        return new MoveStorage();
    }

}
