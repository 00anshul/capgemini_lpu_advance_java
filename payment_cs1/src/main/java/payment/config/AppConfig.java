package payment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("payment")   // tells Spring to scan all classes under "payment" package
public class AppConfig {
    // no code needed here
    // @ComponentScan does the work — finds all @Component classes automatically
}