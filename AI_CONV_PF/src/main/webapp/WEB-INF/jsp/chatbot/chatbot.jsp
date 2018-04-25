<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page buffer="12kb" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<title>SK Hynix - Aibril with Watson</title>
<meta http-equiv="Content-Type" content="txt/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/>
<!-- jQuery -->
<c:set var="locale" value="${sessionScope['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE']}"/>
<%@include file="../common/common.jsp" %>
<style type="text/css">

@CUSTOM-FONT-GODOMAUM {
	font-family: "godomaum";
	src:url("../../resources/fonts/godoMaum.ttf");
}
.header-wrap{
	height: 100px;
}

.header{
    position: relative;
    margin: 0 auto;
    width: 805px;
    height: 100%;
}

.ci-area{
	top: 15px;
	position: absolute;
}

.search-wrap{
	position: absolute;
	top: 40px;
	right: 0px;
}

.search-type-area{
    position: absolute;
    left: 220px;
    top: 15px;
}

.nav-wrap{
	border-top: 1px solid #d9d9d9;
	border-bottom: 1px solid #d9d9d9;
}

.main-nav{
	width: 805px;
	margin: 0 auto;
}

.nav-tabs{
	border:none;
	width:805px;
}

.nav-tabs>li{
	margin-bottom: 0px;
	border-left: 1px solid #d9d9d9;
}

.nav-tabs>li>a{
	color:black;
	border-radius:0px;
	width:100%;
	text-align: center;
	font-weight: bold;
	padding: 7px 71.5px;
}

.dropdown-menu>li>a:hover{
	background-color: #1897d4;
	color: white;
}

/* 김대연 CSS 작업본 시작 */

.result-wrap{
	height:100%;
}

.result-list{
    height: 100%;
    background-color: #fff;
    width: 805px;
    margin: 0px auto;
}

.result-item{
	height:185px;
}

.title-wrap{
	position: relative;
}

.title-index{
	position: absolute;
	background-color: #ED145B;
	color: white;
	font-weight: bold;
	height:20px;
	width:60px;
	text-align:center;
	line-height:20px;
}

.title-index:after{
	position: absolute;
    border-top: 20px solid #ED145B;
    border-right: 20px solid transparent;
    content: '';
    left:100%;
}

.report-title{
	position: absolute;
    left: 10%;
    line-height: 20px;
    font-weight: bold;
}

.dotdotdot{
	white-space: nowrap; 
    max-width: 500px;
    overflow: hidden;
    text-overflow: ellipsis;
    display:inline-block;
}

.decimal{
	font-weight: bold;
	vertical-align: top;
	color: #1c9ad6;
}

.type-index{
    position: absolute;
    color: white;
    font-weight: bold;
    height: 18px;
    width: 70px;
    text-align: center;
    line-height: 18px;
    right: 0;
    font-size: 11px;
}

.type-100{
	background-color: red;
}

.type-200{
	background-color: green;
}

.type-500{
	background-color: blue;
}

.content-wrap{
	position: relative;
	top: 17%;
}

.report-cont{
	position: absolute;
    left: 0;
    line-height: 15px;
    overflow: auto;
    height:100px;
}

.meta-wrap{
	position: relative;
	top:75%;
}

.project-index{
	position: absolute;
    background-color: #aaaaaa;
    color: white;
    font-weight: bold;
    height: 16px;
    width: 82px;
    text-align: center;
    line-height: 15px;
    font-size: 10px;
}

.project-cont{
	position: absolute;
	left:11%;
}

.user-index{
	position: absolute;
    left: 41%;
    background-color: #aaaaaa;
    color: white;
    font-weight: bold;
    height: 16px;
    width: 42px;
    text-align: center;
    line-height: 15px;
    font-size: 10px;
}

.user-cont{
	position: absolute;
	left: 47%;
}

.date-index{
	position: absolute;
 	background-color: #aaaaaa;
    color: white;
    font-weight: bold;
    height: 16px;
    width: 42px;
    text-align: center;
    line-height: 15px;
    font-size: 10px;
    left:80%;
}

.date-cont{
	position: absolute;
	right: 0;
}

.attach-wrap{
	position: relative;
	top:92%;
}

.separator{
	margin:20px 0px;
	border:0.5px solid #cbcbcb;
}

.high-light{
	background-color: yellow;
}

/* 김대연 CSS 작업본 종료 */


</style>
</head>
<body style="background-color:#fefefe; overflow-y: scroll; min-width: 1280px;">

    <!-- 컨텐츠 영역 -->
<!--     <a id="btnChatbot" style="position:fixed; right:20px; bottom:10px; cursor:pointer;" onclick="openChatbot()">
		<img id="btnChatbotImage" src="../../resources/images/btn_chatbot_on.png" />
	</a> -->

    <div id="chatbot-box" style="height:90%;"></div>
			     <div id="section-box" style="height:90%;">
			     	<div><input id="ETC" type="checkbox"  disabled>기타</input></div>
			     	<div><input id="PC" type="checkbox" disabled>PC</input></div>
			     	<div><input id="OS" type="checkbox" disabled>OS</input></div>
			     	<div><input id="DRM" type="checkbox" disabled>DRM</input></div>
			     	<div><input id="SKYNET" type="checkbox" disabled>SKYNET</input></div>
</div>
</body>
<script>

//chatbotFlag 초기값 "I"할당
var chatbotFlag = "I";

// RNR 결과 Json Array 전역변수 선언
var rnrSearchResultInt = [];
var rnrSearchResult100 = [];
var rnrSearchResult200 = [];
var rnrSearchResult500 = [];

// tabIndex 전역변수 선언
var tabIndex = "int";


