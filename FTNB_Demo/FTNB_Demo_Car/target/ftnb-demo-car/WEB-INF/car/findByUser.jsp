<%--
  Created by IntelliJ IDEA.
  User: xiaoy_000
  Date: 2017/7/6
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<!-- Mirrored from condorthemes.com/cleanzone/tables-datatables.html by HTTrack Website Copier/3.x [XR&CO'2013], Mon, 31 Mar 2014 14:37:21 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../images/favicon.png">

    <title>NBIOT - 智慧交通管理</title>

    <!-- Bootstrap core CSS -->
    <link href="../js/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../js/jquery.gritter/css/jquery.gritter.css" />

    <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="../js/html5shiv.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="../js/jquery.nanoscroller/nanoscroller.css" />
    <link rel="stylesheet" type="text/css" href="../js/jquery.easypiechart/jquery.easy-pie-chart.css" />
    <link rel="stylesheet" type="text/css" href="../js/bootstrap.switch/bootstrap-switch.css" />
    <link rel="stylesheet" type="text/css" href="../js/bootstrap.datetimepicker/css/bootstrap-datetimepicker.min.css" />
    <link rel="stylesheet" type="text/css" href="../js/jquery.select2/select2.css" />
    <link rel="stylesheet" type="text/css" href="../js/bootstrap.slider/css/slider.css" />
    <link rel="stylesheet" type="text/css" href="../js/jquery.datatables/bootstrap-adapter/css/datatables.css" />
    <link href="../css/style.css" rel="stylesheet" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript">
        function getVal(){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath }/public/freshCar.do",
                dataType:"json",
                success: function(data){
                    for(i=0;i<data.length;i++){
                        var s = data[i];
                        if(s.st_ci_status == 1){
                            $('#' + s.sy_di_code + "1").text("入库");
                        }else{
                            $('#' + s.sy_di_code + "1").text("出库");
                        }
                        if(s.st_ci_online == 0){
                            $('#' + s.sy_di_code + "2").text("离线");
                        }else{
                            $('#' + s.sy_di_code + "2").text("在线");
                        }
                    }
                }
            })
        }

        window.setInterval(getVal, 10000);
    </script>
</head>

<body>
<jsp:include page="/WEB-INF/include/info.jsp" flush="true" />

<div id="cl-wrapper">
    <div class="cl-sidebar">
        <div class="cl-toggle"><i class="fa fa-bars"></i></div>
        <div class="cl-navblock">
            <div class="menu-space">
                <div class="content">
                    <div class="side-user">
                        <div class="avatar"><img src="../images/avatar1_50.jpg" alt="Avatar" /></div>
                        <div class="info">
                            <a href="#">
                                <%
                                    String u=session.getAttribute("name").toString();
                                %>
                                <%out.print(u); %>
                            </a>
                            <img src="../images/state_online.png" alt="Status" /> <span>Online</span>
                        </div>
                    </div>
                    <jsp:include page="/WEB-INF/include/menu.jsp" flush="true" />
                </div>
            </div>
            <div class="text-right collapse-button" style="padding:7px 9px;">
                <button id="sidebar-collapse" class="btn btn-default" style=""><i style="color:#fff;" class="fa fa-angle-left"></i></button>
            </div>
        </div>
    </div>

    <div class="container-fluid" id="pcont">
        <div class="page-head">
            <h2>设备列表信息</h2>
            <ol class="breadcrumb">
                <li><a href="#">主页</a></li>
                <li><a href="#">智慧交通管理</a></li>
                <li class="active">设备列表信息</li>
            </ol>
        </div>
        <div class="cl-mcont">

            <div class="row">
                <div class="col-md-12">
                    <div class="block-flat">
                        <div class="header">
                            <h3>设备列表信息</h3>
                        </div>
                        <div class="content">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="datatable-icons" >
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>设备序列号</th>
                                        <th>NFC</th>
                                        <th>车牌号</th>
                                        <th>车辆状态</th>
                                        <th>在线状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${carinfos}" var="c">
                                        <tr>
                                            <td>${c.st_ci_id}</td>
                                            <td>${c.sy_di_code}</td>
                                            <td>${c.st_ci_nfc}</td>
                                            <td>${c.st_ci_carnum}</td>
                                            <td id="${c.sy_di_code}1">
                                                <c:choose>
                                                    <c:when test="${c.st_ci_status==1}">入库</c:when>
                                                    <c:when test="${c.st_ci_status==2}">出库</c:when>
                                                </c:choose>
                                            </td>
                                            <td id="${c.sy_di_code}2">
                                                <c:choose>
                                                    <c:when test="${c.st_ci_online==0}">离线</c:when>
                                                    <c:when test="${c.st_ci_online==1}">在线</c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <%--<a href="${pageContext.request.contextPath }/car/toUpdateCard.do?sy_di_code=${c.sy_di_code}" title="设置卡号">设置卡号</a>&nbsp;&nbsp;--%>
                                                <%--<a href="${pageContext.request.contextPath }/car/toUpdate.do?sy_di_code=${c.sy_di_code}" title="设置信息">设置信息</a>&nbsp;&nbsp;--%>
                                                <%--<a href="${pageContext.request.contextPath }/car/findByCode.do?sy_di_code=${c.sy_di_code}" title="查看设备信息">查看设备信息</a>&nbsp;&nbsp;--%>
                                                <%--&lt;%&ndash;<a href="${pageContext.request.contextPath }/car/toRecharge.do?sy_di_code=${c.sy_di_code}" title="充值">充值</a>&nbsp;&nbsp;&ndash;%&gt;--%>
                                                <%--<a href="${pageContext.request.contextPath }/car/findInfoByCode.do?sy_di_code=${c.sy_di_code}" title="GPS显示">GPS显示</a>&nbsp;&nbsp;--%>
                                                    <%--<a href="${pageContext.request.contextPath }/car/delete.do?st_ci_id=${c.st_ci_id}" title="删除">删除</a>&nbsp;&nbsp;--%>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>

