<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp" %>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-display4" />
  	<c:param name="title" value="수강 과목 조회" />
  	<c:param name="firstname" value="과목관리" />
  	<c:param name="lastname" value="수강과목조회" />
</c:import>

<div class="content">
	<div class="card mb-0">
		<div class="card-header header-elements-inline bg-white">
			<h4 class="card-title font-weight-bold">
				<i class="icon-list mr-2"></i>수강 과목 목록
			</h4>
			<div class="header-elements">
				<a href="/inbeea/organization/employee/regist" class="btn bg-primary"><i class="icon-user-plus mr-2"></i>과목 등록</a>
			</div>
		</div>
		<div class="card-body">
			<div class="d-flex mt-1 mb-2">
				<label class="col-form-label font-weight-bold mr-3">검색조건 <i class="icon-arrow-right13"></i></label>
				<div class="mr-3">
					<select class="form-control form-control-select2" name="subjectGroup" data-width="200">
						<option value="0">- 전 체 -</option>
						<c:forEach var="group" items="${subjectGroups}" varStatus="status">
							<option value="${group.id}">${group.name}</option>
						</c:forEach>
					</select>
				</div>
				<button id="searchBtn" class="btn bg-teal-400"><i class="icon-search4 mr-2"></i> 조 회</button>
			</div>
			
			<table class="table table-bordered table-striped table-hover" id="subjectTable">
				<thead class="text-center">
					<tr>
						<th>번호</th>
						<th>과목그룹</th>
						<th>과목명</th>
						<th>대상,<br>정원(명)</th>
						<th>수강기간</th>
						<th>유형</th>
						<th>과목특징</th>
						<th>강사명</th>
						<th>재료비 및<br>교구비</th>
						<th></th>
					</tr>
				</thead>
				<tbody class="tbody-xs text-center"></tbody>
			</table>
		</div>
	</div>
</div>

<script>
var SubjectManager = function() {
	var DataTable = {
		ele: "#subjectTable",
		table: null,
		option: {
			columns: [{
		    	width: "6%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    },{ 
		    	width: "8%",
		    	data: "subjectGroup.name" 
		    },{ 
		    	width: "15%",
		    	data: "name" 
		    },{
		    	width: "8%",
		    	data: "target" 
		    },{
		    	width: "10%",
		    	render: function(data, type, row, meta) {
		    		return row.period + "<br>" + row.time;
		    	}
		    },{ 
		    	width: "6%",
		    	data: "week" 
		    },{ 
		    	width: "26%",
		    	data: "description" 
		    },{ 
		    	width: "8%",
		    	data: "teacher"
		    },{ 
		    	width: "8%",
		    	data: "costDesc" 
		    },{
		    	width: "5%",
		    	render: function(data, type, row, meta) {
		    		return '<button type="button" class="btn bg-primary-400 btn-sm" ' +
		    			'onClick="SubjectManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>'
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.order(this.ele, this.option, 2, " _TOTAL_ 개의 과목이 있습니다.");
			this.search();
		},
		search: function() {
			var param = new Object();
			param.groupId = $("select[name=subjectGroup]").val();
			Datatables.rowsAdd(this.table, contextPath + "/subject/search", param);
		}
	}
	
	var searchControl = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});
	}
	
	return {
		init: function() {
			DataTable.init();
			searchControl();
		}
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	SubjectManager.init();
});
</script>