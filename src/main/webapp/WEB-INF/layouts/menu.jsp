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
                <li class="active"><a href="#"><spring:message code="login.welcome"/></a></li>
                <li><a href="#">Order</a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Order<span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                    <li class="divider"></li>
                    <li><a href="#">One more separated link</a></li>
                  </ul>
                </li>
              </ul>
            </div><!-- /.navbar-collapse -->
            <!-- Navbar Right Menu -->
              <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                  <li class="dropdown">
                    <a href="javascript:void(0)" class="dropdown-toggle" style="cursor: default;" >
                    	<span class="x-lang-ch <c:if test="${pageContext.response.locale.language=='zh' }">selected</c:if>" onclick="changeLang('zh_CN')" ></span>
                    	<span class="x-lang-en <c:if test="${pageContext.response.locale.language=='en' }">selected</c:if>" onclick="changeLang('en_US')" ></span>
                    </a>
                  </li>
               
                  <!-- User Account Menu -->
                  <li class="dropdown" >
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Welcome, Admin <span class="caret"></span></a>
                    <ul class="dropdown-menu" >
                      <li >
                          <a href="#" >Profile</a>
                      </li>
                      <li >
                          <a href="${ctx}/logout" >Sign out</a>
                      </li>
                    </ul>
                  </li>
                </ul>
              </div><!-- /.navbar-custom-menu -->
          </div><!-- /.container-fluid -->
        </nav>
      </header>