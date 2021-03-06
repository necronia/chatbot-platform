/**
 * This is automatically generated by sprout plug-in.
 */
package chatbot.conversation.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import chatbot.platform.model.cube.CubeConvModel;

/**
 * 
 * @since 
 * @author Administrator
 */
@Repository("ChatbotDAO")
public class ChatbotDAO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	
	@SuppressWarnings("rawtypes")
	public ArrayList checkWordList(String value){
		return  (ArrayList) sqlSessionTemplate.selectList("Chatbot.checkWordList", value);
	}
	
	public int insertQuestion(Map<String, String> map) {
        return sqlSessionTemplate.insert("Chatbot.insertQuestion", map);
    }
	
	public String getWeatherCode(Map<String, Object> map){
		return  (String) sqlSessionTemplate.selectOne("Chatbot.getWeatherCode", map);
	}
	
	public int insertCubeInfo(CubeConvModel cm) {
		return sqlSessionTemplate.insert("Chatbot.insertCubeInfo", cm);
	}
	
	public CubeConvModel selectCubeInfo(CubeConvModel cm){
		return  (CubeConvModel)sqlSessionTemplate.selectOne("Chatbot.selectCubeInfo", cm);
	}
	
	public int insertConvInfo(Map<String, Object> map) {
        return sqlSessionTemplate.insert("Chatbot.insertConvInfo", map);
    }
	
	@SuppressWarnings("rawtypes")
	public  ArrayList checkConvSection (String userId){
		return (ArrayList)sqlSessionTemplate.selectList("Chatbot.checkConvSection", userId);
	}
}