<#import "admin/common/pagination.jsp" as pagination>
<table class="table table-hover general-table table-bordered">
	<thead>
		<tr>
			<th>#</th>
			<th>账号</th>
			<th>状态</th>
			<th>最后登陆日期</th>
			<th>注册日期</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<#if userPage?? && userPage.content?size gt 0>
			<#list userPage.content as user>
				<tr>
					<td>${user_index + 1 + (userPage.number * userPage.size)}</td>
					<td>${user.account}</td>
					<td>${user.status}</td>
					<td>
						<#if user.lastLoginTime??>
							${user.lastLoginTime?string("yyyy-MM-dd HH:mm")!""}
						</#if>
					</td>
					<td>
						<#if user.createTime??>
							${user.createTime?string("yyyy-MM-dd HH:mm")!""}
						</#if>
					</td>
					<td>
						<button class="btn btn-success" type="button" onclick="viewUser('${user.id}');">
							<i class="fa fa-eye"></i>&nbsp;查看 
						</button>
						<button class="btn btn-warning" type="button" onclick="editUser('${user.id}');">
							<i class="fa fa-edit"></i>&nbsp;编辑 
						</button>
					</td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td colspan="7">暂无信息</td>
			</tr>		
		</#if>
	</tbody>
</table>
<#if userPage?? && userPage.content?size gt 0>
	<@pagination.page pageModel=userPage paginationSize=userPage.size jsFunction="doSearch"/>
</#if>
