<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp" %>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-display4" />
  	<c:param name="title" value="수강 과목 조회" />
  	<c:param name="firstname" value="과목관리" />
  	<c:param name="lastname" value="수강과목조회" />
</c:import>

<div class="content">
	<div id="list_content" class="card mb-0">
		<div class="card-header header-elements-inline bg-white">
			<h4 class="card-title font-weight-bold">
				<i class="icon-list mr-2"></i>수강 과목 목록
			</h4>
			<div class="header-elements">
				<%-- <a href="${pageContext.request.contextPath}/subject/regist" class="btn bg-primary"><i class="icon-add mr-2"></i>과목 등록</a> --%>
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
				<button id="searchBtn" class="btn bg-info-600"><i class="icon-search4 mr-2"></i> 조 회</button>
			</div>
			
			<table class="table table-bordered table-striped table-hover" id="subjectTable">
				<thead class="text-center bg-slate-400">
					<tr>
						<th>번호</th>
						<th>과목그룹</th>
						<th>과목명</th>
						<th>대상,<br>정원(명)</th>
						<th>수강기간</th>
						<th>운영시간</th>
						<th>유형</th>
						<th>강사명</th>
						<th>재료비 및<br>교구비</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody class="tbody-xs text-center"></tbody>
			</table>
		</div>
	</div>
</div>

<div id="updateSubjectModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title">
					<i class="icon-pencil6 mr-2"></i>과목 정보 수정
				</h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<form id="updateSubjectForm" action="${pageContext.request.contextPath}/subject/update">
				<div class="modal-body">
					<input type="hidden" name="id">
					<div class="form-group row">
						<div class="col-md-4 ml-2">
							<label class="font-weight-bold">그룹 선택 :</label>
							<select class="form-control form-control-select2" name="groupId">
								<c:forEach var="subjectGroup" items="${subjectGroups}" varStatus="status">
									<option value="${subjectGroup.id}">${subjectGroup.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 ml-auto px-3">
							<div class="form-group">
								<label class="font-weight-bold">이 름 :</label>
								<input type="text" class="form-control" name="name" placeholder="과목 이름" readonly>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="font-weight-bold">대 상 :</label>
										<select class="form-control form-control-select2" name="targetType">
											<c:forEach var="targetType" items="${targetTypes}" varStatus="status">
												<option value="${targetType}">${targetType.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="font-weight-bold">대상 학년 :</label>
										<select class="form-control form-control-select2" name="gradeType">
											<c:forEach var="gradeType" items="${gradeTypes}" varStatus="status">
												<option value="${gradeType}">${gradeType.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="font-weight-bold">운영 시간 :</label>
										<input type="text" class="form-control" name="time" placeholder="예) 16:00-18:00" required>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="font-weight-bold">유 형 :</label>
										<input type="text" class="form-control" name="week" placeholder="예) 화,목,토" required>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="font-weight-bold">수업 장소 :</label>
								<textarea rows="3" class="form-control" name="location" placeholder="지명(주소)" required></textarea>
							</div>
						</div>
						<div class="col-md-6 mr-auto px-3">
							<div class="form-group">
								<label class="font-weight-bold">강 사 :</label>
								<input type="text" class="form-control" name="teacher" placeholder="강사 이름" required>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="font-weight-bold">정 원(명) :</label>
										<input type="number" class="form-control" name="fixedNumber" required>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="font-weight-bold">수강 기간 :</label>
										<input type="text" class="form-control" name="period" placeholder="예) 7/1~9/28" required>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="font-weight-bold">재료비 및 교구비 :</label>
								<input type="text" class="form-control" name="costDesc" placeholder="예) 무료 또는 1만원" required>
							</div>
							<div class="form-group">
								<label class="font-weight-bold">과목 특징 :</label>
								<textarea rows="4" class="form-control" name="description" placeholder="과목 특징 설명" required></textarea>
							</div>
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
		    	width: "24%",
		    	data: "name" 
		    },{
		    	width: "8%",
		    	data: "target" 
		    },{
		    	width: "8%",
		    	data: "period" 
		    },{
		    	width: "8%",
		    	data: "time" 
		    },{ 
		    	width: "9%",
		    	data: "week" 
		    },{ 
		    	width: "12%",
		    	data: "teacher"
		    },{ 
		    	width: "9%",
		    	data: "costDesc" 
		    },{
		    	width: "8%",
		    	render: function(data, type, row, meta) {
    				return '<button type="button" class="btn btn-outline bg-primary text-primary-800 btn-sm" ' +
			    			'onClick="SubjectManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>' +
    					'<button type="button" class="btn btn-outline bg-danger text-danger-800 btn-sm" ' + 
		    				'onClick="SubjectManager._delete(' + row.id + ')"><i class="icon-trash"></i></button>';
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
	
	var controlData = function() {
		$('#updateSubjectForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		 	updateModalCommon(url, form.serializeObject(), "과목", DataTable, "updateSubjectModal");
		}); 
	}
	
	return {
		init: function() {
			DataTable.init();
			searchControl();
			controlData();
		},
		modal: function(id) {
			$.ajax({
	    		url: contextPath + "/subject/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateSubjectForm input[name="id"]').val(response.id);
	    			$('#updateSubjectForm select[name="groupId"]').val(response.subjectGroup.id).trigger('change');
	    			$('#updateSubjectForm input[name="name"]').val(response.name);
	    			$('#updateSubjectForm input[name="teacher"]').val(response.teacher);
	    			$('#updateSubjectForm select[name="targetType"]').val(response.targetType).trigger('change');
	    			$('#updateSubjectForm select[name="gradeType"]').val(response.gradeType).trigger('change');
	    			$('#updateSubjectForm input[name="fixedNumber"]').val(response.fixedNumber);
	    			$('#updateSubjectForm input[name="period"]').val(response.period);
	    			$('#updateSubjectForm input[name="time"]').val(response.time);
	    			$('#updateSubjectForm input[name="week"]').val(response.week);
	    			$('#updateSubjectForm input[name="costDesc"]').val(response.costDesc);
	    			$('#updateSubjectForm textarea[name="location"]').val(response.location);
	    			$('#updateSubjectForm textarea[name="description"]').val(response.description);
	    			$("#updateSubjectModal").modal();
	           	}
	    	}); 
		},
		_delete: function(id) {
        	deleteCommon(contextPath + "/subject/delete", id, "과목", DataTable);
        }
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	SubjectManager.init();
});
</script>