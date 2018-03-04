<div class="row breadcrumb" id="adminRoleSearchPanel">
	<form class="form-inline" role="form" id="adminRoleSearchForm">
		<div class="form-group">
			<label class="" for="account">名称:</label>
			<input type="text" class="form-control" placeholder="名称"
				id="search_LIKE_remark_true" name="search_LIKE_remark_true">
		</div>
		<button type="button" id="search_button" onclick="doSearch();" class="btn btn-primary">查询</button>
	</form>
</div>
<div class="row breadcrumb" id="adminRoleContentPanel"></div>
<script>
	$(document).ready(function(){
		doSearch(1);
	});
	
	function doSearch(pageNum){
		$('#search_button').attr("disabled",true);
		$("#adminRoleSearchForm").ajaxSubmit({
			type: "get",
			url:"${webRoot}admin/role/content",
			target: "#adminRoleContentPanel",
			success: function(data){
				$('#search_button').attr("disabled",false);
			}
		});
	}
</script>