<#macro page pageModel paginationSize jsFunction>
<#assign current=(pageModel.number+1)>
<#assign begin=1>
<#assign end=1>

<#if current gt 1>
	<#assign begin=(current - 4 )>
	<#if begin lt 1>
		<#assign begin=1>
	</#if>
<#else>
	<#assign begin=1>
</#if>

<#if (current + 4) lt pageModel.totalPages>
	<#assign end=(current + 4)>
<#else>
	<#assign end=pageModel.totalPages>
</#if>

<div class="pagination pagination-centered">
	<input type="hidden" name="paginationCurrentPage" id="paginationCurrentPage" value="${current}">
	<ul class="pagination">
		<#if (pageModel.totalElements gt 0) && (pageModel.hasPrevious())>
			<li>
				<a href="###" onclick="${jsFunction}(1);">首页</a>
			</li>
			<li>
				<a href="###" onclick="${jsFunction}(${current-1});">上一页</a>
			</li>
		<#else>
			<li class="disabled"><a href="###">首页</a></li>
			<li class="disabled"><a href="###">上一页</a></li>
		</#if>

		<#list begin..end as index >
			<#if index gt 0>
				<#if current = index>
					<li class="active">
						<a href="###" onclick="${jsFunction}(${index});">${index}</a>
					</li>
				<#else>
					<li>
						<a href="###" onclick="${jsFunction}(${index});">${index}</a>
					</li>
				</#if>
			</#if>
		</#list>

		<#if (pageModel.totalElements gt 0 && pageModel.hasNext())>
			<li>
				<a href="###" onclick="${jsFunction}(${current+1});">下一页</a>
			</li>
			<li>
				<a href="###" onclick="${jsFunction}(${pageModel.totalPages});">末页</a>
			</li>
		<#else>
			<li class="disabled"><a href="###">下一页</a></li>
			<li class="disabled"><a href="###">末页</a></li>
		</#if>
		【共
		<span style="color: blue;">${pageModel.totalPages}</span>页：
		<span style="color: blue;">${pageModel.totalElements}</span>
		条记录.
		</span>】
	</ul>
</div>
</#macro>