package chatbot.platform.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.internal.LinkedTreeMap;
import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import chatbot.common.util.MorphologicalAnalysis;
import chatbot.conversation.dao.ChatbotDAO;

@Component
public class ConvUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ConvUtil.class);
	
	private static String AIBRIL_ACCOUNT_USER;
	private static String AIBRIL_ACCOUNT_PWD;
	public static String CONVERSATION_WORKSPACE_COMMON;
	public static String CONVERSATION_WORKSPACE_TASK;

	@Value("${aibril.credential.user}")
	private void setAibrilAccountUser(String str){ConvUtil.AIBRIL_ACCOUNT_USER = str;}
	
	@Value("${aibril.credential.pwd}")
	private void setAibrilAccountPwd(String str){ConvUtil.AIBRIL_ACCOUNT_PWD = str;}
	
	@Value("${aibril.workspace.common}")
	private void setConversationWorkspaceCommon(String str){ConvUtil.CONVERSATION_WORKSPACE_COMMON = str;}
	
	@Value("${aibril.workspace.task}")
	private void setConversationWorkspaceTask(String str){ConvUtil.CONVERSATION_WORKSPACE_TASK = str;}	

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@SuppressWarnings({ "rawtypes" })
	public static ConvModelWrapper sendText(ConvModelWrapper conv) throws Exception{
		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword(ConvUtil.AIBRIL_ACCOUNT_USER, ConvUtil.AIBRIL_ACCOUNT_PWD);

		String[] workspaceId ={ ConvUtil.CONVERSATION_WORKSPACE_TASK, ConvUtil.CONVERSATION_WORKSPACE_COMMON };
		
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ sendText 1 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(conv.getConversationModelString());
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ sendText 1 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}
		
		InputData input = new InputData.Builder(conv.getInputText()).build();		
		Context context = new Context();
		
		MessageResponse aibrilResponse = new MessageResponse();
		
		try {
			context = conv.getContext();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//workspace 하나로 쓰기
//		MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
//		MessageResponse response = service.message(options).execute();
//		
//		aibrilResponse = response;
		
		//1. task workspace에서 intents 찾기
		//2.업무 workspace 에서 없으면 공통 workspace에서 intents 찾기
		for (int i=0 ; i<workspaceId.length ; i++){
		
			context.put("workspace", workspaceId[i]);
			
			MessageOptions options = new MessageOptions.Builder(workspaceId[i]).input(input).context(context).build();
			MessageResponse response = service.message(options).execute();
			
			//||  Integer.parseInt(((HashMap)context.get("system")).get("dialog_turn_counter").toString()) > 2
			if(((ArrayList)response.get("intents")).size() > 0){//workspace를 돌다가 intents를 찾으면 break;
				aibrilResponse = response;
				break;
			}
	
//일상 용어 버전			
			else{
				 if(ConvUtil.CONVERSATION_WORKSPACE_COMMON.equals((context.get("workspace")))){//반복 시나리오에서 응답하는말(숫자, 용어)은 intents가 존재 x, 그래서 null이 return 되는걸 막기위해
					 aibrilResponse = response;
				 }
			}

			
//업무 포함			
//			else if(context.get("intentPlace").equals("task")){  //intent 없으면 이전 대화 intent 따르도록
//					if("f64cb648-43be-49d9-8074-883a0e71ff99".equals((context.get("workspace")))){
//						aibrilResponse = response;
//					}
//			}else if(context.get("intentPlace").equals("common")){ //intent 없으면 이전 대화 intent 따르도록
//					if("f0ec5991-0680-4226-ab45-a869a2fff06b".equals((context.get("workspace")))){
//						aibrilResponse = response;
//					}
//			}else { //intent 없으면 공통 workspace 처리 
//					if("f0ec5991-0680-4226-ab45-a869a2fff06b".equals((context.get("workspace")))){
//						aibrilResponse = response;
//					}
//			}
		}
			
		ConvModelWrapper wrapper = new ConvModelWrapper(JSONObject.toJSONString(aibrilResponse));
				
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ sendText 2 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(wrapper.getConversationModelString());
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ sendText 2 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}		
				
		return wrapper;
	}	
	
	//형태소 분석기
	@SuppressWarnings("rawtypes")
	public static Map findTermsDetailInText(String msg, ChatbotDAO chatbotDAO){
		//질문에 은,는,? 있으면 제거
		if (msg.endsWith("는?") || msg.endsWith("은?")){
			msg = msg.substring(0, msg.length()-2);
		}else if (msg.endsWith("?")){
			msg = msg.substring(0, msg.length()-1);
		}
		//형태소 분석기로 entities 파악
		HashMap terms = (HashMap) MorphologicalAnalysis.mAnalysis(msg, chatbotDAO);		
		
		return terms;
		
	}
	
    // Map ->  JsonString 
    public static String mapToJsonString( Map<String, Object> map ) {
    	return JSONObject.toJSONString(map);
    }
    
    // LinkedTreeMap ->  JsonString 
    @SuppressWarnings("rawtypes")
	public static String lMapToJsonString( LinkedTreeMap map ) {
    	return JSONObject.toJSONString(map);
    }
        
	//List -> JsonString
	public static String listToJsonString(List<Object> list){
		return JSONArray.toJSONString(list);
	}
	
	//JsonString -> Map
	@SuppressWarnings({ "unchecked" })
	public static Map<String, Object> jsonStringToMap(String jsonString) throws JsonParseException, JsonMappingException, IOException{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		
		resultMap = new ObjectMapper().readValue(jsonString, Map.class) ;
		return resultMap;
	}
	
	//JsonString -> List
	@SuppressWarnings("unchecked")
	public static List<Object> jsonStringToList(String jsonString) throws JsonParseException, JsonMappingException, IOException{
		List<Object> resultList = new ArrayList<>();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		
		resultList =  new ObjectMapper().readValue(jsonString, List.class) ;
		return resultList;
	}
}
