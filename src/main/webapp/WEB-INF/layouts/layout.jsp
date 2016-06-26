<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.web.servlet.LocaleResolver"%>
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
    <title>Loslife | <sitemesh:title/></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${ctx}/static/styles/font-awesome.min.css">
    
    <!-- Ion Slider 
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/ionslider/ion.rangeSlider.skinFlat.css">
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/ionslider/ion.rangeSlider.skinNice.css">
    -->
    
    <!-- daterange picker 
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/daterangepicker/daterangepicker-bs3.css">
    -->
    
    <!-- iCheck for checkboxes and radio inputs 
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/iCheck/all.css">
    -->
    
    <!-- Bootstrap Color Picker 
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/colorpicker/bootstrap-colorpicker.min.css">
    -->
    
    <!-- Bootstrap time Picker 
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/timepicker/bootstrap-timepicker.min.css">
    -->
    
    <!-- Select2 -->
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/select2/select2.min.css">
    
    <!-- Theme style -->
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/dist/css/skins/_all-skins.css">
    
    <!-- daterangepicker 
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/daterangepicker/daterangepicker-bs3.css">
    -->
    
    <!-- datatable 
    <link rel="stylesheet" href="${ctx}/static/AdminLTE-2.3.3/plugins/datatables/dataTables.bootstrap.css">
    -->
    
    <!-- datetimepicker  new -->
    <link rel="stylesheet" href="${ctx}/static/datetimepicker/bootstrap-datetimepicker.min.css">
    
    <!-- custom style -->
    <link rel="stylesheet" href="${ctx}/static/styles/custom.css">
  
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- jQuery 2.1.4 -->
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/jQuery/jQuery-2.2.0.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="${ctx}/static/AdminLTE-2.3.3/bootstrap/js/bootstrap.min.js"></script>
   <!-- Select2 -->
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/select2/select2.full.min.js"></script>
    <!-- InputMask -->
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    
    <!-- date-range-picker 
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/daterangepicker/moment.min.js"></script>
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/daterangepicker/daterangepicker.js"></script>
    -->
    
    <!-- bootstrap color picker 
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
    -->
    
    <!-- bootstrap time picker 
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/timepicker/bootstrap-timepicker.min.js"></script>
    -->
    
    <!-- SlimScroll 1.3.0 
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    -->
    
    <!-- iCheck 1.0.1 
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/iCheck/icheck.min.js"></script>
    -->
    
    <!-- ChartJS 2.1.4 
    <script src="${ctx}/static/chartjs/Chart.js"></script>
    -->
    
    <!-- FastClick 
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/fastclick/fastclick.min.js"></script>
    -->
    <!-- Ion Slider 
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/ionslider/ion.rangeSlider.min.js"></script>
    -->
    
    <!-- bootbox 
    <script src="${ctx}/static/bootbox/bootbox.js"></script>
    -->
    
    <!-- jquery validate 1.14.0 
    <script src="${ctx}/static/jquery-validation/1.14.0/dist/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.14.0/dist/jquey.validate.override.js" type="text/javascript"></script>
	-->
	
    <!-- DataTables 
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${ctx}/static/AdminLTE-2.3.3/plugins/datatables/dataTables.bootstrap.min.js"></script>
    -->
    
    <!-- datetimepicker  new -->
    <script src="${ctx}/static/datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctx}/static/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>

    <!-- AdminLTE App -->
    <script src="${ctx}/static/AdminLTE-2.3.3/dist/js/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="${ctx}/static/AdminLTE-2.3.3/dist/js/demo.js"></script>
    
    <%
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver (request);
	String lang =localeResolver.resolveLocale(request).getLanguage();
	if("zh".equals(lang)){
		%>
		<!-- 
		<script src="${ctx}/static/jquery-validation/1.14.0/dist/localization/messages_zh.js" type="text/javascript"></script>
		 -->
		<%
	}
	%>
    
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
    
  </head>
  <body class="hold-transition layout-top-nav skin-black">
    <div class="wrapper">
	<%@ include file="/WEB-INF/layouts/menu.jsp"%>
      <!-- Full Width Column -->
      <div class="content-wrapper">
        <div class="">
         <sitemesh:body/>
        </div><!-- /.container -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="container">
          <div class="pull-right hidden-xs">
            <b>Version</b> 1.0
          </div>
          <strong>Copyright &copy; 2016 </strong> All rights reserved.
        </div><!-- /.container -->
      </footer>
    </div><!-- ./wrapper -->
    
  </body>
</html>