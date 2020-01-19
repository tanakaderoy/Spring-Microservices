package com.tanaka.microservices.currencyconversionservice;

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
"id",
"from",
"to",
"conversionMultiple",
"port"
})
public class ConversionMultipleModel {

@JsonProperty("id")
private Integer id;
@JsonProperty("from")
private String from;
@JsonProperty("to")
private String to;
@JsonProperty("conversionMultiple")
private Double conversionMultiple;
@JsonProperty("port")
private Integer port;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("from")
public String getFrom() {
return from;
}

@JsonProperty("from")
public void setFrom(String from) {
this.from = from;
}

@JsonProperty("to")
public String getTo() {
return to;
}

@JsonProperty("to")
public void setTo(String to) {
this.to = to;
}

@JsonProperty("conversionMultiple")
public Double getConversionMultiple() {
return conversionMultiple;
}

@JsonProperty("conversionMultiple")
public void setConversionMultiple(Double conversionMultiple) {
this.conversionMultiple = conversionMultiple;
}

@JsonProperty("port")
public Integer getPort() {
return port;
}

@JsonProperty("port")
public void setPort(Integer port) {
this.port = port;
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