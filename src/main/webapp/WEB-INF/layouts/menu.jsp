<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

  <header class="main-header">
  
        <nav class="navbar navbar-static-top ">
          <div class="container">
            <div class="navbar-header">
              <a href="${ctx}/static/AdminLTE-2.3.0/index2.html" class="navbar-brand"><img style="width: 110px;height: 40px;margin-top: -6px;" src="${ctx}/static/images/iclick-logo.png"></a>
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <i class="fa fa-bars"></i>
              </button>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
              <ul class="nav navbar-nav">
                <li id="menu_business_opportunity"><a href="${ctx}/businessOpportunity"><spring:message code="menu.business.opportunity"/></a></li>
                <li id="menu_client" class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="menu.advertiser"/><span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="${ctx}/client"><spring:message code="menu.advertiser"/></a></li>
                    <li><a href="${ctx}/advertiser/list"><spring:message code="menu.advertiser.review"/></a></li>
                  </ul>
                </li>
                <li id="menu_agency"><a href="#"><spring:message code="menu.agency"/></a></li>
                <li id="menu_report" class="dropdown hidden-sm hidden-xs">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="menu.report"/><span class="caret"></span></a>
                 
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="#"><spring:message code="menu.report"/>1</a></li>
                    <li><a href="#"><spring:message code="menu.report"/>2</a></li>
                    <li class="divider"></li>
                    <li><a href="#"><spring:message code="menu.report"/>3</a></li>
                    <li class="divider"></li>
                    <li><a href="#"><spring:message code="menu.report"/>4</a></li>
                  </ul>
                </li>
              </ul>
            </div><!-- /.navbar-collapse -->
            <!-- Navbar Right Menu -->
              <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                  <li class="dropdown" >
                    <a href="javascript:void(0)" class="dropdown-toggle" style="cursor: default;height: 50px;" >
                    	<span class="x-lang-ch <c:if test="${pageContext.response.locale.language=='zh' }">selected</c:if>" onclick="changeLang('zh_CN')" ></span>
                    	<span class="x-lang-en <c:if test="${pageContext.response.locale.language=='en' }">selected</c:if>" onclick="changeLang('en_US')" ></span>
                    </a>
                  </li>
               
	               <li class="dropdown">
	                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><shiro:principal/><span class="caret"></span></a>
	                  <ul class="dropdown-menu" role="menu">
	                    
	                    <li><a href="#"><span class="glyphicon glyphicon-cog"></span>
	                    		<spring:message code="menu.profile"/>
	                    	</a></li>
	                    <li class="divider"></li>
	                    <li> <a href="${ctx}/logout"><span class="glyphicon glyphicon-log-out"></span>
	                    		<spring:message code="menu.logout"/>
	                        </a></li>
	                  </ul>
	                </li>
               
                </ul>
              </div><!-- /.navbar-custom-menu -->
          </div><!-- /.container-fluid -->
        </nav>
      </header>