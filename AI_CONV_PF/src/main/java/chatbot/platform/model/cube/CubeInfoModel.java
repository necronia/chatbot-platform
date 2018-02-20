package chatbot.platform.model.cube;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CubeInfoModel {
	@JsonProperty("info")
	private InfoModel info;

	public InfoModel getInfo() {
		return info;
	}

	public void setInfo(InfoModel info) {
		this.info = info;
	}
}
