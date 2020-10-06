package com.oreilly.sbf.config;

import com.oreilly.sbf.entities.Greeting;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration  // JavaConfig --> class annotated with @Configuration,
                // uses @Bean method to add beans to app context
public class AppConfig {

    @Bean("greeting")
    public Greeting defaultGreeting() {
        return new Greeting();
    }

    @Bean @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Greeting hiGreeting() {
        return new Greeting("Hi there!");
    }

}
