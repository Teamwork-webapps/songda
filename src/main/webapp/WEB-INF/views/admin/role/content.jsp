<#import "admin/common/pagination.jsp" as pagination>
<table class="table table-hover general-table table-bordered">
	<thead>
		<tr>
			<th>#</th>
			<th>代码</th>
			<th>名称</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<#if roleList?? && roleList?size gt 0>
			<#list roleList as role>
				<tr>
					<td>${role_index + 1}</td>
					<td>${role.code}</td>
					<td>${role.remark}</td>
					<td></td>
				</tr>
			</#list>
		<#else>
			<tr>
				<td colspan="4">暂无信息</td>
			</tr>		
		</#if>
	</tbody>
</table>
