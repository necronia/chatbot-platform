package chatbot.platform.model.conv;

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
	
	@JsonProperty("perm_context")
	private Map<String, Object> perm_context;
	
	@JsonProperty("slot_val1")
	private String slot_val1;
	
	@JsonProperty("slot_val2")
	private String slot_val2;
	
	@JsonProperty("slot_val3")
	private String slot_val3;
	
	@JsonProperty("slot_val4")
	private String slot_val4;
	
	@JsonProperty("slot_val5")
	private String slot_val5;
	
	@JsonProperty("slot_val6")
	private String slot_val6;
	
	@JsonProperty("slot_val7")
	private String slot_val7;
	
	@JsonProperty("slot_val8")
	private String slot_val8;
	
	@JsonProperty("slot_val9")
	private String slot_val9;
	
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
		return this.custom_context;
	}

	public ContextModel setCustom_context(Map<String, Object> custom_context) {
		this.custom_context = custom_context;
		return this;
	}

	public String getSlot_val1() {
		return slot_val1;
	}

	public void setSlot_val1(String slot_val1) {
		this.slot_val1 = slot_val1;
	}

	public String getSlot_val2() {
		return slot_val2;
	}

	public void setSlot_val2(String slot_val2) {
		this.slot_val2 = slot_val2;
	}

	public String getSlot_val3() {
		return slot_val3;
	}

	public void setSlot_val3(String slot_val3) {
		this.slot_val3 = slot_val3;
	}

	public String getSlot_val4() {
		return slot_val4;
	}

	public void setSlot_val4(String slot_val4) {
		this.slot_val4 = slot_val4;
	}

	public String getSlot_val5() {
		return slot_val5;
	}

	public void setSlot_val5(String slot_val5) {
		this.slot_val5 = slot_val5;
	}

	public String getSlot_val6() {
		return slot_val6;
	}

	public void setSlot_val6(String slot_val6) {
		this.slot_val6 = slot_val6;
	}

	public String getSlot_val7() {
		return slot_val7;
	}

	public void setSlot_val7(String slot_val7) {
		this.slot_val7 = slot_val7;
	}

	public String getSlot_val8() {
		return slot_val8;
	}

	public void setSlot_val8(String slot_val8) {
		this.slot_val8 = slot_val8;
	}

	public String getSlot_val9() {
		return slot_val9;
	}

	public void setSlot_val9(String slot_val9) {
		this.slot_val9 = slot_val9;
	}

	public Map<String, Object> getPerm_context() {
		return perm_context;
	}

	public void setPerm_context(Map<String, Object> perm_context) {
		this.perm_context = perm_context;
	}	
}
