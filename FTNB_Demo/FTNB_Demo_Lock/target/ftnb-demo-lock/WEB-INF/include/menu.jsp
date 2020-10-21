<%--
  Created by IntelliJ IDEA.
  User: xiaoy_000
  Date: 2017/7/6
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <script type="text/javascript" src="../js/jquery.js"></script>
</head>
<body>
<ul class="cl-vnavigation">
    <li <c:if test="${module =='index'}">class="active"</c:if>><a href="${pageContext.request.contextPath }/login/toIndex.do"><i class="fa fa-home"></i><span>主页</span></a>
    </li>
    <li><a href="#"><i class="fa fa-list-alt"></i><span>智能锁管理</span></a>
        <ul class="sub-menu">
            <li <c:if test="${module =='lock_list'}">class="active"</c:if>><a href="${pageContext.request.contextPath }/lock/findByThing.do">智能锁列表</a></li>
        </ul>
    </li>
</ul>
</body>
</html>
