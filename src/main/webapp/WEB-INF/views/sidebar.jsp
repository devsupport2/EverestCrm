<aside class="main-sidebar">
	<section class="sidebar">		
		<ul class="sidebar-menu">
			<li class="header text-center">ADMIN MAIN MENU</li>
			<li class="treeview" id="manage">
				<a href="#">
					<i class="fa fa-cogs"></i> <span>Manage</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">					
				<%if((Integer)session.getAttribute("usertypeidadmin") == 1) {%>
					<li id="project"><a href="<%=request.getContextPath() %>/manage_project"><i class="fa fa-circle-o"></i> Project</a></li>
					<li id="enquiry"><a href="<%=request.getContextPath() %>/manage_enquiry"><i class="fa fa-circle-o"></i> Enquiries</a></li>
					<li id="property"><a href="<%=request.getContextPath() %>/manage_property"><i class="fa fa-circle-o"></i> Property</a></li>					
					<li id="user"><a href="<%=request.getContextPath() %>/manage_user"><i class="fa fa-circle-o"></i> User</a></li>
					<li id="workstatus"><a href="<%=request.getContextPath() %>/manage_workstatus"><i class="fa fa-circle-o"></i> Work Status</a></li>
				<%}%>
				<%if(session.getAttribute("accessProject").toString().equals("y") && (Integer)session.getAttribute("usertypeidadmin") == 2) {%>
					<li id="project"><a href="<%=request.getContextPath() %>/manage_project"><i class="fa fa-circle-o"></i> Project</a></li>
				<%}%>
				<%if(session.getAttribute("accessEnquiry").toString().equals("y") && (Integer)session.getAttribute("usertypeidadmin") == 2) {%>
					<li id="enquiry"><a href="<%=request.getContextPath() %>/manage_enquiry"><i class="fa fa-circle-o"></i> Enquiries</a></li>
				<%}%>
				<%if(session.getAttribute("accessProperty").toString().equals("y") && (Integer)session.getAttribute("usertypeidadmin") == 2) {%>
					<li id="property"><a href="<%=request.getContextPath() %>/manage_property"><i class="fa fa-circle-o"></i> Property</a></li>
				<%}%>
				<%if(session.getAttribute("accessUser").toString().equals("y") && (Integer)session.getAttribute("usertypeidadmin") == 2) {%>
					<li id="user"><a href="<%=request.getContextPath() %>/manage_user"><i class="fa fa-circle-o"></i> User</a></li>
				<%}%>
					<li id="projectslider"><a href="<%=request.getContextPath() %>/manage_site_slider"><i class="fa fa-circle-o"></i> Manage Project Slider</a></li>
					<li id="slider"><a href="<%=request.getContextPath() %>/manage_slider_details"><i class="fa fa-circle-o"></i> Manage Home Slider</a></li>
				</ul>
			</li>
			<li class="treeview" id="master">
				<a href="#">
					<i class="fa fa-flag-checkered"></i> <span>Masters</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">				
					<li id="achievement"><a href="<%=request.getContextPath() %>/manage_achievement"><i class="fa fa-circle-o"></i> Achievement</a></li>
					<li id="bank"><a href="<%=request.getContextPath() %>/manage_bank"><i class="fa fa-circle-o"></i> Bank</a></li>
					<li id="category"><a href="<%=request.getContextPath() %>/manage_category"><i class="fa fa-circle-o"></i> Category</a></li>
					<li id="country"><a href="<%=request.getContextPath() %>/manage_country"><i class="fa fa-circle-o"></i> Country</a></li>
					<li id="currency"><a href="<%=request.getContextPath() %>/manage_currency"><i class="fa fa-circle-o"></i> Currency</a></li>
					<li id="designation"><a href="<%=request.getContextPath() %>/manage_designation"><i class="fa fa-circle-o"></i> Designation</a></li>
					<li id="district"><a href="<%=request.getContextPath() %>/manage_district"><i class="fa fa-circle-o"></i> District</a></li>
					<li id="financial_year"><a href="<%=request.getContextPath() %>/manage_financial_year"><i class="fa fa-circle-o"></i> Financial Year</a></li>					
					<li id="location"><a href="<%=request.getContextPath() %>/manage_location"><i class="fa fa-circle-o"></i> Location</a></li>
					<li id="measurement_unit"><a href="<%=request.getContextPath() %>/manage_measurement_unit"><i class="fa fa-circle-o"></i> Measurement Unit</a></li>
					<li id="occupation"><a href="<%=request.getContextPath() %>/manage_occupation"><i class="fa fa-circle-o"></i> Occupation</a></li>
					<li id="payment_schedule"><a href="<%=request.getContextPath() %>/manage_payment_schedule"><i class="fa fa-circle-o"></i> Payment Schedule</a></li>
					<li id="price"><a href="<%=request.getContextPath() %>/manage_price_label"><i class="fa fa-circle-o"></i> Price Label</a></li>
					<li id="range"><a href="<%=request.getContextPath() %>/manage_range"><i class="fa fa-circle-o"></i> Range</a></li>
					<li id="state"><a href="<%=request.getContextPath() %>/manage_state"><i class="fa fa-circle-o"></i> State</a></li>
					<li id="subcategory"><a href="<%=request.getContextPath() %>/manage_subcategory"><i class="fa fa-circle-o"></i> Subcategory</a></li>
					<li id="taluka"><a href="<%=request.getContextPath() %>/manage_taluka"><i class="fa fa-circle-o"></i> Taluka</a></li>
					<li id="feedback"><a href="<%=request.getContextPath() %>/manage_feedback"><i class="fa fa-circle-o"></i> Testimonial</a></li>
					<li id="tax"><a href="<%=request.getContextPath() %>/manage_tax"><i class="fa fa-circle-o"></i> Tax</a></li>
					<li id="type"><a href="<%=request.getContextPath() %>/manage_type"><i class="fa fa-circle-o"></i> Type</a></li>
					<%-- <li id="type"><a href="<%=request.getContextPath() %>/manage_slider"><i class="fa fa-circle-o"></i> Manage Home Slider</a></li> --%>
					
					<%-- <li id="type"><a href="<%=request.getContextPath() %>/manage_site_slider"><i class="fa fa-circle-o"></i> Manage Site Slider</a></li> --%>
				</ul>
			</li>
			<%-- <li class="treeview" id="reports">
				<a href="#">
					<i class="fa fa-flag-checkered"></i> <span>Report</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">					
					<li id="current_funnel"><a href="<%=request.getContextPath() %>/current_funnel"><i class="fa fa-list-alt"></i> Current Funnel</a></li>										
				</ul>
			</li> --%>
			<%-- <li class="treeview" id="imports">
				<a href="#">
					<i class="fa fa-cloud-download"></i> <span>Import</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">					
					<li id="import_products"><a href="<%=request.getContextPath() %>/import_products"><i class="fa fa-upload"></i> Import Products</a></li>										
				</ul>
			</li> --%>
		</ul>
	</section>
</aside>