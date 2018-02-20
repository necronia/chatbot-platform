package chatbot.platform.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chatbot.conversation.dao.ChatbotDAO;
import chatbot.conversation.service.ChatbotService;
import chatbot.platform.model.conv.ConversationModel;
import chatbot.platform.model.conv.ConversationSimpleModel;
import chatbot.platform.util.ConvModelWrapper;

@RestController
@RequestMapping("/api")
public class RestAPIsController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ChatbotDAO chatbotDAO;
	
	@Autowired
	ChatbotService chatbotService;
	
	@RequestMapping("/getSimpleConversation")
	public ConversationSimpleModel getSimpleConversation(@RequestBody String str) throws Exception{
		
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getSimpleConversation 1 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(str);
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getSimpleConversation 1 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}		
		
		ConvModelWrapper conv = new ConvModelWrapper();
		conv.setConversationModelBySimpleString(str);
		conv = chatbotService.sendText(conv);
		
		// 질의내용 DB저장
		Map<String, String> qMap = new HashMap<String, String>();
		qMap.put("text", conv.getInputText());
		
    	//chatbotDAO.setCubeInfo(cm);
		
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getSimpleConversation 2 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(conv.getConversationSimpleModelString());
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getSimpleConversation 2 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}
		
		return conv.getConversationSimpleModel();
	}
	
	@RequestMapping("/getConversation")
	public ConversationModel getConversation(@RequestBody String str) throws Exception{
		
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getConversation 1 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(str);
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getConversation 1 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}		
		
		ConvModelWrapper conv = new ConvModelWrapper(str);
		conv = chatbotService.sendText(conv);
		
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getConversation 2 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(conv.getConversationModelString());
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getConversation 2 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}
		
		return conv.getConversationModel();
	}
}