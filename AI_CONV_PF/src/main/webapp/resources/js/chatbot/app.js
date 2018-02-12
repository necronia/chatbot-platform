/*
Title:		OEngine: a SK Conversation Chatbot Plugin
Author:		Choi jaecheol
Version:	0.1.0
Website:	
License: 	the MIT and GPL licenses.
*/
(function($) {
    
	//Extend the jQuery prototype
    $.fn.extend({
        OEngine: function(options) {
            if (options && typeof(options) == 'object') {
                options = $.extend( {}, $.OEngine.defaults, options );
            }
            this.each(function() {
                new $.OEngine(this, options);
            });
            return;
        }
    });
	
	
	$.OEngine = function(elem, options) {
		if (options == undefined) {
			//Set plugin defaults
			width   =  '25%';
			height  =  '100%';
			bgColor =  '#2e3951';
			ctitle  =  "Aibril Chatbot";
			firstMsg  =  "안녕하세요. Aibril Chatbot 입니다.";
			service_id    = "";
		}else{
			//Define plugin options
			width      = (options.width == undefined) ? '25%' :  options.width;
			height     = (options.height == undefined) ? '100%' :  options.height;
			bgColor    = (options.bgColor == undefined) ? '#2e3951' :  options.bgColor;
			ctitle	   = (options.ctitle == undefined) ? "Aibril Chatbot" : options.ctitle;
			firstMsg   = (options.firstMsg == undefined) ? '안녕하세요. Aibril Chatbot 입니다.' :  options.firstMsg;
			service_id    = (options.service_id == undefined) ? '' :  options.service_id;
		}
		
		$(elem).append('<div class="chatbot-inner">');
		$('.chatbot-inner').append('<div class="title-box"><span class="title">Aibril-Watson ChatBot</span><span class="debug">debug <input type="checkbox" id="debugging" name="debugging"></span><a class="chatbot-close" onclick="closeChatbot();"><img src="../../resources/images/icon/close_icon.png"></img></a></div>');
		$('.chatbot-inner').append('<div class="conversation-box"></div>');
		$('.conversation-box').append('<div class="conversation"></div>');
		//2th
			var time = new Date(Date.now());
        	var option = {hour : "2-digit", minute : "2-digit"};
			var timeStamp = time.toLocaleTimeString("en-US", option);
			
//			$('.conversation').append('<p class="aibril"><span class="box"><span>'+firstMsg+'</span></span><span class="conversationTime">'+timeStamp+'</span></p>');
			$('.conversation').append('<div class="aibril"><div class="box"><div>'+firstMsg+'</div></div></div>')
			
			/*$(".conversation").append("<div class='talk_isac'></div>");
			$(".talk_isac").append("<div class='talk_isac_box'>"+firstMsg+"</div>");
			$(".talk_isac").append("<div class='talk_isac_time'>"+timeStamp+"</div>");
			$(".talk_isac").append("<div><img  src='../../resources/images/icon/ideam.png' style=' position:absolute; width:45px; height:45px; margin:15px 0px 0px 10px'></div>");*/
			/*HHY $(".talk_isac").append("<div class='talk_isac_icon'><span class='prefix prefix_type_a'><span class='prefix_ico prefix_ico_bell'></span></span></div>");*/
			
			$(".conversation").append("<div class='clear'></div>");
			
			
		//3th div
		$('.chatbot-inner').append('<div class="input-box"></div>');
		$('.input-box').append('<input type="text" id="messageText" placeholder="무엇을 알려드릴까요?">');		
		$('.input-box').append('<a id="sendImg" style="border-radius:0%; background:#ffffff url(../../resources/images/icon/send_image.png) no-repeat; height: 25px;width: 25px;background-size:contain;"></a>');
		//$('.input-box').append('<a id="sendImg" style="border-radius:0%; background-image:url(../../resources/images/icon/icon_vr_image.png);"></a>');
		$('.input-box').append('<a id="sendMessage"></a>');

		//4th div (helpdesk 영역)
		//$(".chatbot-inner").append("<div id='helpdesk'></div>");
		//$("#helpdesk").attr('style','position:absolute; width:800px; height:900px; left:500px; top:5px;');
		
		//채팅부분.(글입력부)
		//$("#question_wrap").append("<input type='text' placeholder='질문을 입력하세요' id='messageText' style='width:86%'>");
		
		//------------------------------------------------------------------------------------------------------------------------
	
        $('#sendMessage').unbind('click').click(function(){
            fnSend(service_id);
        });
		
		$('#sendImg').unbind('click').click(function(){
			Storage.vrSearchByChatbotFlag = true;
			chooseFile();
        });
		
	}; //End of $.OEngine
	
	
	//글 입력시 이벤트.
	document.onkeypress  = function(e){ 
        //크로스 도메인 허용
        $.support.cors = true;
    
		var result = ""; 
		
		//크롬 계열
		if(typeof(e) != "undefined") {
			result = e.which;
		}else {
			result = event.keyCode;
		}
		
		if (result == 13){
			var focusId = $(':focus')[0].id;
			if(focusId == "messageText"){
				fnSend(service_id);
			}
		}

	} //End of document.onkeypress
	
})(jQuery);

