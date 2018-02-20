package chatbot.platform.model.cube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CubeConvModel {	
	@JsonProperty("UNIQUENAME")
	private String UNIQUENAME;
	
	@JsonProperty("CHANNELID")
	private String CHANNELID;
	
	@JsonProperty("QUESTION")
	private String QUESTION;
	
	@JsonProperty("ANSWER")
	private String ANSWER;
	
	@JsonProperty("REC")
	private String REC;
	
	@JsonProperty("CONTEXT")
	private String CONTEXT;
	
	@JsonProperty("REGUSRID")
	private String REGUSRID;
	
	@JsonProperty("REGDT")
	private String REGDT;
	
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

	public String getREC() {
		return REC;
	}

	public void setREC(String rEC) {
		REC = rEC;
	}

	public String getCONTEXT() {
		return CONTEXT;
	}

	public void setCONTEXT(String cONTEXT) {
		CONTEXT = cONTEXT;
	}

	public String getREGUSRID() {
		return REGUSRID;
	}

	public void setREGUSRID(String rEGUSRID) {
		REGUSRID = rEGUSRID;
	}

	public String getREGDT() {
		return REGDT;
	}

	public void setREGDT(String rEGDT) {
		REGDT = rEGDT;
	}
}
