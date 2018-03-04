<form id="adminUserEditForm" method="post" action="${webRoot}admin/user/save">
<div class="row">
	<table class="table">
		<tr>
			<th>账号</th>
			<td>${user.account}</td>
			<th></th>
			<td></td>
		</tr>
		<tr>
			<th>最后登陆日期</th>
			<td><#if user.lastLoginTime??>
				${user.lastLoginTime?string("yyyy-MM-dd HH:mm")!""} </#if></td>
			<th>注册日期</th>
			<td><#if user.createTime??> ${user.createTime?string("yyyy-MM-dd HH:mm")!""}
				</#if></td>
		</tr>
		<tr>
			<th>状态</th>
			<td><select name="status" id="status"
				class="form-control input-md">
					<option value="">请选择</option> <#list statusList as status>
					<option value="${status}">${status}</option> </#list>
			</select></td>
			<th></th>
			<td></td>
		</tr>
		<tr>
			<th>角色列表</th>
			<td colspan="3"><#if roleList?? && roleList?size gt 0> <#list
				roleList as role>
				<div class="checkbox">
					<label> <input type="checkbox" id="roles" name="roles"
						value="${role.code}"<#if
						user.roles?seq_contains(role)> checked="checked" </#if> />
						${role.remark}
					</label>
				</div> </#list> </#if>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<button type="submit" class="btn btn-info btn-block" id="adminUserEditFormSubmit">提交</button>
			</td>
		</tr>
		
	</table>
</div>
</form>
<script>
	$(document).ready(function(){
		//企业用户表单校验
		$('#adminUserEditForm').bootstrapValidator()
		.on('success.form.bv', function(e) {
		       // Prevent form submission
		       e.preventDefault();
		       // Get the form instance
		       var $form = $(e.target);
		       // Get the BootstrapValidator instance
		       var bv = $form.data('bootstrapValidator');
		       // Use Ajax to submit form data
		       $("#adminUserEditForm").ajaxSubmit({
		       	beforeSend: function ( xhr ) {
		   			$("#adminUserEditFormSubmit").attr({ disabled: "disabled" });
		   		},
		   		success: function(retMsg) { // data 保存提交后返回的数据，一般为 json 数据
		   			if(retMsg.result == 'T'){
		   				//成功
		   				//关闭弹出框
		   				//刷新列表
		   				doSearch();
		   				showSysCommonMessage("提示",retMsg.msg);
		   			}else{
		   				//失败
		   				$("#adminUserEditFormSubmit").removeAttr("disabled");
		   			}
		           }
		   	});
		});
	});
	
</script>
