<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/select2.min.css">		
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/AdminLTE.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/_all-skins.min.css">		
<header class="main-header">
	<a href="<%=request.getContextPath() %>/home" class="logo">
		<span class="logo-mini"><b>CRM</b></span>
		<span class="logo-lg"><b>Admin</b>CRM</span>
	</a>
	<nav class="navbar navbar-static-top" role="navigation">
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
			<span class="sr-only">Toggle navigation</span>
		</a>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">				
				<!-- <li class="dropdown notifications-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-bell-o"></i>
						<span class="label label-warning">10</span>
					</a>
					<ul class="dropdown-menu">
						<li class="header">You have 10 notifications</li>
						<li>
							<ul class="menu">
								<li>
									<a href="#">
										<i class="fa fa-users text-aqua"></i> 5 new members joined today
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the page and may cause design problems
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa fa-users text-red"></i> 5 new members joined
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa fa-shopping-cart text-green"></i> 25 sales made
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa fa-user text-red"></i> You changed your username
									</a>
								</li>
							</ul>
						</li>
						<li class="footer"><a href="#">View all</a></li>
					</ul>
				</li> -->				
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img src="<%=request.getContextPath() %>/resources/admin/images/user2-160x160.jpg" class="user-image" alt="User Image">
						<span class="hidden-xs"><%= session.getAttribute("shownameadmin") %></span>
					</a>
					<ul class="dropdown-menu">
						<li class="user-header">
							<img src="<%=request.getContextPath() %>/resources/admin/images/user2-160x160.jpg" class="img-circle" alt="User Image">
							<p>
								<%= session.getAttribute("shownameadmin") %>
								<!-- <small>Member since Nov. 2012</small> -->
							</p>
						</li>
						<!-- <li class="user-body">
							<div class="col-xs-4 text-center">
								<a href="#">Followers</a>
							</div>
							<div class="col-xs-4 text-center">
								<a href="#">Sales</a>
							</div>
							<div class="col-xs-4 text-center">
								<a href="#">Friends</a>
							</div>
						</li> -->
						<li class="user-footer">
							<div class="pull-left">
								<a href="<%= request.getContextPath() %>/change_password" class="btn btn-default btn-flat">Change Password</a>
							</div>
							<div class="pull-right">
								<a href="<%= request.getContextPath() %>/logoutadmin" class="btn btn-default btn-flat">Sign out</a>
							</div>
						</li>
					</ul>
				</li>			
			</ul>
		</div>
	</nav>
</header>