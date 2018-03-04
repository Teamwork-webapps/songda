<!--sidebar nav start-->
<ul class="nav nav-pills nav-stacked custom-nav">
	<li class="active" id="menuPageHome">
		<a href="#" onclick="gotoPageUnPrId(this);" data-url="${webRoot}admin/dashboard">
			<i class="fa fa-home"></i>
			<span>实时统计</span>
		</a>
	</li>
	<li class="menu-list" id="sysManager">
		<a href="">
			<i class="fa fa-laptop"></i>
			<span>系统管理</span>
		</a>
		<ul class="sub-menu-list">
			<li id="adminUserIndex">
				<a href="#" onclick="gotoPage(this,'sysManager');" 
					data-url="${webRoot}admin/user/index">用户管理</a>
			</li>
			<li id="adminRoleIndex">
				<a href="#" onclick="gotoPage(this,'sysManager');" 
					data-url="${webRoot}admin/role/index">角色管理</a>
			</li>
		</ul>
	</li>
</ul>

<!--sidebar nav end-->