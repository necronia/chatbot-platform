<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lunch Menu</title>
</head>
<body>
	<iframe src="about:blank" name="pieceWrap" frameborder="0" scrolling="no"
		onLoad="if(!this.loaded){
			(function(node, frame){
				node.width = 740;
				node.height = 1280;
				node.loaded = true; 
  				frame.document.write('<html><body style=margin:0;><iframe style=\'position:absolute;top:-360px;left:-270px\' src=\'https://www.skhystec.com/support/supportCafeteriaIc.asp?m_gubun1=<%=request.getParameter("type")%>&shr_yymmdd=<%=request.getParameter("date")%>\' width=\'1000px\' height=\'3000px\' frameborder=\'0\' scrolling=\'no\'></iframe></body></html>');
			})(this, frames[this.name])};">
</body>
</html>