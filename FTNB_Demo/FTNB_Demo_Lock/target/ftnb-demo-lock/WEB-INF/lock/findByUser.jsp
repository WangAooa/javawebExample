<%--
  Created by IntelliJ IDEA.
  User: xiaoy_000
  Date: 2017/7/6
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <title>NBIOT - 智能锁管理</title>

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
                url:"${pageContext.request.contextPath }/public/freshLock.do",
                dataType:"json",
                success: function(data){
                    for(i=0;i<data.length;i++){
                        var s = data[i];
                        if(s.sl_li_status == 0){
                            $('#1' + s.sy_di_code).text("施封");
                        }else if(s.sl_li_status == 1){
                            $('#1' + s.sy_di_code).text("解封");
                        }else if(s.sl_li_status == 2){
                            $('#1' + s.sy_di_code).text("验封");
                        }else{
                            $('#1' + s.sy_di_code).text("解除报警");
                        }
                        if(s.sl_li_online == 0){
                            $('#2' + s.sy_di_code).text("离线");
                        }else{
                            $('#2' + s.sy_di_code).text("在线");
                        }
                    }
                }
            })
        }

        window.setInterval(getVal, 5000);

        function open1(code){
            wait('#j' + code);
            //alert("jiefeng");
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath }/lock/openLock.do",
                dataType:"json",
                data:{'sy_di_code':code},
                success: function(data){

                }
            })

        }

        function close1(code){
            wait('#s' + code);
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath }/lock/closeLock.do",
                dataType:"json",
                data:{'sy_di_code':code},
                success: function(data){

                }
            })
        }

        <%--function check1(code){--%>
            <%--$.ajax({--%>
                <%--type:"post",--%>
                <%--url:"${pageContext.request.contextPath }/lock/checkLock.do",--%>
                <%--dataType:"json",--%>
                <%--data:{'sy_di_code':code},--%>
                <%--success: function(data){--%>

                <%--}--%>
            <%--})--%>
        <%--}--%>

        <%--function alarm1(code){--%>
            <%--$.ajax({--%>
                <%--type:"post",--%>
                <%--url:"${pageContext.request.contextPath }/lock/alarmRelease.do",--%>
                <%--dataType:"json",--%>
                <%--data:{'sy_di_code':code},--%>
                <%--success: function(data){--%>

                <%--}--%>
            <%--})--%>
        <%--}--%>

        function wait(wcode){
            $(wcode).attr("disabled",true);
            setTimeout(function(){$(wcode).attr("disabled",false);}, 2000);
        }

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
            <h2>智能锁列表信息</h2>
            <ol class="breadcrumb">
                <li><a href="#">主页</a></li>
                <li><a href="#">智能锁管理</a></li>
                <li class="active">智能锁列表信息</li>
            </ol>
        </div>
        <div class="cl-mcont">

            <div class="row">
                <div class="col-md-12">
                    <div class="block-flat">
                        <div class="header">
                            <h3>智能锁列表信息</h3>
                        </div>
                        <div class="content">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="datatable-icons" >
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>设备序列号</th>
                                        <th>锁状态</th>
                                        <th>在线状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${lockinfos}" var="l">
                                        <tr>
                                            <td>${l.sl_li_id}</td>
                                            <td>${l.sy_di_code}</td>
                                            <td id="1${l.sy_di_code}">
                                                <c:choose>
                                                    <c:when test="${l.sl_li_status==0}">施封</c:when>
                                                    <c:when test="${l.sl_li_status==1}">解封</c:when>
                                                    <c:when test="${l.sl_li_status==2}">验封</c:when>
                                                    <c:when test="${l.sl_li_status==3}">解除报警</c:when>
                                                </c:choose>
                                            </td>
                                            <td id="2${l.sy_di_code}">
                                                <c:choose>
                                                    <c:when test="${l.sl_li_online==0}">离线</c:when>
                                                    <c:when test="${l.sl_li_online==1}">在线</c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <%--<a href="${pageContext.request.contextPath }/lock/toUpdate.do?sy_di_code=${l.sy_di_code}" title="设置信息">设置信息</a>&nbsp;&nbsp;--%>
                                                <%--<a href="${pageContext.request.contextPath }/lock/findByCode.do?sy_di_code=${l.sy_di_code}" title="查看设备信息">查看设备信息</a>&nbsp;&nbsp;--%>
                                                <%--<a href="${pageContext.request.contextPath }/lock/findInfoByCode.do?sy_di_code=${l.sy_di_code}" title="GPS显示">GPS显示</a>&nbsp;&nbsp;--%>
                                                    <%--<a href="${pageContext.request.contextPath }/lock/delete.do?sl_li_id=${l.sl_li_id}" title="删除">删除</a>&nbsp;&nbsp;--%>
                                                        <%--<a href="${pageContext.request.contextPath }/lock/toUpdateTime.do?sy_di_code=${l.sy_di_code}" title="设置间隔时间">设置间隔时间</a>&nbsp;&nbsp;--%>
                                                <%--<a onclick="open1('${l.sy_di_code}');" title="开锁">开锁</a>&nbsp;&nbsp;--%>
                                                <%--<a onclick="close1('${l.sy_di_code}');" title="关锁">关锁</a>&nbsp;&nbsp;--%>
                                                <%--<button class="btn btn-primary" type="button" onclick="open('${l.sy_di_code}');">开锁</button>&nbsp;&nbsp;--%>
                                                <%--<button class="btn btn-default" type="button" onclick="close('${l.sy_di_code}');" >关锁</button>&nbsp;&nbsp;--%>

                                                <input type="button" id="s${l.sy_di_code}" value="施封" onclick="close1('${l.sy_di_code}');" />
                                                <input type="button" id="j${l.sy_di_code}" value="解封" onclick="open1('${l.sy_di_code}');" />
                                                <%--<input type="button" value="验封" onclick="check1('${l.sy_di_code}');" />--%>
                                                <%--<input type="button" value="报警解除" onclick="alarm1('${l.sy_di_code}');" />--%>
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
