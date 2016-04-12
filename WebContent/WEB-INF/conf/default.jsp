<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="utf-8">
	<head>
		<meta charset="utf-8" />
		<title>XSnake Admin</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link rel="stylesheet" href="${res}/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${res}/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${res}/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${res}/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${res}/assets/css/ace-skins.min.css" />
		<script src="${res}/assets/js/ace-extra.min.js"></script>
		<script src='${res}/assets/js/jquery-1.10.2.min.js'></script>
		<script src='${res}/assets/js/jquery.validate.min.js'></script>
		<script src='${res}/assets/js/plugin/jquery-form.min.js'></script>
		<script src='${res}/assets/js/jquery-ui-1.10.3.full.min.js'></script>
		<script src='${res}/resource/js/ajax.js' ></script>
	</head>
	<sitemesh:write property="head" />
<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i
						class="icon-leaf"></i> XSnake Admin
				</small>
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
				
				<!-- .nav-list -->
				<ul class="nav nav-list">
					<li class="active">
						<a href="main.html"> 
							<i class="icon-dashboard"></i> <span class="menu-text"> 首页 </span>
						</a>
					</li>
					
					<li>
						<a href="service/serviceList.html"> 
							<i class="icon-server"></i> <span class="menu-text"> 服务管理 </span>
						</a>
					</li>
					
					
					<li>
						<a href="#" class="dropdown-toggle"> 
							<i class="icon-text-width"></i> <span class="menu-text"> 参数配置 </span>
							<b class="arrow icon-angle-down"></b>
						</a>
						<ul class="submenu">
							<li>
								<a href="trustAddress.html"> 
									<i class="icon-double-angle-right"></i> IP白名单
								</a>
							</li>
							<li>
								<a href="recordDatabase.html"> 
									<i class="icon-double-angle-right"></i> 记录地址配置
								</a>
							</li>
						</ul>
					</li>
					
					<li>
						<a href="#" class="dropdown-toggle"> 
							<i class="icon-text-width"></i> <span class="menu-text"> 历史分析 </span>
							<b class="arrow icon-angle-down"></b>
						</a>
						<ul class="submenu">
							<li>
								<a href="trustAddress.html"> 
									<i class="icon-double-angle-right"></i> 服务维度
								</a>
							</li>
							
							<li>
								<a href="recordDatabase.html"> 
									<i class="icon-double-angle-right"></i> 主机维度
								</a>
							</li>
							
							<li>
								<a href="recordDatabase.html"> 
									<i class="icon-double-angle-right"></i> 日期维度
								</a>
							</li>
						</ul>
					</li>

				</ul>
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>
		<sitemesh:write property="body" />
		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>

	<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/typeahead-bs2.min.js"></script>
	<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="assets/js/jquery.slimscroll.min.js"></script>
	<script src="assets/js/jquery.sparkline.min.js"></script>
	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>

</body>
</html>
