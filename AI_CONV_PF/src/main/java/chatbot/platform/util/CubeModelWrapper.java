package chatbot.platform.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import chatbot.platform.model.cube.CubeConvModel;
import chatbot.platform.model.cube.CubeInfoModel;

public class CubeModelWrapper {
	private Logger logger;	
	private CubeInfoModel infoModel;
	private CubeConvModel convModel;
	private ObjectMapper mapper;
	
	public CubeModelWrapper(){
		this.mapper = new ObjectMapper();
	}
	
	public CubeModelWrapper(CubeInfoModel infoModel){
		this.mapper = new ObjectMapper();
		this.infoModel = infoModel;		
	}
	
	public CubeModelWrapper(CubeConvModel convModel){
		this.mapper = new ObjectMapper();
		this.convModel = convModel;		
	}
	
	public CubeModelWrapper(String ciModel) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException{
		this.mapper = new ObjectMapper();
		this.infoModel = mapper.readValue(ciModel.getBytes("UTF-8"), CubeInfoModel.class);
		if (logger.isDebugEnabled()){
			logger.debug("★★★★★★★★★★★★ CubeModelWrapper created : " + this.mapper.writeValueAsString(this.infoModel));
		}
	}
	
	public CubeModelWrapper setCubeInfoModel(CubeInfoModel infoModel){
		this.infoModel = infoModel;	
		return this;
	}
	
	public CubeModelWrapper setCubeConvModel(CubeConvModel convModel){
		this.convModel = convModel;	
		return this;
	}
	
	public CubeInfoModel getCubeInfoModel(){
		return this.infoModel;
	}
	
	public String getCubeInfoModelString() throws JsonProcessingException{
		return this.mapper.writeValueAsString(this.infoModel);	
	}
	
	public CubeConvModel getCubeConvInfo(){
		return this.convModel;
	}

	public CubeConvModel makeCubeConvInfo(String question, String answer, String contextString) {
		this.convModel.setUNIQUENAME(this.infoModel.getInfo().getUniquename());
		this.convModel.setCHANNELID(this.infoModel.getInfo().getChannelid());
		this.convModel.setQUESTION(question);
		this.convModel.setANSWER(answer);
		this.convModel.setCONTEXT(contextString);
		
		
		return this.convModel;
	}
	
	public void convertQuestionToAnswer(){
		this.infoModel.setInfo(this.infoModel.getInfo().setRec(this.convModel.getANSWER()));
		
	}
}
