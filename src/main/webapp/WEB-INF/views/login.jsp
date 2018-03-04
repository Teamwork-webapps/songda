<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>Login</title>

    <link href="${webRoot}static/admin/css/style.css" rel="stylesheet">
    <link href="${webRoot}static/admin/css/style-responsive.css" rel="stylesheet">
    <link href="${webRoot}static/admin/js/bootstrapvalidator/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${webRoot}static/admin/js/html5shiv.js"></script>
    <script src="${webRoot}static/admin/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-body">

<div class="container">

    <form id="userLoginForm" class="form-signin" action="${webRoot}userLogin" method="post">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">Sign In</h1>
            <img src="${webRoot}static/admin/images/login-logo.png" alt=""/>
        </div>
        <div class="login-wrap">
            <input type="text" name="loginName" id="loginName" class="form-control" placeholder="User ID" autofocus>
            <input type="password" name="password" id="password" class="form-control" placeholder="Password">

            <button class="btn btn-lg btn-login btn-block" type="submit" id="userLoginFormSubmit">
                <i class="fa fa-check"></i>
            </button>

            <div class="registration">
                Not a member yet?
                <a class="" href="registration.html">
                    Signup
                </a>
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
                <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> Forgot Password?</a>
                </span>
            </label>
        </div>

        <!-- Modal -->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Forgot Password ?</h4>
                    </div>
                    <div class="modal-body">
                        <p>Enter your e-mail address below to reset your password.</p>
                        <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">

                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                        <button class="btn btn-primary" type="button">Submit</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal -->

    </form>

</div>



<!-- Placed js at the end of the document so the pages load faster -->

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${webRoot}static/admin/js/jquery-1.10.2.min.js"></script>
<script src="${webRoot}static/admin/js/bootstrap.min.js"></script>
<script src="${webRoot}static/admin/js/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script src="${webRoot}static/admin/js/modernizr.min.js"></script>
<script src="${webRoot}static/admin/js/jquery.form.js"></script>

<script>
	$(document).ready(function(){
		$("#loginName").focus();
		
		//企业用户表单校验
		$('#userLoginForm').bootstrapValidator()
		.on('success.form.bv', function(e) {
	        // Prevent form submission
	        e.preventDefault();
	        // Get the form instance
	        var $form = $(e.target);
	        // Get the BootstrapValidator instance
	        var bv = $form.data('bootstrapValidator');
	        // Use Ajax to submit form data
	        $("#userLoginForm").ajaxSubmit({
		        	beforeSend: function ( xhr ) {
		    			$("#userLoginFormSubmit").attr({ disabled: "disabled" });
		    		},
		    		success: function(retMsg) { // data 保存提交后返回的数据，一般为 json 数据
		    			if(retMsg.result == 'T'){
		    				//成功，关闭右侧框。
		    				window.location.href="${webRoot}page";
		    			}else{
		    				//失败，提示信息
		    				$("#userLoginFormSubmit").removeAttr("disabled");
		    			}
				}
	    		});
	    });
	});
	
</script>

</body>
</html>
