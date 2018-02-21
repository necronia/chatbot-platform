package chatbot.platform.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		this.logger = LoggerFactory.getLogger(this.getClass());	
		this.mapper = new ObjectMapper();
		this.infoModel = new CubeInfoModel();
		this.convModel = new CubeConvModel();
	}
	
	public CubeModelWrapper(CubeInfoModel infoModel){
		this.logger = LoggerFactory.getLogger(this.getClass());	
		this.mapper = new ObjectMapper();
		this.infoModel = infoModel;		
		this.convModel = new CubeConvModel();
	}
	
	public CubeModelWrapper(CubeConvModel convModel){
		this.logger = LoggerFactory.getLogger(this.getClass());	
		this.mapper = new ObjectMapper();
		this.infoModel = new CubeInfoModel();
		this.convModel = convModel;		
	}
	
	public CubeModelWrapper(String ciModel) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException{
		this.logger = LoggerFactory.getLogger(this.getClass());	
		this.mapper = new ObjectMapper();
		this.infoModel = mapper.readValue(ciModel.getBytes("UTF-8"), CubeInfoModel.class);		
		if (logger.isDebugEnabled()){
			logger.debug("★★★★★★★★★★★★ CubeModelWrapper created : " + this.mapper.writeValueAsString(this.infoModel));
		}
		this.convModel = new CubeConvModel();
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
	
	public CubeConvModel getCubeConvModel(){
		return this.convModel;
	}

	public CubeConvModel makeCubeConvModel() {
		this.convModel.setUniquename(this.infoModel.getInfo().getUniquename());
		this.convModel.setChannelid(this.infoModel.getInfo().getChannelid());		
		
		return this.convModel;
	}
	
	public CubeConvModel makeCubeConvModel(String question, String answer, String contextString) {
		this.convModel = new CubeConvModel();
		this.convModel.setUniquename(this.infoModel.getInfo().getUniquename());
		this.convModel.setChannelid(this.infoModel.getInfo().getChannelid());
		this.convModel.setQuestion(question);
		this.convModel.setAnswer(answer);
		this.convModel.setContext(contextString);		
		
		return this.convModel;
	}
	
	public void convertQuestionToAnswer(){
		this.infoModel.setRec(this.convModel.getAnswer());		
	}	
}