<script src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.nanoscroller/jquery.nanoscroller.js"></script>
<script type="text/javascript" src="../js/jquery.sparkline/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="../js/jquery.easypiechart/jquery.easy-pie-chart.js"></script>
<script type="text/javascript" src="../js/behaviour/general.js"></script>
<script src="../js/jquery.ui/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery.nestable/jquery.nestable.js"></script>
<script type="text/javascript" src="../js/bootstrap.switch/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../js/jquery.select2/select2.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.slider/js/bootstrap-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery.gritter/js/jquery.gritter.js"></script>
<script type="text/javascript" src="../js/jquery.datatables/jquery.datatables.min.js"></script>
<script type="text/javascript" src="../js/jquery.datatables/bootstrap-adapter/js/datatables.js"></script>

<script type="text/javascript">

    $(document).ready(function(){
        //initialize the javascript
        App.init();
        App.dataTables();

        /* Formating function for row details */
        function fnFormatDetails ( oTable, nTr )
        {
            var aData = oTable.fnGetData( nTr );
            var sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
            sOut += '<tr><td>Rendering engine:</td><td>'+aData[1]+' '+aData[4]+'</td></tr>';
            sOut += '<tr><td>Link to source:</td><td>Could provide a link here</td></tr>';
            sOut += '<tr><td>Extra info:</td><td>And any further details here (images etc)</td></tr>';
            sOut += '</table>';

            return sOut;
        }

        /*
         * Insert a 'details' column to the table
         */
        var nCloneTh = document.createElement( 'th' );
        var nCloneTd = document.createElement( 'td' );
        nCloneTd.innerHTML = '<img class="toggle-details" src="images/plus.png" />';
        nCloneTd.className = "center";

        $('.dataTables_filter input').addClass('form-control').attr('placeholder','Search');
        $('.dataTables_length select').addClass('form-control');

        //Horizontal Icons dataTable
        $('#datatable-icons').dataTable();
    });
</script>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../js/behaviour/voice-commands.js"></script>
<script src="../js/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.flot/jquery.flot.js"></script>
<script type="text/javascript" src="../js/jquery.flot/jquery.flot.pie.js"></script>
<script type="text/javascript" src="../js/jquery.flot/jquery.flot.resize.js"></script>
<script type="text/javascript" src="../js/jquery.flot/jquery.flot.labels.js"></script>
</body>

<!-- Mirrored from condorthemes.com/cleanzone/tables-datatables.html by HTTrack Website Copier/3.x [XR&CO'2013], Mon, 31 Mar 2014 14:37:27 GMT -->
</html>
