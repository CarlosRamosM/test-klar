package mx.com.klar.geolocation.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author carlos.ramos
 * @version 1.0
 *
 */
@Configuration
public class ApiConfig {

  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
    return factory -> factory.setContextPath("/test-klar/geolocation");
  }
}
