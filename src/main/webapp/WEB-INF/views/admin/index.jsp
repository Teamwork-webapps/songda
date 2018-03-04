<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="keywords" content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link rel="shortcut icon" href="#" type="image/png">

  <title>${webTitle}</title>

  <!--icheck-->
  <link href="${webRoot}static/admin/js/iCheck/skins/minimal/minimal.css" rel="stylesheet">
  <link href="${webRoot}static/admin/js/iCheck/skins/square/square.css" rel="stylesheet">
  <link href="${webRoot}static/admin/js/iCheck/skins/square/red.css" rel="stylesheet">
  <link href="${webRoot}static/admin/js/iCheck/skins/square/blue.css" rel="stylesheet">

  <!--dashboard calendar-->
  <link href="${webRoot}static/admin/css/clndr.css" rel="stylesheet">

  <!--Morris Chart CSS -->
  <link rel="stylesheet" href="${webRoot}static/admin/js/morris-chart/morris.css">

  <!--common-->
  <link href="${webRoot}static/admin/css/style.css" rel="stylesheet">
  <link href="${webRoot}static/admin/css/style-responsive.css" rel="stylesheet">
  <link href="${webRoot}static/admin/js/bootstrapvalidator/css/bootstrapValidator.min.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="${webRoot}static/admin/js/html5shiv.js"></script>
  <script src="${webRoot}static/admin/js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="sticky-header">

<section>
    <!-- left side start-->
    <div class="left-side sticky-left-side">

        <!--logo and iconic logo start-->
        <div class="logo">
            <a href="index.html"><img src="${webRoot}static/admin/images/logo.png" alt=""></a>
        </div>

        <div class="logo-icon text-center">
            <a href="index.html"><img src="${webRoot}static/admin/images/logo_icon.png" alt=""></a>
        </div>
        <!--logo and iconic logo end-->

        <div class="left-side-inner">

            <!-- visible to small devices only -->
            <#include "admin/common/miniUserInfo.jsp">

            <#include "admin/common/leftMenu.jsp">

        </div>
    </div>
    <!-- left side end-->
    
    <!-- main content start-->
    <div class="main-content" >

        <!-- header section start-->
        <div class="header-section">

            <!--toggle button start-->
            <a class="toggle-btn"><i class="fa fa-bars"></i></a>
            <!--toggle button end-->

            <!--notification menu start -->
            <div class="menu-right">
                <#include "admin/common/notification.jsp">
            </div>
            <!--notification menu end -->

        </div>
        <!-- header section end-->
		<#include "admin/common/defaultContent.jsp">
		
        <!--footer section start-->
        <footer>
            2018 &copy; SongDa
        </footer>
        <!--footer section end-->


    </div>
    <!-- main content end-->
</section>

<div aria-hidden="true" aria-labelledby="sysCommonModalLabel" role="dialog" tabindex="-1" id="sysCommonModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title" id="sysCommonModalTitle"></h4>
			</div>
			<div class="modal-body" id="sysCommonModalContent">
			</div>
		</div>
	</div>
</div>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${webRoot}static/admin/js/jquery-1.10.2.min.js"></script>
<script src="${webRoot}static/admin/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${webRoot}static/admin/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${webRoot}static/admin/js/bootstrap.min.js"></script>
<script src="${webRoot}static/admin/js/modernizr.min.js"></script>
<script src="${webRoot}static/admin/js/jquery.nicescroll.js"></script>

<!--easy pie chart-->
<script src="${webRoot}static/admin/js/easypiechart/jquery.easypiechart.js"></script>
<script src="${webRoot}static/admin/js/easypiechart/easypiechart-init.js"></script>

<!--Sparkline Chart-->
<script src="${webRoot}static/admin/js/sparkline/jquery.sparkline.js"></script>
<script src="${webRoot}static/admin/js/sparkline/sparkline-init.js"></script>

