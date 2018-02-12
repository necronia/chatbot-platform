package chatbot.platform.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutputModel {
	@JsonProperty("text")
	private List<String> text;
	
	@JsonProperty("nodes_visited")
	private List<String> nodes_visited;
	
	@JsonProperty("log_messages")
	private List<LogMessage> log_messages;
	
	@JsonProperty("error")
	private String error;
	
	/********** GETTER / SETTER **********/
	
	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}

	public List<String> getNodes_visited() {
		return nodes_visited;
	}

	public void setNodes_visited(List<String> nodes_visited) {
		this.nodes_visited = nodes_visited;
	}

	public List<LogMessage> getLog_messages() {
		return log_messages;
	}

	public void setLog_messages(List<LogMessage> log_messages) {
		this.log_messages = log_messages;
	}
}
