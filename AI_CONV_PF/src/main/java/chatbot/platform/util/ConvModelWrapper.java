package chatbot.platform.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;

import chatbot.platform.model.ContextModel;
import chatbot.platform.model.ConversationModel;
import chatbot.platform.model.ConversationSimpleModel;
import chatbot.platform.model.EntityModel;
import chatbot.platform.model.InputModel;
import chatbot.platform.model.IntentModel;

public class ConvModelWrapper {
	
	private Logger logger;	
	private ConversationModel convModel;	
	private ObjectMapper mapper;
	
	public ConvModelWrapper() throws Exception{
		this.logger = LoggerFactory.getLogger(this.getClass());	
		this.mapper = new ObjectMapper();
		this.convModel = new ConversationModel();
		this.convModel.setContext(new ContextModel().setTimezone("Asia/Seoul"));
	}
	
	public ConvModelWrapper(String str) throws Exception {
		this.logger = LoggerFactory.getLogger(this.getClass());	
		this.mapper = new ObjectMapper();
		this.convModel = new ConversationModel();
		this.setConversationModelByString(str);
		if (this.convModel.getContext() == null){
			this.convModel.setContext(new ContextModel().setTimezone("Asia/Seoul"));
		}else if (convModel.getContext().getTimezone() == null){
			this.convModel.setContext(this.convModel.getContext().setTimezone("Asia/Seoul"));
		}
	}
	
	public ConvModelWrapper(String text, String cntx) throws Exception {
		this.logger = LoggerFactory.getLogger(this.getClass());	
		this.mapper = new ObjectMapper();
		this.convModel = new ConversationModel();
		this.setConversationSource(text, cntx);
		if (this.convModel.getContext() == null){
			this.convModel.setContext(new ContextModel().setTimezone("Asia/Seoul"));
		}else if (this.convModel.getContext().getTimezone() == null){
			this.convModel.setContext(this.convModel.getContext().setTimezone("Asia/Seoul"));
		}
	}
	
	@Override
	public String toString() {
		try {
			return this.getConversationModelString();
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
			return "{}";
		}
	}

	public void setConversationModelByString(String str) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException {		
		if (str != null){			
			convModel = mapper.readValue(str.getBytes("UTF-8"), ConversationModel.class);
		}
		
		if (logger.isDebugEnabled()){
			logger.debug("★★★★★★★★★★★★ ConvModelWrapper created : " + this.mapper.writeValueAsString(this.convModel));
		}
	}
	
	public void setConversationSource(String text, String cntx) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException{
		this.convModel.setInput(new InputModel().setText(text));
		
		if (cntx != null){
			ContextModel context = new ContextModel();
			context = mapper.readValue(cntx.getBytes("UTF-8"), ContextModel.class);
		
			this.convModel.setContext(context);		
		}
	}
	
	public ConversationModel getConversationModel(){
		return this.convModel;
	}
	
	public ConversationSimpleModel getConversationSimpleModel(){
		ConversationSimpleModel simpleModel = new ConversationSimpleModel();
		if (this.convModel.getInput() != null){
			simpleModel.setInputText(this.convModel.getInput().getText());
		}
		
		if (this.convModel.getOutput() != null){
			Iterator<String> it = this.convModel.getOutput().getText().iterator();		
			StringBuffer outputs = new StringBuffer();
			while(it.hasNext()){
				outputs.append(it.next());
				if(it.hasNext()){
					outputs.append("<br/>");
				}
			}
			simpleModel.setOutputText(outputs.toString());		
		}
		
		simpleModel.setContext(this.convModel.getContext());
		
		return simpleModel;
	}
	
	public void setConversationModelBySimpleString(String str) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException{
		ConversationSimpleModel simpleModel = new ConversationSimpleModel();
		simpleModel = mapper.readValue(str.getBytes("UTF-8"), ConversationSimpleModel.class);
		
		this.convModel.setInput(new InputModel().setText(simpleModel.getInputText()));
		this.convModel.setContext(simpleModel.getContext());
	}
	
	public String getConversationSimpleModelString() throws JsonProcessingException{
		return this.mapper.writeValueAsString(this.getConversationSimpleModel());		
	}
	
	public String getConversationModelString() throws JsonProcessingException{
		return this.mapper.writeValueAsString(this.convModel);		
	}
	
	public void setContextByString(String str) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException{
		ContextModel context = new ContextModel();
		context = mapper.readValue(str.getBytes("UTF-8"), ContextModel.class);
		
		this.convModel.setContext(context);
	}
		
	public void setTimezone(String str){
		this.convModel.setContext(this.convModel.getContext().setTimezone(str));
	}
	
	public String getInputText(){
		return this.convModel.getInput().getText();
	}
	
	public String getContextString() throws JsonProcessingException{
		return this.mapper.writeValueAsString(this.getConversationModel().getContext());
	}
	
	public String getWorkspace(){
		return this.convModel.getContext().getWorkspace();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Context getContext() throws JsonParseException, JsonMappingException, IOException{
		String contextStr = this.getContextString();
		Context context = new Context();
		if (contextStr != null){			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			
			HashMap hMap = (HashMap) new ObjectMapper().readValue(contextStr, Map.class);
			context.putAll(hMap);
		}
		return context;
		
	}
	
	public List<IntentModel> getIntents(){
		return this.convModel.getIntents();
	}
	
	public List<EntityModel> getEntities(){
		return this.convModel.getEntities();
	}
	
	public IntentModel getIntentModel(int index){
		return this.convModel.getIntents().get(index);
	}
	
	public String getIntent(int index){
		return ((IntentModel)this.convModel.getIntents().get(index)).getIntent();
	}
	
	public Double getIntentConfidence(int index){
		return ((IntentModel)this.convModel.getIntents().get(index)).getConfidence();
	}
	
	public EntityModel getEntityModel(int index){
		return this.convModel.getEntities().get(index);		
	}
	
	public String getEntity(int index){
		return ((EntityModel)this.convModel.getEntities().get(index)).getEntity();
	}
	
	public String getEntityValue(int index){
		return ((EntityModel)this.convModel.getEntities().get(index)).getValue();
	}	
	
	public Object getCustomContext(String key){
		return this.convModel.getContext().getCustom_context().get(key);
	}
	
	public void setCustomContext(Map<String, Object> customContext){
		ContextModel cm = this.convModel.getContext();
		Map<String, Object> m = cm.getCustom_context();
		m.putAll(customContext);
		cm.setCustom_context(m);
		this.convModel.setContext(cm);;
	}
	
	public int getEntitySize(){
		return this.convModel.getEntities().size();
	}
	
	public int getIntentSize(){
		return this.convModel.getIntents().size();
	}
	
	public Double getEntityLocation(int index, int location){
		return ((EntityModel)this.convModel.getEntities().get(index)).getLocation().get(location);
	}
}