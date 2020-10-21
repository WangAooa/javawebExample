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
    <li><a href="#"><i class="fa fa-map-marker"></i><span>智慧交通管理</span></a>
        <ul class="sub-menu">
            <li <c:if test="${module =='car_list'}">class="active"</c:if>><a href="${pageContext.request.contextPath }/car/findByThing.do">车辆列表</a></li>
        </ul>
    </li>
</ul>
</body>
</html>
