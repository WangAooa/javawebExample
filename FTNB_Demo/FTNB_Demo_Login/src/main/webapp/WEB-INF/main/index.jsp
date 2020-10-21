<%--
  Created by IntelliJ IDEA.
  User: xiaoy_000
  Date: 2017/7/1
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<!-- Mirrored from condorthemes.com/cleanzone/charts.html by HTTrack Website Copier/3.x [XR&CO'2013], Mon, 31 Mar 2014 14:37:32 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../images/favicon.png">

    <title>NBIOT - 主页</title>

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
    <!-- Custom styles for this template -->
    <link href="../css/style.css" rel="stylesheet" />

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
            <h2>主页</h2>
            <ol class="breadcrumb">
                <li><a href="#">主页</a></li>
                <%--<li class="active">主页</li>--%>
            </ol>
        </div>
        <div class="cl-mcont">


        </div>
    </div>

</div>

<script src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.nanoscroller/jquery.nanoscroller.js"></script>
<script type="text/javascript" src="../js/jquery.sparkline/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="../js/jquery.easypiechart/jquery.easy-pie-chart.js"></script>
<script type="text/javascript" src="../js/behaviour/general1.js"></script>
<script src="../js/jquery.ui/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery.nestable/jquery.nestable.js"></script>
<script type="text/javascript" src="../js/bootstrap.switch/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../js/jquery.select2/select2.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.slider/js/bootstrap-slider.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery.gritter/js/jquery.gritter.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        //initialize the javascript
        App.init();
        App.dashBoard();
        // App.charts();
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

<!-- Mirrored from condorthemes.com/cleanzone/charts.html by HTTrack Website Copier/3.x [XR&CO'2013], Mon, 31 Mar 2014 14:37:32 GMT -->
</html>
