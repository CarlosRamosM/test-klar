package mx.com.klar.geolocation.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(content = Include.NON_NULL)
public class Address {

  @JsonProperty(value = "direccion")
  private String formattedAddress;

  public String getFormattedAddress() {
    return formattedAddress;
  }

  public void setFormattedAddress(String formattedAddress) {
    this.formattedAddress = formattedAddress;
  }

  @Override
  public String toString() {
    return "Address [formattedAddress=" + formattedAddress + "]";
  }
}