var conText;

var todaysDate = new Date();

function convertDate(date) {
  var yyyy = date.getFullYear().toString();
  var mm = (date.getMonth()+1).toString();
  var dd  = date.getDate().toString();

  var mmChars = mm.split('');
  var ddChars = dd.split('');

  return yyyy + '-' + (mmChars[1]?mm:"0"+mmChars[0]) + '-' + (ddChars[1]?dd:"0"+ddChars[0]);
}


function fnSend(service_id){
	//console.log(service_id);
	var msg = $("#messageText").val();
	
	var data ={};
    //data.api_key = service_id;
    data.text = msg;
	data.context = JSON.stringify(conText);
	console.log(data)
	data = JSON.stringify(data);
	
	$.ajax({
//		url : '/chatbot/sendMessage.json',
		url : '/sendText.json',
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		mimeType: 'application/json',
		processData : false,
		data : data,
		cache : false,
		beforeSend:function(){
			//사용자 메시지 출력창.
			// timeStamp
			var time = new Date(Date.now());
			var option = {hour : "2-digit", minute : "2-digit"};
			var timeStamp = time.toLocaleTimeString("en-US", option);
//			$('.conversation').append('<p class="user"><span class="conversationTime">'+timeStamp+'</span><span class="box"><span>'+msg+'</span></span></p>');
			$('.conversation').append('<div class="user"><div class="box"><div>'+msg+'</div></div></div>');
//			$("#style-3").append("<div class='talk_user'><div class='talk_user_icon'><span class='glyphicon glyphicon-user' aria-hidden='true'></span></div><div class='talk_user_box'>"+msg+"</div><div class='talk_user_time'>"+timeStamp+"</div></div>");
			/*HHY $("#style-3").append("<div class='talk_user'><div class='talk_user_icon'><span class='prefix'><span class='prefix_ico2 prefix_ico_bell'></span></span></div><div class='talk_user_box'>"+msg+"</div><div class='talk_user_time'>"+timeStamp+"</div></div>");*/
//		    $("#style-3").append("<div class='clear'></div>");
			$(".conversation").append("<div class='clear'></div>");
			
            $("#messageText").val("");
            $('#chatbot-box').loading('start');  //loading start
        },
		success : function(response, status, xhr) {
			// Validation Message 초기화
			$('.error').text('');
			
			if(response.resultMessage == "FAIL") {
				var validError = response.errorMessageList;
				for (var i = 0; i < validError.length; i++) {
					$('#'+validError[i].field+'\\.error').text(validError[i].message);
				}
			} else {	
				console.log(response);
				var result = response.returnMessage;
//				console.log(result);
//				console.log(result.output.text[0]);
				
				//전역변수.(대화의 연속성을 위해서 필요)
				conText = result.context;
//				if(result.output.nodes_visited == "node_2_1510896663225"){
				if(result.output.nodes_visited == "node_11_1510721962719" && (JSON.stringify(result.intents) != "[]" && result.intents[0].intent == "search")){
					$("input:radio[value='rank']").prop('checked',true)
					$('#rnrQueryMain').val(result.input.text);
					searchRnrResult('main');
				}else if(result.output.nodes_visited == "node_1_1514356047175" && (JSON.stringify(result.intents) != "[]" && result.intents[0].intent == "search_image")){
					openImportImage('main');
				}
				process(result); //html 안에 평션있음.		
			}
		},
		complete:function(){
			$('#chatbot-box').loading('stop');  //loading stop
			$('.conversation').scrollTop($('.conversation')[0].scrollHeight);
			//$(".conversation").scrollTop($("#style-3")[0].scrollHeight - 430);  //scroll Y 재조정.  
        },
		error : function(xhr, status, error){
			$('#chatbot-box').loading('stop');  //loading stop
			alert('error' + JSON.stringify(xhr) );
		}
	});	
}

function searchVRcallback(defect1, defect2){
	if ($("#vrSearchResult").length == 1){
		$("#vrSearchResult").parent().html("이미지 검색결과<br/>1순위는 " + defect1 + ",<br/>2순위는 " + defect2 + " 입니다.");
	}else if (Storage.vrSearchByChatbotFlag){
		$('.conversation').append('<div class="aibril"><div class="box"><div>이미지 검색결과,<br>1순위는 '+defect1+',<br>2순위는 ' + defect2 + ' 입니다.</div></div></div>');
		Storage.vrSearchByChatbotFlag = false;
	}
}

function searchRNRcallback(title, link, confidc){
	if ($("#rnrSearchResult").length == 1){
		if (title == undefined){
			$("#rnrSearchResult").parent().html("검색된 결과가 없어요.");			
		}else{
			$("#rnrSearchResult").parent().html("1순위로 검색된 보고서의 제목은 <a href='" + link + "' target='blank'>[" + title + "]</a> 이고 신뢰도는 "+confidc+"% 입니다.");
		}
	}
}
