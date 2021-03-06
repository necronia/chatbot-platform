package chatbot.platform.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chatbot.conversation.dao.ChatbotDAO;
import chatbot.conversation.service.ChatbotService;
import chatbot.platform.model.conv.ConversationModel;
import chatbot.platform.model.cube.CubeInfoModel;
import chatbot.platform.util.ConvModelWrapper;
import chatbot.platform.util.CubeModelWrapper;

@RestController
@RequestMapping("/api")
public class RestAPIsController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ChatbotDAO chatbotDAO;
	
	@Autowired
	ChatbotService chatbotService;
	
	@RequestMapping("/getSimpleConversation")
	public CubeInfoModel getSimpleConversation(@RequestBody CubeInfoModel cim) throws Exception{
		
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getSimpleConversation 1 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(cim.toString());
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getSimpleConversation 1 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}
		
		// CubeInfoModel로 Wrapper 생성
		CubeModelWrapper cube = new CubeModelWrapper(cim);
		cube.makeCubeConvModel();
		cube.setCubeConvModel(chatbotDAO.selectCubeInfo(cube.getCubeConvModel()));
				
		// 대화 호출
		ConvModelWrapper conv = new ConvModelWrapper();
		conv.setConversationModelByCube(cube.getCubeInfoModel(), cube.getCubeConvModel());
		conv = chatbotService.sendText(conv, "dummy");
		
		// 질의내용 DB저장
    	chatbotDAO.insertCubeInfo(cube.makeCubeConvModel(conv.getInputText(), (String)conv.getOutputText(), conv.getContextString()));
    	cube.convertQuestionToAnswer();
		    	
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getSimpleConversation 2 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(conv.getConversationModelString());
			logger.debug(cube.getCubeInfoModelString());
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getSimpleConversation 2 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}
		
		return cube.getCubeInfoModel();
	}
	
	@RequestMapping("/getConversation")
	public ConversationModel getConversation(@RequestBody String str) throws Exception{
		
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getConversation 1 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(str);
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getConversation 1 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}		
		
		ConvModelWrapper conv = new ConvModelWrapper(str);
		conv = chatbotService.sendText(conv, "dummy");
		
		if(logger.isDebugEnabled()){
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getConversation 2 S ☆☆☆☆☆☆☆☆☆☆☆☆");
			logger.debug(conv.getConversationModelString());
			logger.debug("☆☆☆☆☆☆☆☆☆☆☆☆ getConversation 2 E ☆☆☆☆☆☆☆☆☆☆☆☆");
		}
		
		return conv.getConversationModel();
	}
}