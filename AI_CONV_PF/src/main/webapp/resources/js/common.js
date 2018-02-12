(function($){
	// to avoid crash caused by browser not support 'console' object;
	var log = (console && console.log) ? function(){console.log.apply(console, arguments);} : function(){};

	// start ajax plugin
	log('Setup default common ajax callback.');
	
	// ajax handlers
	function handleRedirect(event, jqXHR, ajaxOps, data){
		log('[' + event.type + ']ajax.handleRedirect(), ', arguments);
		
		if(data && data.__redirectUri__){
			if(confirm('이동 하시겠습니까?\n(' + data.__redirectUri__ + ')'))
				location.href = data.__redirectUri__;
			else
				log('Page Redirect was canceled. [' + data.__redirectUri__ + ']');
		}
	}
	
	// function for ajax setup
	// Ref := http://api.jquery.com/category/ajax/global-ajax-event-handlers/
	//TODO : ajax 호출흐름 정리
	function ajaxSetup(){
		var $document = $(document);
		$document.ajaxSuccess(handleRedirect);
		$document.ajaxComplete(handleRedirect);
		//$document.ajaxStop(handleRedirect);
	}

	// lazy bind plug-in
	$(document).ready(ajaxSetup);
	
	/*
	// common ajax callback for 30x.
	function _30x(){
		console.log("30x was responsed. ", arguments);
	}
	
	// for ajaxSetup() and statusCode option
	//   := http://api.jquery.com/jQuery.ajaxSetup/
	//   := http://api.jquery.com/jQuery.Ajax/
	// for http status code := http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
	var setup = {statusCode:{}};
	setup.statusCode['301'] = _30x;
	setup.statusCode['302'] = _30x;
	setup.statusCode['303'] = _30x;
	setup.statusCode['307'] = _30x;

	$.ajaxSetup();
	*/
	
	// SerializeObject function 정의
	$.fn.serializeObject = function()
	{
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
	
	$.ajaxSetup({
	   'beforeSend': function(xhr) {           
	      xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	    }
	});
	
})(jQuery);


// Redirect에 대한 테스트 객체. template()을 제외한 나머지 function()으로 테스트를 수행
var ajax_test = {
	template: function(url){
		var deferred = $.getJSON('http://localhost:8080/web/ajax-redirect/' + url);
		
		function fac(deferred, callback){
			deferred[callback](
				function(){
					console.log('ajax.' + callback + '()', arguments);
				}
			);
		};
		
		fac(deferred, 'done');
		fac(deferred, 'fail'); // call by logical error. server response with not success code(1000)
		fac(deferred, 'always');
		fac(deferred, 'then');
	},
	success : function(){ this.template("getBoardInfo.json?seqNo=1"); },
	jsonResponse : function(){ this.template('response-body'); },
	http30x : function(){ this.template('http-status'); }
};

var Watson = {
	
}