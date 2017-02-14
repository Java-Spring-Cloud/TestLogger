package com.test.testlogger;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"cvv",
"cardNumber",
"baz"
})
public class SampleJson {

@JsonProperty("cvv")
private String cvv;
@JsonProperty("cardNumber")
private String cardNumber;
@JsonProperty("baz")
private Boolean baz;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("cvv")
public String getCvv() {
return cvv;
}

@JsonProperty("cvv")
public void setCvv(String cvv) {
this.cvv = cvv;
}

@JsonProperty("cardNumber")
public String getCardNumber() {
return cardNumber;
}

@JsonProperty("cardNumber")
public void setCardNumber(String cardNumber) {
this.cardNumber = cardNumber;
}

@JsonProperty("baz")
public Boolean getBaz() {
return baz;
}

@JsonProperty("baz")
public void setBaz(Boolean baz) {
this.baz = baz;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}