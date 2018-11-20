package mx.com.klar.geolocation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.klar.geolocation.service.business.GoogleService;
import mx.com.klar.geolocation.service.dto.Address;
import mx.com.klar.geolocation.service.util.ErrorBean;

/**
 * 
 * @author carlos.ramos
 * @version 1.0
 *
 */
@RestController
@RequestMapping(path = "/examen", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
    MediaType.APPLICATION_JSON_VALUE })
public class GLService {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(GLService.class);
  
  @Autowired
  private GoogleService service;

  @GetMapping(path = { "/info" })
  public ResponseEntity<?> getInfo(@RequestParam("latitud") String latitud, @RequestParam("longitud") String longitud) {
    LOG.info("##########:::::::::: Inicio busqueda de direccion...");
    ResponseEntity<?> response = null;
    Address address = service.getAddressByLatLng(latitud, longitud);
    if (address != null) {
      response = new ResponseEntity<>(address, HttpStatus.OK);
    } else {
      ErrorBean errorBean = new ErrorBean();
      errorBean.setStatus(HttpStatus.NOT_FOUND.value());
      errorBean.setMessage("No se encontro la direccción solicitada");
      response = new ResponseEntity<ErrorBean>(errorBean, HttpStatus.NOT_FOUND);
    }
    return response;
  }
}
