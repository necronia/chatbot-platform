package chatbot.platform.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityModel {
	@JsonProperty("entity")
	private String entity;
	
	@JsonProperty("location")
	private List<Double> location;
	
	@JsonProperty("value")
	private String value;
	
	@JsonProperty("confidence")
	private Double confidence;

	/********** GETTER / SETTER **********/

	public String getEntity() {
		return entity;
	}
	
	public void setEntity(String entity) {
		this.entity = entity;
	}

	public List<Double> getLocation() {
		return location;
	}

	public void setLocation(List<Double> location) {
		this.location = location;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Double getConfidence() {
		return confidence;
	}

	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}	
}
