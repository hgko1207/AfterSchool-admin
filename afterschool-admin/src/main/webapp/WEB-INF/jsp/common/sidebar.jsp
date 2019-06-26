<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<div class="sidebar sidebar-dark sidebar-main sidebar-expand-md">
	<!-- Sidebar mobile toggler -->
	<div class="sidebar-mobile-toggler text-center">
		<a href="#" class="sidebar-mobile-main-toggle">
			<i class="icon-arrow-left8"></i>
		</a>
		Navigation
		<a href="#" class="sidebar-mobile-expand">
			<i class="icon-screen-full"></i>
			<i class="icon-screen-normal"></i>
		</a>
	</div>
	<!-- /sidebar mobile toggler -->
	
	<!-- Sidebar content -->
	<div class="sidebar-content">
		<div class="card card-sidebar-mobile">
			<ul class="nav nav-sidebar" data-nav-type="accordion">
				<li class="nav-item-header">
					<div class="text-uppercase font-size-md line-height-md">목록</div> 
					<i class="icon-menu" title="Main"></i>
				</li>
				<li id="student" class="nav-item nav-item-submenu">
					<a href="#" class="nav-link"><i class="icon-users"></i> <span>학생 관리</span></a>
					<ul class="nav nav-group-sub" data-submenu-title="Layouts">
						<li id="student_list" class="nav-item"><a href="${pageContext.request.contextPath}/student/list" class="nav-link">
							<i class="icon-list"></i><span>학생 조회</span></a>
						</li>
					</ul>
				</li>
				<li id="apply" class="nav-item nav-item-submenu">
					<a href="#" class="nav-link"><i class="icon-stack-empty"></i> <span>수강 관리</span></a>
					<ul class="nav nav-group-sub" data-submenu-title="Layouts">
						<li id="apply_list" class="nav-item"><a href="${pageContext.request.contextPath}/apply/list" class="nav-link">
							<i class="icon-list"></i><span>수강 신청 조회</span></a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>

<script>
	var pathName = this.location.pathname;
	var menuName = pathName.split("/")[2];
    var subMenuName = pathName.split("/")[3];
    
    if (subMenuName) {
		$("li#" + menuName).addClass("nav-item-expanded nav-item-open");
		$("li#" + menuName + "_" + subMenuName).children().addClass("active");
	} else {
		 $("li#" + menuName).children().addClass("active");
	}
</script>