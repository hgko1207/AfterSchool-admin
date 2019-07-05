<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLib.jsp"%>

<c:import url="/WEB-INF/jsp/common/pageHeader.jsp" >
  	<c:param name="icon" value="icon-user" />
  	<c:param name="title" value="학교 관리" />
	<c:param name="firstname" value="데이터관리" />
  	<c:param name="lastname" value="학교관리" />
</c:import>

<div class="content">
	<div class="row">
		<div class="col-md-5">
			<div class="card">
				<div class="card-header bg-white">
					<h6 class="card-title">
						<i class="icon-cog3 mr-2"></i>학교 설정
					</h6>
				</div>
				<form id="registForm" action="${pageContext.request.contextPath}/setting/school/regist">
					<div class="card-body">
						<div class="form-group row mt-2">
							<label class="col-md-3 col-form-label text-md-right">학교 이름 :</label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="name" placeholder="초등학교, 중학교를 빼고 작성" autocomplete="off" required>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-3 col-form-label text-md-right">유형 :</label>
							<div class="col-md-7">
								<select class="form-control form-control-select2" name="schoolType" required>
									<c:forEach var="type" items="${schoolTypes}" varStatus="status">
										<option value="${type}">${type.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group row last mb-2">
							<label class="col-md-3 col-form-label text-md-right">학생 수 :</label>
							<div class="col-md-7">
								<input type="number" class="form-control" name="number" placeholder="학생 수" autocomplete="off" required>
							</div>
						</div>
					</div>
					<div class="card-footer bg-white d-flex justify-content-center align-items-center">
						<button type="submit" class="btn bg-teal-400 px-3"><i class="icon-plus-circle2 mr-2"></i>학교 추가</button>
					</div>
				</form>
			</div>
		</div>
		<div class="col-md-7">
			<div class="card">
				<div class="card-header bg-white">
					<h6 class="card-title">
						<i class="icon-list mr-2"></i>학교 리스트
					</h6>
				</div>
				<div class="card-body">
					<table class="table table-bordered table-striped table-hover" id="schoolTable">
						<thead class="text-center bg-slate-400">
							<tr>
								<th>번호</th>
								<th>이름</th>
								<th>유형</th>
								<th>학생 수</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody class="tbody-sm text-center"></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
var SettingManager = function() {
	var Datatable = {
		ele: "#schoolTable",
		table: null,
		option: {
			columns: [{
		    	width: "12%",
		    	render: function(data, type, row, meta) {
		    		return meta.row + 1
		    	}
		    }, 
		    { data: "name" }, 
		    { data: "schoolType" }, 
		    { data: "number" }, 
		    {
		    	width: "14%",
		    	render: function(data, type, row, meta) {
		    		return '<button type="button" class="btn btn-outline bg-primary text-primary-800 btn-sm" ' +
		    			'onClick="SettingManager.modal(' + row.id + ')"><i class="icon-pencil7"></i></button>' +
    					'<button type="button" class="btn btn-outline bg-danger text-danger-800 btn-sm" ' + 
		    				'onClick="SettingManager._delete(' + row.id + ')"><i class="icon-trash"></i></button>'
		    	}
		    }]
		},
		init: function() {
			this.table = Datatables.order(this.ele, this.option, 0);
			this.search();
		},
		search: function() {
			Datatables.rowsAdd(this.table, contextPath + "/setting/school/search", new Object());
		}
	}
	
	var controlData = function() {
		$('#registForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		    registCommon(url, form.serializeObject(), "학교", SettingManager);
		});
		
		$('#updateForm').submit(function(e) {
			e.preventDefault();
			var form = $(this);
			var url = form.attr('action');
			
		 	updateModalCommon(url, form.serializeObject(), "학교", Datatable, "updateGroupModal");
		}); 
	}
	
	return {
        init: function() {
        	Datatable.init();
        	controlData();
        },
        _delete: function(id) {
        	deleteCommon(contextPath + "/setting/school/delete", id, "학교", Datatable);
        },
        modal: function(id) {
        	$.ajax({
	    		url: contextPath + "/setting/school/get",
	    		type: "GET",
	    		data: {"id": id},
	    		success: function(response) {
	    			$('#updateForm input[name="id"]').val(response.id);
	    			$('#updateForm input[name="name"]').val(response.name);
	    			$('#updateForm input[name="number"]').val(response.number);
	    			$("#updateModal").modal();
	           	}
	    	}); 
        },
        success: function() {
        	Datatable.search();
        	$('#registForm input[name="name"]').val("");
        	$('#registForm input[name="number"]').val("");
        }
	}
}();

document.addEventListener('DOMContentLoaded', function() {
	SettingManager.init();
});
</script>