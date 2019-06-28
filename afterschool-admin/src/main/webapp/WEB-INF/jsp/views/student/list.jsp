<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp" %>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-users" />
  	<c:param name="title" value="학생 조회" />
  	<c:param name="firstname" value="학생관리" />
  	<c:param name="lastname" value="학생조회" />
</c:import>

<div class="content">
	<div class="card mb-0">
		<div class="card-header header-elements-inline bg-white">
			<h4 class="card-title font-weight-bold">
				<i class="icon-list mr-2"></i>등록된 학생 목록
			</h4>
			<div class="header-elements">
			
			</div>
		</div>
		<div class="card-body">
			<div class="d-flex mt-1 mb-2">
				<label class="col-form-label font-weight-bold mr-3">검색조건 <i class="icon-arrow-right13"></i></label>
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
				<button id="searchBtn" class="btn bg-teal-400"><i class="icon-search4 mr-2"></i> 조 회</button>
			</div>
			
			<table class="table table-bordered table-striped table-hover" id="studentTable">
				<thead class="text-center">
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>소속(학교 명)</th>
						<th>학년</th>
						<th>학급(반)</th>
						<th>번호</th>
						<th>연락처</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody class="tbody-xs text-center"></tbody>
			</table>
		</div>
	</div>
</div>

<div id="updateStudentModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title">
					<i class="icon-pencil6 mr-2"></i>학생 정보 수정
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<form id="updateStudentForm" action="${pageContext.request.contextPath}/student/update" class="form-horizontal">
				<div class="modal-body">
					<input type="hidden" name="id">
					<div class="form-group row mb-2">
						<label class="col-form-label col-md-4 text-md-right font-weight-bold">이 름</label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="name" readonly>
						</div>
					</div>
					<div class="form-group row mb-2">
						<label class="col-form-label col-md-4 text-md-right font-weight-bold">학 교</label>
						<div class="col-md-6">
							<select class="form-control select-search" name="school">
								<c:forEach var="school" items="${schools}" varStatus="status">
									<option value="${school}">${school}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row mb-2">
						<label class="col-form-label col-md-4 text-md-right font-weight-bold">학 년</label>
						<div class="col-md-6">
							<select class="form-control form-control-select2" name="grade">
								<c:forEach var="item" begin="1" end="6" step="1">
									<option value="${item}">${item} 학년</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row mb-2">
						<label class="col-form-label col-md-4 text-md-right font-weight-bold">반</label>
						<div class="col-md-6">
							<select class="form-control form-control-select2" name="classType">
								<c:forEach var="item" begin="1" end="10" step="1">
									<option value="${item}">${item} 반</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row mb-2">
						<label class="col-form-label col-md-4 text-md-right font-weight-bold">번 호</label>
						<div class="col-md-6">
							<select class="form-control form-control-select2" name="number">
								<c:forEach var="item" begin="1" end="40" step="1">
									<option value="${item}">${item} 번</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row mb-2">
						<label class="col-form-label col-md-4 text-md-right font-weight-bold">핸드폰</label>
						<div class="col-md-6">
							<input type="text" class="form-control" name="tel" maxlength="11" 
								autocomplete="off" placeholder="'-' 없이 입력하세요." required >
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary mr-2 px-3"><i class="icon-pencil5 mr-2"></i>수 정</button>
					<button type="button" class="btn btn-light px-3" data-dismiss="modal"><i class="icon-cross2 mr-2"></i>닫 기</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
var StudentManager = function() {
	var DataTable = {
		ele: "#studentTable",
		table: null,
		option: {
			columns: [{
		    	width: "6%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    },
		    { data: "name" },
		    { data: "school" },
		    {
		    	render: function(data, type, row, meta) {
		    		return row.grade + " 학년";
		    	}
		    },
		    {
		    	render: function(data, type, row, meta) {
		    		return row.classType + " 반";
		    	}
		    },
		    {
		    	render: function(data, type, row, meta) {
		    		return row.number + " 번";
		    	}
		    },
		    { data: "tel" },
		    {
		    	width: "8 %",
		    	render: function(data, type, row, meta) {
		    		return '<button type="button" class="btn bg-primary-400 btn-sm" ' +
		    			'onClick="StudentManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>'
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.action(this.ele, this.option, " _TOTAL_ 명의 학생이 있습니다.");
			this.search();
		},
		search: function() {
			var param = new Object();
			param.school = $("select[name=school]").val();
			param.grade = $("select[name=grade]").val();
			Datatables.rowsAdd(this.table, contextPath + "/student/search", param);
		}
	}
	
	var searchControl = function() {
		$("#searchBtn").click(function() {
			DataTable.search();
		});
	}
	
	var controlStudentData = function() {
		$('#updateStudentForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		 	updateModalCommon(url, form.serializeObject(), "학생", DataTable, "updateStudentModal");
		}); 
	}
	
	return {
		init: function() {
			DataTable.init();
			searchControl();
			controlStudentData();
		},
	 	modal: function(id) {
	 		$.ajax({
	    		url: contextPath + "/student/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateStudentForm input[name="id"]').val(response.id);
	    			$('#updateStudentForm input[name="name"]').val(response.name);
	    			$('#updateStudentForm select[name="school"]').val(response.school).trigger('change');
	    			$('#updateStudentForm select[name="grade"]').val(response.grade).trigger('change');
	    			$('#updateStudentForm select[name="classType"]').val(response.classType).trigger('change');
	    			$('#updateStudentForm select[name="number"]').val(response.number).trigger('change');
	    			$('#updateStudentForm input[name="tel"]').val(response.tel);
	    			$("#updateStudentModal").modal();
	           	}
	    	}); 
	 	}
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	StudentManager.init();
});
</script>