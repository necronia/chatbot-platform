package chatbot.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;

import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFConnectorShape;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class DocUtil {
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static LinkedHashMap extractScenario(String fileUrl){
		XMLSlideShow pptSlideShow  = null;
		boolean readyFlag = false;
		StringBuffer sb = new StringBuffer();
		LinkedHashMap scenarioMap = new LinkedHashMap();
		
		try {
			
			String url = fileUrl;
	    	String inputLine;
	    	 
        	if(!"noFile".equals(fileUrl)){//URL 링크에 파일 존재 
    	    	InputStream is = new URL(url.toString()).openStream();
    			pptSlideShow  = new XMLSlideShow(is);
    			for (XSLFSlide slide : pptSlideShow.getSlides()) {
    		        for (XSLFShape sh : slide.getShapes()) {
    		            // name of the shape
    		            String name = sh.getShapeName();

    		            // shapes's anchor which defines the position of this shape in the slide
    		            if (sh instanceof PlaceableShape) {
    		                java.awt.geom.Rectangle2D anchor = ((PlaceableShape)sh).getAnchor();
    		            }

    		            if (sh instanceof XSLFConnectorShape) {
    		                XSLFConnectorShape line = (XSLFConnectorShape) sh;
    		                System.out.println(line);
    		                // work with Line
    		            } else if (sh instanceof XSLFTextShape) {
    		            	XSLFTextShape shape = (XSLFTextShape) sh;
    		            	if (readyFlag){
    		            		sb.append(shape.getText());
    		            		break;
    		            	}
    		                //System.out.println(shape);
    		                //System.out.println(shape.getText());	//textbox의 text
    		                
    		                if ("#scenario".equals(shape.getText())) readyFlag = true;
    		                // work with a shape that can hold text
    		            } else if (sh instanceof XSLFPictureShape) {
    		                XSLFPictureShape shape = (XSLFPictureShape) sh;
    		                System.out.println(shape);
    		                // work with Picture
    		            } else if (sh instanceof XSLFTable){
    		            	XSLFTable table = (XSLFTable) sh;
    		            	if(readyFlag){
    		            		int colCnt = table.getNumberOfColumns();
    		            		int rowCnt = table.getNumberOfRows();
    		            		
    		            		StringBuffer lineNum = new StringBuffer();
    		            		StringBuffer lineText = new StringBuffer();    		            		

    		            		for (int i=0 ; i<rowCnt ; i++){
    		            			for (int j=0 ; j<colCnt ; j++){
    		            				XSLFTableCell cell = table.getCell(i, j);
    		            				String text = cell.getText();    		            				
    		            				
    		            				if (text.isEmpty()){
    		            					continue;
    		            				}else if(text.matches("[0-9.]+")){
    		            					lineNum.append(text);
    		            				}else{
    		            					lineText.append(text);
    		            					if(j != colCnt-1){
    		            						lineText.append("<br/>");
    		            					}
    		            				}
    		            				
    		            			}
    		            			scenarioMap.put(lineNum.toString(), lineText.toString());
    		            			lineNum.delete(0, lineNum.length());
    		    					lineText.delete(0, lineText.length());
    		            		}    		            		
    		            	}
    		            }
    		        }
    		        if(readyFlag) break;
    		    }
        	}else {
        		//URL 링크에 파일이 존재 X
        	}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*int line = 1;
		boolean numFlag = true;
		StringBuffer lineNum = new StringBuffer();
		StringBuffer lineText = new StringBuffer();
		LinkedHashMap scenarioMap = new LinkedHashMap();
		
		if(!"noFile".equals(fileUrl)){//URL 링크에 파일 존재
			for (int i=0 ; i<sb.length() ; i++){
				char temp = sb.charAt(i);
				if (numFlag && (String.valueOf(temp)).matches("[0-9.].*")){
					lineNum.append(temp);
				}else if (numFlag && (String.valueOf(temp)).equals(" ")){
					numFlag = false;
				}else if (!numFlag && (String.valueOf(temp)).equals("\n")){
					scenarioMap.put(lineNum.toString(), lineText.toString());
					lineNum.delete(0, lineNum.length());
					lineText.delete(0, lineText.length());
					numFlag = true;
				}else{
					lineText.append(temp);
				}
			}
			scenarioMap.put(lineNum.toString(), lineText.toString());
		}else{//URL 링크에 파일이 존재 X
			
		}*/
		System.out.println(scenarioMap);
		
		return scenarioMap;
	}
}
