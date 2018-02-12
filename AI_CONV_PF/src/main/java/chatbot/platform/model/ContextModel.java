package chatbot.platform.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContextModel {
	@JsonProperty("workspace")
	private String workspace;
	
	@JsonProperty("system")
	private SystemModel system;
	
	@JsonProperty("timezone")
	private String timezone;
	
	@JsonProperty("conversation_id")
	private String conversation_id;
	
	@JsonProperty("custom_context")
	private Map<String, Object> custom_context;
	
	/********** GETTER / SETTER **********/

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public SystemModel getSystem() {
		return system;
	}

	public void setSystem(SystemModel system) {
		this.system = system;
	}

	public String getTimezone() {
		return timezone;
	}

	public ContextModel setTimezone(String timezone) {
		this.timezone = timezone;
		return this;
	}

	public String getConversation_id() {
		return conversation_id;
	}

	public void setConversation_id(String conversation_id) {
		this.conversation_id = conversation_id;
	}

	public Map<String, Object> getCustom_context() {
		return custom_context;
	}

	public void setCustom_context(Map<String, Object> custom_context) {
		this.custom_context = custom_context;
	}
	
}
