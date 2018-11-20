package mx.com.klar.geolocation.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author carlos.ramos
 * @version 1.0
 *
 */
@SpringBootApplication(scanBasePackages = { "mx.com.klar.geolocation" })
public class Main {

  /**
   * 
   * @param args
   */
  public static void main(String... args) {
    SpringApplication.run(Main.class, args);
  }
}
