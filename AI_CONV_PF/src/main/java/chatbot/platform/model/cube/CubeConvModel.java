package chatbot.platform.model.cube;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import chatbot.platform.util.ConvModelWrapper;

public class CubeConvModel {	
	@JsonProperty("UNIQUENAME")
	private String UNIQUENAME;
	
	@JsonProperty("CHANNELID")
	private String CHANNELID;
	
	@JsonProperty("QUESTION")
	private String QUESTION;
	
	@JsonProperty("ANSWER")
	private String ANSWER;	
	
	@JsonProperty("CONTEXT")
	private String CONTEXT;
	
	public CubeConvModel(CubeInfoModel ciModel, ConvModelWrapper conv) {
		this.UNIQUENAME = ciModel.getInfo().getUniquename();
		this.CHANNELID = ciModel.getInfo().getChannelid();
		this.QUESTION = conv.getInputText();
		this.ANSWER = conv.getConversationModel().getOutput().getText().get(0);
		try {
			this.CONTEXT = conv.getContextString();
		} catch (JsonProcessingException e) {
			this.CONTEXT = "JsonProcessingException";
		}
	}

	/********** GETTER / SETTER **********/

	public String getUNIQUENAME() {
		return UNIQUENAME;
	}

	public void setUNIQUENAME(String uNIQUENAME) {
		UNIQUENAME = uNIQUENAME;
	}

	public String getCHANNELID() {
		return CHANNELID;
	}

	public void setCHANNELID(String cHANNELID) {
		CHANNELID = cHANNELID;
	}

	public String getQUESTION() {
		return QUESTION;
	}

	public void setQUESTION(String qUESTION) {
		QUESTION = qUESTION;
	}

	public String getANSWER() {
		return ANSWER;
	}

	public void setANSWER(String aNSWER) {
		ANSWER = aNSWER;
	}	

	public String getCONTEXT() {
		return CONTEXT;
	}

	public void setCONTEXT(String cONTEXT) {
		CONTEXT = cONTEXT;
	}
}
