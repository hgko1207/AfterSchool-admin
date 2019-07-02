<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp" %>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-display4" />
  	<c:param name="title" value="수강 신청 조회" />
  	<c:param name="firstname" value="수강관리" />
  	<c:param name="lastname" value="수강신청조회" />
</c:import>

<div class="content">
	<div class="card mb-0">
		<div class="card-header header-elements-inline bg-white">
			<h4 class="card-title font-weight-bold">
				<i class="icon-list mr-2"></i>신청된 수강 목록
			</h4>
			<div class="header-elements">
			
			</div>
		</div>
		<div class="card-body">
			<div class="d-flex mt-1 mb-2">
				<label class="col-form-label font-weight-bold mr-3">검색조건 <i class="icon-arrow-right13"></i></label>
				<div class="mr-2">
					<select class="form-control select-search" name="subject" data-width="340">
						<option value="">- 전 체 -</option>
						<c:forEach var="subject" items="${subjects}" varStatus="status">
							<option value="${subject.id}">
								${subject.name} - ${subject.week}
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="mr-2">
					<select class="form-control select-search" name="school" data-width="200">
						<option value="">- 전 체 -</option>
						<c:forEach var="school" items="${schools}" varStatus="status">
							<option value="${school}">${school}</option>
						</c:forEach>
					</select>
				</div>
				<div class="mr-3">
					<select class="form-control form-control-select2" name="grade" data-width="160">
						<option value="">- 전 체 -</option>
						<c:forEach var="item" begin="1" end="6" step="1">
							<option value="${item}">${item} 학년</option>
						</c:forEach>
					</select>
				</div>
				<button id="searchBtn" class="btn bg-info-600"><i class="icon-search4 mr-2"></i> 조 회</button>
			</div>
			
			<table class="table table-bordered table-striped table-hover" id="applyTable">
				<thead class="text-center bg-slate-400">
					<tr>
						<th>번호</th>
						<th>과목</th>
						<th>수강기간</th>
						<th>운영시간</th>
						<th>유형</th>
						<th>강사명</th>
						<th>학생명</th>
						<th>소속(학교 명)</th>
						<th>학년 반 번호</th>
						<th>연락처</th>
					</tr>
				</thead>
				<tbody class="tbody-xs text-center"></tbody>
			</table>
		</div>
	</div>
</div>

<script>
var ApplyManager = function() {
	var DataTable = {
		ele: "#applyTable",
		table: null,
		option: {
			columns: [{
		    	width: "6%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    },
		    { data: "subject.name" },
		    { data: "subject.period" },
		    { data: "subject.time" },
		    { data: "subject.week" },
		    { data: "subject.teacher" },
		    { data: "student.name" },
		    { data: "student.school" },
		    { 
		    	render: function(data, type, row, meta) {
		    		return row.student.grade + "학년 " + row.student.classType + "반 " + row.student.number + "번";
		    	}
		    },
		    { 
	    		render: function(data, type, row, meta) {
	    			return row.student.tel.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
	    		} 
		    }]
		},
		init: function() {
			this.table = Datatables.apply(this.ele, this.option, " _TOTAL_ 개의 수강신청이 있습니다.");
			this.search();
		},
		search: function() {
			var param = new Object();
			param.subjectId = $("select[name=subject]").val();
			param.school = $("select[name=school]").val();
			param.grade = $("select[name=grade]").val();
			Datatables.rowsAdd(this.table, contextPath + "/apply/search", param);
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
	ApplyManager.init();
});
</script>