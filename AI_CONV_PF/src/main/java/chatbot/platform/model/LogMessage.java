package chatbot.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogMessage {
	@JsonProperty("level")
	private String level;
	
	@JsonProperty("msg")
	private String msg;
	
	@JsonProperty("node_id")
	private String node_id;

	/********** GETTER / SETTER **********/
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getNode_id() {
		return node_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}
}
