package chatbot.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chatbot.conversation.dao.ChatbotDAO;

public class MorphologicalAnalysis {
	
	private static Logger logger = LoggerFactory.getLogger(MorphologicalAnalysis.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map mAnalysis(String str, ChatbotDAO chatbotDAO) {
		
		Map resultMap = new HashMap();
		List resultList = new ArrayList();
		Vector mv = new Vector();
		Vector<Integer> endPoses = new Vector();
	    int termStartPos = 0;
		String term = str;
		List<LNode> nodes = Analyzer.parseJava(str);
		int pos = 0;
		boolean breakPoint = false;
		
		for (LNode node : nodes) {
			
			if (logger.isDebugEnabled()){
	            logger.debug("※※※※※※※※※※※※※※※※※※※");
	            logger.debug(node.toString());
	            logger.debug(String.valueOf(node.startPos()));	//시작위치
	            logger.debug(String.valueOf(node.morpheme().cost()));
	            logger.debug(node.morpheme().key());
	            logger.debug(String.valueOf(node.morpheme().productArity()));
	            logger.debug(node.morpheme().productPrefix());
	            logger.debug(node.morpheme().surface());	//해당 글자
	            logger.debug(String.valueOf(node.morpheme().feature()));
	            logger.debug(node.morpheme().feature().apply(0));	//품사
	            logger.debug(node.morpheme().feature().toVector().getElem(0, 0));	//이것도 품사
	            logger.debug(String.valueOf(node.morpheme().leftId()));
	            logger.debug(node.morpheme().mType().toString());
	            logger.debug(node.morpheme().poses().toString());
	            logger.debug(node.morpheme().productIterator().toString());
	            logger.debug(String.valueOf(node.morpheme().rightId()));
	            logger.debug("※※※※※※※※※※※※※※※※※※※");
			}
			
            if (((String)node.morpheme().feature().apply(0)).matches("MM|SL|SN|SH|NNG|NNP|NNB|NR|MAG")){
            	mv.add(node.morpheme().surface());            	
            }
            
            //필요없는 문장 제거용
            if (((String)node.morpheme().feature().apply(0)).matches("IC|MAG|VV|EC") || ("에서".equals(node.morpheme().surface())) && "JKB".equals(node.morpheme().feature().apply(0))){
            	endPoses.add(node.endPos());
            }
            
            if (("이".equals(node.morpheme().surface()) || "가".equals(node.morpheme().surface())) && "JKS".equals(node.morpheme().feature().apply(0))){
            	if (!breakPoint) {
            		term = str.substring(0, node.startPos());
            		termStartPos = node.startPos();
            	}
            	breakPoint = true;
            }else if (("은".equals(node.morpheme().surface()) || "는".equals(node.morpheme().surface()) || "란".equals(node.morpheme().surface()) ||"이란".equals(node.morpheme().surface())) && "JX".equals(node.morpheme().feature().apply(0))){
            	if (!breakPoint) {
            		term = str.substring(0, node.startPos());
            		termStartPos = node.startPos();
            	}
            	breakPoint = true;
            }else if (("은".equals(node.morpheme().surface()) || "는".equals(node.morpheme().surface()) || "란".equals(node.morpheme().surface()) ||"이란".equals(node.morpheme().surface())) && "NNG".equals(node.morpheme().feature().apply(0))){
            	if (!breakPoint) {
            		term = str.substring(0, node.startPos()-1);
            		termStartPos = node.startPos();
            	}
            	breakPoint = true;
            }else if (("에".equals(node.morpheme().surface())) && "JKB".equals(node.morpheme().feature().apply(0))){
            	if (!breakPoint) {
            		term = str.substring(0, node.startPos());
            		termStartPos = node.startPos();
            	}
            	breakPoint = true;
            }else if (("의".equals(node.morpheme().surface())) && "JKG".equals(node.morpheme().feature().apply(0))){
            	if (nodes.size() > pos+1){
	            	LNode nextNode = nodes.get(pos+1);
	            	if ("NNG".equals(nextNode.morpheme().feature().apply(0))){
	            		if (!breakPoint) {
	            			term = str.substring(0, node.startPos());
	            			termStartPos = node.startPos();
	            		}
	            		breakPoint = true;
	            	}
            	}
            }else if (("을".equals(node.morpheme().surface()) || "를".equals(node.morpheme().surface())) && "JKO".equals(node.morpheme().feature().apply(0))){
            	if (!breakPoint) {
            		term = str.substring(0, node.startPos());
            		termStartPos = node.startPos();
            	}
            	breakPoint = true;
            }else if (("이".equals(node.morpheme().surface())) && "VCP".equals(node.morpheme().feature().apply(0))){
            	if (nodes.size() > pos+1){
	            	LNode nextNode = nodes.get(pos+1);
	            	if ("ETM".equals(nextNode.morpheme().feature().apply(0))){
	            		if (!breakPoint) {
	            			term = str.substring(0, node.startPos());
	            			termStartPos = node.startPos();
	            		}
	            		breakPoint = true;
	            	}
            	}
            }else if (("라는".equals(node.morpheme().surface())) && "VCP+ETM".equals(node.morpheme().feature().apply(0))){
            	if (!breakPoint) {
            		term = str.substring(0, node.startPos());
            		termStartPos = node.startPos();
            	}
            	breakPoint = true;
            }else if ("NNG".equals(node.morpheme().feature().apply(0))){
            	if (nodes.size() > pos+1){
	            	LNode nextNode = nodes.get(pos+1);
	            	if ("XSV+EC".equals(nextNode.morpheme().feature().apply(0))){
	            		if (!breakPoint) {
	            			term = str.substring(0, node.startPos());
	            			termStartPos = node.startPos();
	            		}
	            		breakPoint = true;
	            	}else if ("뜻".equals(node.morpheme().surface()) || "의미".equals(node.morpheme().surface())){
	            		if (!breakPoint) {
	                		term = str.substring(0, node.startPos());
	                		termStartPos = node.startPos();
	                	}
	                	breakPoint = true;
	            	}
            	}
            }else if ("VV+EC+VX+EC".equals(node.morpheme().feature().apply(0))){        	
        		if (!breakPoint) {
        			term = str.substring(0, node.startPos());
        			termStartPos = node.startPos();
        		}
            }else if ("NP".equals(node.morpheme().feature().apply(0))){
            	if (nodes.size() > pos+1){
	            	LNode nextNode = nodes.get(pos+1);
	            	if ("VCP+EF".equals(nextNode.morpheme().feature().apply(0))){
	            		if (!breakPoint) {
	            			term = str.substring(0, node.startPos());
	            			termStartPos = node.startPos();
	            		}
	            		breakPoint = true;
	            	}
            	}
            }
            
            pos++;
        }		
		
		int maxEndPos = 0;
		
		for( int ePos:endPoses){
			if (termStartPos > ePos && maxEndPos < ePos){
				maxEndPos = ePos;
			}			
		}
		term = term.substring(maxEndPos, term.length());
		
		if (term != null && !"".equals(term)){
			resultList = chatbotDAO.checkWordList(term);
		}
		
		resultMap.put("Term", resultList);
		resultMap.put("Nouns", mv);
		
		return resultMap;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Vector mAnalysisPublic(String str) {
		Vector result = new Vector();
		List<LNode> nodes = Analyzer.parseJava(str);
		
		for (LNode node : nodes) {		
            if (((String)node.morpheme().feature().apply(0)).matches("MM|SL|SN|SH|NNG|NNP|NNB|NR|MAG")){
            	result.add(node.morpheme().surface()); 	
            }
        }	
		
		return result;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static String findJKS(Object strHashMap, int type) {
		HashMap strMap = (HashMap)strHashMap;
		String strS = strMap.get("SHORTCUT").toString();
		String strF = strMap.get("FULLNAME").toString();
		
		String strType = "H";
		String JKS = "";
		
		String[] rCho = { "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ" };
		String[] rJung = { "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ" };
		String[] rJong = { "", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ" };
		
		char sTest = strS.charAt(strS.length() - 1)==')'?strS.charAt(strS.length() - 2):strS.charAt(strS.length() - 1);
		if (((int)sTest >=65 && (int)sTest <=90) || ((int)sTest >=97 && (int)sTest <=122)) strType = "E";
		if ((int)sTest >=48 && (int)sTest <=57) strType = "N";
		
		int nTmp = (sTest - 0xAC00);
		int jong = nTmp % 28; // 종성
		int jung = ((nTmp - jong) / 28) % 21; // 중성
		int cho = (((nTmp - jong) / 28) - jung) / 21; // 초성

		logger.debug("초성:" + rCho[cho] + "\n" + "중성:" + rJung[jung] + "\n" + "종성:" + rJong[jong]);
		
		if ("H".equals(strType)) {
			JKS = switchJKS(type, rJong[jong].isEmpty());
		}else if ("E".equals(strType)) {
			// JKS = switchJKS(type, String.valueOf(sTest).toLowerCase().matches("^[adefghijopqrstuvwxyz]?$"));
			// spelling base
			JKS = switchJKS(type, String.valueOf(sTest).toLowerCase().matches("^[abcdefghijkopqstuvwxyz]?$"));
		}else if ("N".equals(strType)) {
			JKS = switchJKS(type, String.valueOf(sTest).toLowerCase().matches("^[2459]?$"));
		}
		
		String returnStr = new String();
		if (strF == null || strF.isEmpty() || strS.equals(strF)){
			returnStr = strS+JKS;			
		}else{
			returnStr = strS+"("+strF+")"+JKS;
		}

		return returnStr;
	}
	
	private static String switchJKS(int type1, boolean type2) {
		if (type1 == 0) return "";
		if (type1 == 1 && type2) return "가 ";
		if (type1 == 1 && !type2) return "이 ";
		if (type1 == 2 && type2) return "는 ";
		if (type1 == 2 && !type2) return "은 ";
		if (type1 == 3 && type2) return "란 ";
		if (type1 == 3 && !type2) return "이란 ";
		return "";
	}
}