<!--icheck -->
<script src="${webRoot}static/admin/js/iCheck/jquery.icheck.js"></script>
<script src="${webRoot}static/admin/js/icheck-init.js"></script>

<!--Morris Chart-->
<script src="${webRoot}static/admin/js/morris-chart/morris.js"></script>
<script src="${webRoot}static/admin/js/morris-chart/raphael-min.js"></script>

<!--Calendar-->
<script src="${webRoot}static/admin/js/calendar/clndr.js"></script>
<script src="${webRoot}static/admin/js/calendar/evnt.calendar.init.js"></script>
<script src="${webRoot}static/admin/js/calendar/moment-2.2.1.js"></script>

<script src="${webRoot}static/admin/js/underscore/1.5.2/underscore-min.js"></script>

<!--common scripts for all pages-->
<script src="${webRoot}static/admin/js/scripts.js"></script>

<script src="${webRoot}static/admin/js/jquery.form.js"></script>

<script src="${webRoot}static/admin/js/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script src="${webRoot}static/admin/js/bootstrapvalidator/js/language/zh_CN.js"></script>

<script>
$(function(){
	//默认首页
	gotoPageUnPrId( $("#menuPageHome").children("a") );
});

function showSysCommonModel(title, url){
	$("#sysCommonModal").modal('hide');
	$("#sysCommonModal #sysCommonModalTitle").html("");
	$("#sysCommonModal #sysCommonModalContent").html("");
	$("#sysCommonModal").modal('show');
	$("#sysCommonModal #sysCommonModalTitle").html(title);
	$("#sysCommonModal #sysCommonModalContent").load(url);
}

function showSysCommonMessage(title, message){
	$("#sysCommonModal #sysCommonModalTitle").html("");
	$("#sysCommonModal #sysCommonModalContent").html("");
	$("#sysCommonModal").modal('show');
	$("#sysCommonModal #sysCommonModalTitle").html(title);
	$("#sysCommonModal #sysCommonModalContent").html(message);
}

function closeSysCommonModel(){
	$("#sysCommonModal").modal('hide');
	$("#sysCommonModal #sysCommonModalTitle").html("");
	$("#sysCommonModal #sysCommonModalContent").html("");
}

var sysCurrentPage = 1;

function doSearch(){
	if(adminUserListPage != undefined){
		doSearch(adminUserListPage);
	}else{
		doSearch(1);
	}
}

var activePrId = "";
var activeId = "";

function gotoPageUnPrId(linkObj){
	changePrLevelActive( $(linkObj).attr("id") );
	changeMenuActive("");
	//网页跳转
	$("#adminBackSysIndex").load( $(linkObj).attr("data-url") );
	$("#page-heading-prName").hide();
	$("#page-heading-menuName").html($(linkObj).children("span").html());
}

function gotoPage(linkObj,prId){
	changePrLevelActive( prId );
	changeMenuActive( $(linkObj).attr("id") );
	//网页跳转
	$("#adminBackSysIndex").load( $(linkObj).attr("data-url") );
	//设置title部分
	$("#page-heading-prName").show();
	$("#page-heading-prName").children("a").html( $("#"+prId).children("a").children("span").html() );
	$("#page-heading-menuName").html($(linkObj).html());
}

function changePrLevelActive(nowId){
	if(activePrId != nowId){
		//判断是否是菜单组。增加的class不同
		if( $("#" + activePrId).hasClass("menu-list") ){
			$("#" + activePrId).removeClass("nav-active");
		}else{
			$("#" + activePrId).removeClass("active");
		}
		if( $("#" + nowId).hasClass("menu-list") ){
			$("#" + nowId).addClass("nav-active");
		}else{
			$("#" + nowId).addClass("active");
		}
		activePrId = nowId;
	}
}

function changeMenuActive(nowId){
	if(activeId != nowId){
		if(activeId != ""){
			$("#" + activeId).removeClass("active");
		}
		$("#" + nowId).addClass("active");
		activeId = nowId;
	}
}
</script>

</body>
</html>
