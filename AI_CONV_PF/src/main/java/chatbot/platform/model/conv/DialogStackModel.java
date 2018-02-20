package chatbot.platform.model.conv;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogStackModel {
	@JsonProperty("dialog_node")
	private String dialog_node;
	
	@JsonProperty("state")
	private String state;

	
	/********** GETTER / SETTER **********/
	
	public String getDialog_node() {
		return dialog_node;
	}

	public void setDialog_node(String dialog_node) {
		this.dialog_node = dialog_node;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
