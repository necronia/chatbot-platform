package chatbot.platform.model.cube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CubeInfoModel {
	@JsonProperty("info")
	private InfoModel info;
	
	@JsonProperty("rec")
	private String rec;
	
	/********** GETTER / SETTER **********/

	public InfoModel getInfo() {
		return info;
	}
	
	public void setInfo(InfoModel info) {
		this.info = info;
	}

	public String getRec() {
		return rec;
	}

	public CubeInfoModel setRec(String rec) {
		this.rec = rec;
		return this;
	}
}
