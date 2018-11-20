package mx.com.klar.geolocation.service.business;

import java.text.MessageFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import mx.com.klar.geolocation.service.dto.Address;

/**
 * 
 * @author carlos.ramos
 * @version 1.0
 *
 */
@Service
public class GoogleService {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(GoogleService.class);

  @Autowired
  private Environment environment;

  /**
   * 
   * @param latitud
   * @param longitud
   * @return
   */
  public Address getAddressByLatLng(final String latitud, final String longitud) {
    String urlGoogleApi = environment.getProperty("google.maps.api.url");
    Address address = null;
    ResponseEntity<String> responseEntity = null;
    RestTemplate template = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlGoogleApi);
    builder.queryParam("latlng", coordinatesToPath(latitud, longitud));
    builder.queryParam("key", environment.getProperty("google.maps.api.key"));
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<?> entity = new HttpEntity<>(headers);
    LOG.info("##########::::::::::URL: {}", builder.build().encode().toUri());
    responseEntity = template.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
    try {
      JSONObject jsonResource = (JSONObject) new JSONParser().parse(responseEntity.getBody());
      if (!jsonResource.get("status").equals("ZERO_RESULTS")) {
        JSONArray addressList = (JSONArray) jsonResource.get("results");
        JSONObject element = (JSONObject) addressList.get(0);
        address = new Address();
        address.setFormattedAddress(element.get("formatted_address").toString());
      }
    } catch (ParseException e) {
      LOG.error(e.getMessage(), e);
    }
    return address;
  }

  /**
   * 
   * @param latitud
   * @param longitud
   * @return
   */
  private final String coordinatesToPath(final String latitud, final String longitud) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(MessageFormat.format("{0},{1}", latitud, longitud));
    return stringBuilder.toString();
  }
}
