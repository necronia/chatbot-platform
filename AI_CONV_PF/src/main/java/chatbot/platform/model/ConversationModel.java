package chatbot.platform.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversationModel {
	@JsonProperty("output")
	private OutputModel output;
	
	@JsonProperty("input")
	private InputModel input;
	
	@JsonProperty("intents")
	private List<IntentModel> intents;
	
	@JsonProperty("entities")
	private List<EntityModel> entities;
	
	@JsonProperty("context")
	private ContextModel context;
	
	/********** GETTER / SETTER **********/

	public OutputModel getOutput() {
		return output;
	}

	public void setOutput(OutputModel output) {
		this.output = output;
	}

	public InputModel getInput() {
		return input;
	}

	public void setInput(InputModel input) {
		this.input = input;
	}

	public List<IntentModel> getIntents() {
		return intents;
	}

	public void setIntents(List<IntentModel> intents) {
		this.intents = intents;
	}

	public List<EntityModel> getEntities() {
		return entities;
	}

	public void setEntities(List<EntityModel> entities) {
		this.entities = entities;
	}

	public ContextModel getContext() {
		return context;
	}

	public void setContext(ContextModel context) {
		this.context = context;
	}	
}
