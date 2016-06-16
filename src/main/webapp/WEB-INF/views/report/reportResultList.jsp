<%@page import="com.asgab.util.JsonMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.asgab.util.CommonUtil"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.asgab.entity.Report"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.web.servlet.LocaleResolver"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.asgab.service.account.ShiroDbRealm.ShiroUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
    ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
	String lang = localeResolver.resolveLocale(request).getLanguage();
%>

<div class="box-header with-border">
	<h3 class="box-title ">
		<spring:message code="report.total.sale" /> <b class="text-green">￥<span id="totalSum"></span></b>
	</h3>
	<a class="pull-right"><i class="fa fa-fw fa-download"></i><spring:message code="report.download" /></a>
	<select id="changeRate" class="pull-right select" style="margin-right: 10px;">
		<option value="1">RMB</option>
		<c:forEach items="${exchangeRates}" var="exchangeRate">
			<option value="${exchangeRate.rate}">${exchangeRate.currency}</option>
		</c:forEach>
	</select>
</div>

<div class="box-body  table-responsive no-padding">
	<table class="table table-bordered table-striped" id="dataTableReport">
		<thead>
		</thead>
		<tbody>
			<%
				List<List<String>> datas = new ArrayList<>();
			
				List<String> names = (List<String>)request.getAttribute("names");
				List<Report> opportunityReports = (List<Report>)request.getAttribute("opportunityReports");
				List<Report> orderReports = (List<Report>)request.getAttribute("orderReports");
				BigDecimal totalSum = BigDecimal.ZERO;
				BigDecimal orderSum = BigDecimal.ZERO;
				BigDecimal opportunitySum = BigDecimal.ZERO;
				for(int i = 0 ;i <names.size();i++){
				  List<String> data = new ArrayList<String>();
				  BigDecimal sum = BigDecimal.ZERO;
				  Report orderReport = orderReports.get(i);
				  if(orderReport!=null){
				    sum = sum.add(orderReport.getBudgetSum());
				    orderSum = orderSum.add(orderReport.getBudgetSum());
				  }
				  Report opportunityReport = opportunityReports.get(i);
				  if(opportunityReport!=null){
				    sum = sum.add(opportunityReport.getBudgetSum());
				    opportunitySum = opportunitySum.add(opportunityReport.getBudgetSum());
				  }
				  totalSum = totalSum.add(sum);
				  
				  data.add(names.get(i));
				  data.add(orderReport!=null?orderReport.getFmtBudgetSum():"0.00");
				  data.add(opportunityReport!=null?opportunityReport.getFmtBudgetSum():"0.00");
				  data.add(CommonUtil.digSeg(sum.doubleValue()));
				  datas.add(data);
				}
				request.setAttribute("totalSum", CommonUtil.digSeg(totalSum.doubleValue()));
				request.setAttribute("dataSet", JsonMapper.nonEmptyMapper().toJson(datas));
			%>
		</tbody>
		<tfoot>
			<tr>
				<th><spring:message code="report.total" /></th>
				<th style="text-align: right;"><%=CommonUtil.digSeg(orderSum.doubleValue()) %></th>
				<th style="text-align: right;"><%=CommonUtil.digSeg(opportunitySum.doubleValue()) %></th>
				<th style="text-align: right;"><%=CommonUtil.digSeg(totalSum.doubleValue()) %></th>
			</tr>
		</tfoot>
	</table>
	
</div>

<script type="text/javascript">
		$(function() {
			$("#totalSum").text('${totalSum}');
			var dataSet = ${dataSet};
			var dataRightColumn = "${dataRightColumn}";
			var col1 = "<spring:message code='report.clo1'/>";
			var col2 = "<spring:message code='report.clo2'/>";
			var col3 = "<spring:message code='report.clo3'/>";
			var sInfo = "<spring:message code='report.sinfo'/>";
			var sFirst= "<spring:message code='report.sfirst'/>";
			var sPrevious= "<spring:message code='report.sprevious'/>";
			var sNext= "<spring:message code='report.snext'/>";
			var sLast= "<spring:message code='report.slast'/>";
			var sLengthMenu= "<spring:message code='report.slengthmenu'/>";
			var table = $("#dataTableReport").DataTable({
				searching: true,
				paging: true,
				data: dataSet,
				columns: [
				            { title: dataRightColumn },
				            { title: col1 },
				            { title: col2 },
				            { title: col3 }
				        ],
				language: {
					"sInfo":sInfo,
					"oPaginate": {
			            "sFirst": sFirst,
			            "sPrevious": sPrevious,
			            "sNext": sPrevious,
			            "sLast": sLast,
			        },
			        "sLengthMenu": sLengthMenu
				},
				"createdRow": function( row, data, dataIndex ) {
                    $(row).children('td').eq(1).attr('style', 'text-align: right;');
                    $(row).children('td').eq(2).attr('style', 'text-align: right;');
                    $(row).children('td').eq(3).attr('style', 'text-align: right;');
                },
			});
			
			$("#changeRate").change(function(){
				var rate = +$(this).val();
				console.log(rate);
		        table.clear();
		        var tmpDatas = [];
		        for(var i = 0 ; i<dataSet.length;i++){
		        	var tmpData = [];
		        	tmpData[0] = dataSet[i][0];
		        	for(var j = 1 ; j<dataSet[i].length;j++){
		        		var value = formatMoney(rate * rmoney(dataSet[i][j]));
		        		tmpData[j] = formatMoney(value,2);
		        	}
		        	tmpDatas[i] = tmpData;
		        }
		        table.rows.add(tmpDatas);
		        table.draw();
			});
			
		});
		
		
		function rmoney(s)   
		{   
		   return parseFloat(s.replace(/[^\d\.-]/g, ""));   
		}
		
		function formatMoney(s, n)   
		{   
		   n = n > 0 && n <= 20 ? n : 2;   
		   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
		   var l = s.split(".")[0].split("").reverse(),   
		   r = s.split(".")[1];   
		   t = "";   
		   for(i = 0; i < l.length; i ++ )   
		   {   
		      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
		   }   
		   return t.split("").reverse().join("") + "." + r;   
		};
</script>