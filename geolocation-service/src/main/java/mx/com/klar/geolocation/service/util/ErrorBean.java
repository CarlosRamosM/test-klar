package mx.com.klar.geolocation.service.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author carlos.ramos
 * @version 1.0
 *
 */
@JsonInclude(content = Include.NON_NULL)
public class ErrorBean {
  
  private Integer status;
  
  private String message;

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ErrorBean [status=" + status + ", message=" + message + "]";
  }
}
