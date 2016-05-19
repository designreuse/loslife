<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.springframework.context.i18n.LocaleContextHolder"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="${ctx}/static/images/favicon.ico">
    <title>PMS | <sitemesh:title/></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.0/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${ctx}/static/styles/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${ctx}/static/styles/ionicons.min.css">
    
    <!-- Theme style -->
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.0/dist/css/AdminLTE.css">
   
    <style type="text/css">
    body,button, input, select, textarea,h1 ,h2, h3, h4, h5, h6 { font-family: Microsoft YaHei, Tahoma, Helvetica, Arial,  sans-serif;}
    h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
    font-family: "Microsoft YaHei UI", "Microsoft YaHei", "Arial", "Verdana", "Tahoma";
	}
	body {
	    font-family: "Microsoft YaHei", "Arial", "Verdana", "Tahoma";
	    color: #424242;
	    font-size: 12px;
	}
	 /*langauage ch or en*/
	.x-lang{
	    position: fixed;
	    top: 2px;
	    right: 5px;
	}
	.x-lang-en{
	    width: 24px;
	    height: 24px;
	    border: 1px solid #acb0bc;
	    display: inline-block;
	    background-color: #acb0bc;
	    border-radius: 24px;
	    text-align: center;
	    color: #434343;
	    vertical-align: middle;
	    cursor: pointer;
	    line-height: 24px;
	    box-sizing:border-box;
	}
	.x-lang-en:after{content: "E"}
	
	.x-lang-ch{
	    width: 24px;
	    height: 24px;
	    border: 1px solid #acb0bc;
	    display: inline-block;
	    background-color: #acb0bc;
	    border-radius: 24px;
	    text-align: center;
	    color: #434343;
	    vertical-align: middle;
	    cursor: pointer;
	    line-height: 24px;
	    box-sizing:border-box;
	}
	.x-lang-ch:after{content: "中"}
	
	.x-lang-ch.selected,.x-lang-en.selected{background-color: #336dc6;color: white}
    </style>
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.0/dist/css/skins/_all-skins.css">

    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>
  <body class="hold-transition layout-top-nav skin-black">
    <div class="wrapper">
	<%@ include file="/WEB-INF/layouts/menu.jsp"%>
      <!-- Full Width Column -->
      <div class="content-wrapper">
        <div class="container">
         <sitemesh:body/>
        </div><!-- /.container -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="container">
          <div class="pull-right hidden-xs">
            <b>Version</b> 1.0
          </div>
          <strong>Copyright &copy; 2016 <a href="http://www.i-click.com/">iClick Interactive Asia Limited</a>.</strong> All rights reserved.
        </div><!-- /.container -->
      </footer>
    </div><!-- ./wrapper -->

    <!-- jQuery 2.1.4 -->
    <script src="${ctx}/static/AdminLTE-2.3.0/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${ctx}/static/AdminLTE-2.3.0/bootstrap/js/bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="${ctx}/static/AdminLTE-2.3.0/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="${ctx}/static/AdminLTE-2.3.0/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="${ctx}/static/AdminLTE-2.3.0/dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="${ctx}/static/AdminLTE-2.3.0/dist/js/demo.js"></script>
    <script type="text/javascript">
	    function changeLang(lang){
	    	url = window.location.href;
	    	if(url.indexOf("#")>-1){
	    		window.location.href = url.replace("#","");
	    	}
	    	if(url.indexOf("lang=zh_CN")>-1){
	    		window.location.href = url.replace("lang=zh_CN","lang="+lang);
	    	}
	    	else if(url.indexOf("lang=en_US")>-1){
	    		window.location.href = url.replace("lang=en_US","lang="+lang);
	    	}else if( url.indexOf("?")==-1){
    			window.location.href = url+"?lang="+lang;
    		}else {
    			window.location.href = url+"&lang="+lang;
    		}
	    }
	    
	    
    </script>
    
  </body>
</html>

