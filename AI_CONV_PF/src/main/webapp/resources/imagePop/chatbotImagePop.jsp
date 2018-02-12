<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> --%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> --%>


<!doctype html>
<html>
<link rel="stylesheet" href="/resources/css/chatbot/style.css">
<script src="/resources/js/lib/jquery/jquery-1.11.3.js"></script>

<script type="text/javascript">
var slideIndex = 1;
var imageName = " ";
var imageCount = 1; 
$(document).ready(function() {
	var wNm = window.name.split("┌");
	imageName = wNm[1];
	imageCount = wNm[2];
	var imgNm = imageName.split(".JPG")
	var imgClass = "<img class='mySlides' style='margin: 10px 30px 0px 30px' src="+imageName+">";
	for(var i=0; i<imageCount; i++){
		var imgNumber = i;
		if(imgNumber == 0){
		}else{
		    imgClass += "<img class='mySlides'  style='margin: 10px 30px 0px 30px' src="+imgNm[0]+"_"+(imgNumber+1)+".JPG>";
		}
	}
	$("#imgArea").html(imgClass);
	if(imageCount > 1){
		$("#arrowArea").css("display" , "block");
	}else{
		$("#arrowArea").css("display" , "none");
	}
	showDivs(slideIndex);
});

function plusDivs(n) {
    showDivs(slideIndex  += n);
}

function showDivs(n) {
     var i;
    var x = document.getElementsByClassName("mySlides");
    if  (n > x.length) {slideIndex = 1} 
    if (n < 1) {slideIndex =  x.length} ;
    for (i = 0; i < x.length; i++) {
         x[i].style.display = "none"; 
    }
     x[slideIndex-1].style.display = "block"; 
}
</script>
    </head>
    <body>
        <div>
			<div>
				<div id="imgArea" style="position: relative; z-index: 1;" >
				</div>
				<div id="arrowArea" style="text-align: center; position: relative; top: -360px; z-index: 2;">
					<a href="javascript:void(0)"><img src="<%=request.getContextPath()%>/resources/images/icon/arr_left.png" style="width:50px; height:50px;" onclick="plusDivs(-1)"/></a>
					<span style="padding:0px 950px 0px 0px;">&nbsp;</span>
					<a href="javascript:void(0)"><img src="<%=request.getContextPath()%>/resources/images/icon/arr_right.png" style="width:50px; height:50px;" onclick="plusDivs(+1)"/></a>
				</div>
			</div>
        </div>
</body>
</html>