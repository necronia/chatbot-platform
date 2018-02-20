package chatbot.platform.model.conv;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemModel {
	@JsonProperty("dialog_stack")
	private List<DialogStackModel> dialog_stack;
	
	@JsonProperty("dialog_turn_counter")
	private Double dialog_turn_counter;
	
	@JsonProperty("dialog_request_counter")
	private Double dialog_request_counter;
	
	@JsonProperty("_node_output_map")
	private Map<String, List<Double>> _node_output_map;
	
	@JsonProperty("branch_exited")
	private boolean branch_exited;
	
	@JsonProperty("branch_exited_reason")
	private String branch_exited_reason;
	
	/********** GETTER / SETTER **********/

	public List<DialogStackModel> getDialog_stack() {
		return dialog_stack;
	}

	public void setDialog_stack(List<DialogStackModel> dialog_stack) {
		this.dialog_stack = dialog_stack;
	}

	public Double getDialog_turn_counter() {
		return dialog_turn_counter;
	}

	public void setDialog_turn_counter(Double dialog_turn_counter) {
		this.dialog_turn_counter = dialog_turn_counter;
	}

	public Double getDialog_request_counter() {
		return dialog_request_counter;
	}

	public void setDialog_request_counter(Double dialog_request_counter) {
		this.dialog_request_counter = dialog_request_counter;
	}

	public Map<String, List<Double>> get_node_output_map() {
		return _node_output_map;
	}

	public void set_node_output_map(Map<String, List<Double>> _node_output_map) {
		this._node_output_map = _node_output_map;
	}

	public boolean isBranch_exited() {
		return branch_exited;
	}

	public void setBranch_exited(boolean branch_exited) {
		this.branch_exited = branch_exited;
	}

	public String getBranch_exited_reason() {
		return branch_exited_reason;
	}

	public void setBranch_exited_reason(String branch_exited_reason) {
		this.branch_exited_reason = branch_exited_reason;
	}	
}