/* 헤더 스크립트 시작*/
function turnBorder(){
	$('.search-area-header').css('border','2px solid #1897d4');
}

function returnBorder(){
	$('.search-area-header').css('border','2px solid #d5d5d5');
}
/* 헤더 스크립트 끝*/

$(document).ready(function(){
	$('#btnChatbotImage').prop("src","../../resources/images/btn_chatbot_off.png");
	$('#chatbot-box').show();
	$('#chatbot-box').empty();
	
	$('#chatbot-box').OEngine({
		width   :  '100%',
		height  :  '560px',
		bgColor :  '#fff',
		//ctitle  :  "Aibril Chatbot",
		firstMsg : "안녕하세요. chatbot Platform 입니다.",
		service_id : "aaae1dc4-8bc4-4845-a25c-a28008854997"
	});
	
 	//사용 시 텍스트 스크랩 되도록 수정. 
		$( "#chatbot-box" ).draggable({
			'cancel':'.input-box, .conversation',
			'containment':'parent'
		});
})

function process(result) {
	var s = ""; //초기화
	var intent = "intent가 존재하지 않습니다.";
	var confidence ="0"; 

	//무응답 처리
	if (result.output.text.length == 0) {
		s = "대화모델에 해당 입력에 대한 응답이 없습니다. 대화 모델을 확인 해 주세요."
	} else {
		if (result.output.type == "Link") { //DB 또는 API 연동시
			for (var i = 0; i < result.output.text[0].result.length; i++) {
				s += "<img src='" + result.output.text[0].result[i] + "' width='50' height='30'>"
			}
		} else { //일반 대화시
			for ( var key in result.output.text) {
				s += result.output.text[key];
				//s += "<br />";
			}
		}
		
/* 		var debugCheck = $("input:checkbox[id='debugging']").is(":checked");
		if(debugCheck){
			if(result.intents.length > 0){
				intent = result.intents[0].intent;
				confidence = result.intents[0].confidence;
				
				s += "<br /> intent : " + result.intents[0].intent;
				s +=  "<br /> confidence : " + result.intents[0].confidence; 
			}else{
				s += "<br /> intent : " + intent;
				s +=  "<br /> confidence : " + confidence;
			}
		} */

	}

	var time = new Date(Date.now());
	var option = {
		hour : "2-digit",
		minute : "2-digit"
	};
	var timeStamp = time.toLocaleTimeString("en-US", option);

// 	 $(".conversation")
// 			.append(
// 					"<div class='talk_isac'><div class='talk_isac_box' >"
// 							+ s
// 							+ "</div><div class='talk_isac_time'>"
// 							+ timeStamp
//  							+ "</div><div id='box2-1'></div></div>")
// 							+ "</div><div style='display:inline'><img  src='../../resources/images/icon/ideam.png' style='position:absolute; width:45px; height:45px; margin:15px 0px 0px 10px'></div></div>)";
							

// 	$(".conversation").append("<div class='clear'></div>"); 
	$('.conversation').append('<div class="aibril"><div class="box"><div>'+s+'</div></div></div>')
	$(".conversation").append("<div class='clear'></div>");
}

function imgOpenPop(address){
	var imgAddress = address.split("JPG");
	var imgCount = imgAddress[1].split("┌");
	url = "<%=request.getContextPath()%>/resources/imagePop/chatbotImagePop.jsp";
//		if(imgCount[1] > 1){
//		window.open(url , "imgPop┌"+address , "width=1039 , height =820");
//	}else{
    let temp = "imgPop";	    
	window.open(url , temp + "┌" + address , "width=1100 , height =780");
//	}
}

/* function openChatbot(){
	
	if(chatbotFlag == "I"){
		chatbotFlag = "Y";
		$('#btnChatbotImage').prop("src","../../resources/images/btn_chatbot_off.png");
		$('#chatbot-box').show();
		$('#chatbot-box').empty();
		
		$('#chatbot-box').OEngine({
			//width   :  '100%',
			//height  :  '560px',
			bgColor :  '#fff',
			//ctitle  :  "Aibril Chatbot",
			firstMsg : "안녕하세요. chatbot Platform 입니다.",
			service_id : "aaae1dc4-8bc4-4845-a25c-a28008854997"
		});
		
	 	//사용 시 텍스트 스크랩 되도록 수정. 
 		$( "#chatbot-box" ).draggable({
 			'cancel':'.input-box, .conversation',
 			'containment':'parent'
 		});
		return;
	}
	
	if(chatbotFlag == "N"){
		chatbotFlag = "Y";
		$('#btnChatbotImage').prop("src","../../resources/images/btn_chatbot_off.png");
		$('#chatbot-box').show();
	}else if(chatbotFlag == "Y"){
		chatbotFlag = "N"
		$('#btnChatbotImage').prop("src","../../resources/images/btn_chatbot_on.png");
		$('#chatbot-box').hide();
	}
}

function closeChatbot(){
	chatbotFlag = "N";
	$('#btnChatbotImage').prop("src","../../resources/images/btn_chatbot_on.png");
	$('#chatbot-box').hide();
} */

/* function keyPress(event, queryType){
	if(event.keyCode == 13){
		if(queryType === "main"){
			searchRnrResult("main");
		}else if(queryType === "header"){
			searchRnrResult("header");
		}
	}	
} */

function dateFormatter(beforeDate){
	var year = beforeDate.substring(0, 4);
	var month = beforeDate.substring(4, 6);
	var day = beforeDate.substring(6, 8);
	var hour = beforeDate.substring(8, 10);
	var minute = beforeDate.substring(10, 12);
	var second = beforeDate.substring(12, 14);
	
	var afterDate = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	return afterDate;
}

</script>
</html>