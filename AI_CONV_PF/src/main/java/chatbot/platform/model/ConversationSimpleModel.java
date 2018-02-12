package chatbot.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversationSimpleModel {
	@JsonProperty("outputText")
	private String outputText;
	
	@JsonProperty("inputText")
	private String inputText;	
	
	@JsonProperty("context")
	private ContextModel context;
	
	/********** GETTER / SETTER **********/

	public String getOutputText() {
		return outputText;
	}

	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public ContextModel getContext() {
		return context;
	}

	public void setContext(ContextModel context) {
		this.context = context;
	}
}
