package chatbot.platform.model.conv;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IntentModel {
	@JsonProperty("intent")
	private String intent;
	
	@JsonProperty("confidence")
	private Double confidence;
	
	/********** GETTER / SETTER **********/
	
	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public Double getConfidence() {
		return confidence;
	}

	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}
}
