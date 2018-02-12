package chatbot.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DialogStackModel {
	@JsonProperty("dialog_node")
	private String dialog_node;

	
	/********** GETTER / SETTER **********/
	
	public String getDialog_node() {
		return dialog_node;
	}

	public void setDialog_node(String dialog_node) {
		this.dialog_node = dialog_node;
	}
}
