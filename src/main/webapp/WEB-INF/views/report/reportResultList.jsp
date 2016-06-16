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
		总销售报数 <b class="text-green">￥<span id="totalSum"></span></b>
	</h3>
	<a class="pull-right"><i class="fa fa-fw fa-download"></i>下载</a>
</div>

<div class="box-body  table-responsive no-padding">
	<table class="table table-bordered table-striped" id="dataTableReport">
		<thead>
			<tr>
				<th>产品</th>
				<th>签约订单</th>
				<th>商机</th>
				<th>小计</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<String> product_names = (List<String>)request.getAttribute("product_names");
				List<Report> opportunityReports = (List<Report>)request.getAttribute("opportunityReports");
				List<Report> orderReports = (List<Report>)request.getAttribute("orderReports");
				BigDecimal totalSum = BigDecimal.ZERO;
				for(int i = 0 ;i <product_names.size();i++){
				  BigDecimal sum = BigDecimal.ZERO;
				  Report orderReport = orderReports.get(i);
				  if(orderReport!=null){
				    sum = sum.add(orderReport.getBudgetSum());
				  }
				  Report opportunityReport = opportunityReports.get(i);
				  if(opportunityReport!=null){
				    sum = sum.add(opportunityReport.getBudgetSum());
				  }
				  totalSum = totalSum.add(sum);
				  %>
				  	<tr>
					<td><%=product_names.get(i) %></td>
					<td style="text-align: right;"><%=orderReport!=null?orderReport.getFmtBudgetSum():"0.00" %></td>
					<td style="text-align: right;"><%=opportunityReport!=null?opportunityReport.getFmtBudgetSum():"0.00" %></td>
					<td style="text-align: right;"><%=CommonUtil.digSeg(sum.doubleValue()) %></td>
					</tr>
				  <%
				}
				request.setAttribute("totalSum", CommonUtil.digSeg(totalSum.doubleValue()));
			%>

		</tbody>
	</table>
	
</div>

<script type="text/javascript">
		$(function() {
			$("#totalSum").text('${totalSum}');
			$("#dataTableReport").DataTable({
				searching: false,
				paging: false
			});
			
		})
</script>