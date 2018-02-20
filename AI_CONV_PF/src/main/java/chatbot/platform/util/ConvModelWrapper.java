package chatbot.platform.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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

import chatbot.platform.model.conv.ContextModel;
import chatbot.platform.model.conv.ConversationModel;
import chatbot.platform.model.conv.EntityModel;
import chatbot.platform.model.conv.InputModel;
import chatbot.platform.model.conv.IntentModel;
import chatbot.platform.model.conv.OutputModel;
import chatbot.platform.model.cube.CubeInfoModel;
import chatbot.platform.model.cube.InfoModel;

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
	
	public void setConversationModelByCubeInfo(CubeInfoModel ciModel) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException {		
		this.convModel.setInput(new InputModel().setText(ciModel.getInfo().getRec()));
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
		if (this.convModel.getIntents().size() == 0) return null;
		return ((IntentModel)this.convModel.getIntents().get(index)).getIntent();
	}
	
	public Double getIntentConfidence(int index){
		return ((IntentModel)this.convModel.getIntents().get(index)).getConfidence();
	}
	
	public EntityModel getEntityModel(int index){
		return this.convModel.getEntities().get(index);		
	}
	
	public String getEntity(int index){
		if (this.convModel.getEntities().size() == 0) return null;
		return ((EntityModel)this.convModel.getEntities().get(index)).getEntity();
	}
	
	public String getEntityValue(int index){
		return ((EntityModel)this.convModel.getEntities().get(index)).getValue();
	}	
	
	public Object getCustomContext(String key){
		if (this.convModel.getContext().getCustom_context() == null){
			return new String();
		}
		return this.convModel.getContext().getCustom_context().get(key);
	}
	
	public void setCustomContext(Map<String, Object> customContext){
		ContextModel cm = this.convModel.getContext();
		Map<String, Object> m = cm.getCustom_context();
		m.putAll(customContext);
		cm.setCustom_context(m);
		this.convModel.setContext(cm);;
	}
	
	public void setCustomContext(String str, Object obj){
		ContextModel cm = this.convModel.getContext();
		Map<String, Object> m = new HashMap<String, Object>();
		if (cm.getCustom_context() != null){
			m = cm.getCustom_context();
		}
		m.put(str, obj);
		cm.setCustom_context(m);
		this.convModel.setContext(cm);
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
	
	public void setOutputText(String str){
		OutputModel om = this.convModel.getOutput();
		List<String> list = new ArrayList<String>();
		list.add(str);
		this.convModel.setOutput(om.setText(list));
	}
	
	public boolean getBranchExited(){
		return this.convModel.getContext().getSystem().isBranch_exited();
	}

	public void clearCustomContext() {
		ContextModel cm = this.convModel.getContext();
		cm.setCustom_context(null);
		cm.setSlot_val1(null);
		cm.setSlot_val2(null);
		cm.setSlot_val3(null);
		cm.setSlot_val4(null);
		cm.setSlot_val5(null);
		cm.setSlot_val6(null);
		cm.setSlot_val7(null);
		cm.setSlot_val8(null);
		cm.setSlot_val9(null);
		this.convModel.setContext(this.convModel.getContext().setCustom_context(null));		
	}
}