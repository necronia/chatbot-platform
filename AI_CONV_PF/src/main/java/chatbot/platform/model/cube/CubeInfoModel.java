package chatbot.platform.model.cube;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "JsonProcessingException";
		}
	}
	
	
}
