package chatbot.platform.model.cube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CubeConvModel {	
	@JsonProperty("uniquename")
	private String uniquename;
	
	@JsonProperty("channelid")
	private String channelid;
	
	@JsonProperty("question")
	private String question;
	
	@JsonProperty("answer")
	private String answer;	
	
	@JsonProperty("context")
	private String context;
	
	/********** GETTER / SETTER **********/

	public String getUniquename() {
		return uniquename;
	}

	public void setUniquename(String uniquename) {
		this.uniquename = uniquename;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}	
}
