<div class="row breadcrumb" id="adminUserSearchPanel">
	<form class="form-inline" role="form" id="adminUserSearchForm">
		<div class="form-group">
			<label class="" for="account">账号:</label> <input type="text"
				class="form-control" placeholder="账号" id="search_LIKE_account_true"
				name="search_LIKE_account_true">
		</div>
		<button type="button" id="search_button" onclick="doSearch();"
			class="btn btn-primary">查询</button>
	</form>
</div>
<div class="row breadcrumb" id="adminUserContentPanel"></div>

<script>
	$(document).ready(function(){
		doSearch(1);
	});
	
	function doSearch(pageNum){
		sysCurrentPage = pageNum;
		$('#search_button').attr("disabled",true);
		$("#adminUserSearchForm").ajaxSubmit({
			type: "get",
			url:"${webRoot}admin/user/content",
			data:{"page":pageNum},
			target: "#adminUserContentPanel",
			success: function(data){
				$('#search_button').attr("disabled",false);
			}
		});
	}
	
	function viewUser(id){
		showSysCommonModel("查看用户详情", "${webRoot}admin/user/view/"+id);
	}
	
	function editUser(id){
		showSysCommonModel("编辑用户详情", "${webRoot}admin/user/edit/"+id);
	}
</script>