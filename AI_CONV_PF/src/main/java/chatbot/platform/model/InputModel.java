package chatbot.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputModel {
	@JsonProperty("text")
	private String text;
	
	/********** GETTER / SETTER **********/
	
	public String getText() {
		return text;
	}

	public InputModel setText(String text) {
		this.text = text;
		return this;
	}
}
