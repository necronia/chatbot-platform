package chatbot.platform.model.cube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoModel {
	@JsonProperty("uniquename")
	private String uniquename;
	
	@JsonProperty("channelid")
	private String channelid;
	
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
}
