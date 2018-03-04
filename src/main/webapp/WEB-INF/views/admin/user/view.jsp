<div class="row">
	<table class="table">
		<tr>
			<th>账号</th>
			<td>${user.account}</td>
			<th>状态</th>
			<td>${user.status}</td>
		</tr>
		<tr>
			<th>最后登陆日期</th>
			<td>
				<#if user.lastLoginTime??>
					${user.lastLoginTime?string("yyyy-MM-dd HH:mm")!""}
				</#if>
			</td>
			<th>注册日期</th>
			<td>
				<#if user.createTime??>
					${user.createTime?string("yyyy-MM-dd HH:mm")!""}
				</#if>
			</td>
		</tr>
		<tr>
			<th>角色列表</th>
			<td colspan="3">
				<#if user.roles?? && user.roles?size gt 0>
					<#list user.roles as role>
						<#if role_index != 0>
							,
						</#if>
						${role.remark}
					</#list>
				</#if>
			</td>
		</tr>
	</table>
</div>
 