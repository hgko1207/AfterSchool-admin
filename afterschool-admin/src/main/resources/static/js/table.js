/**
 * 테이블 관련 유틸
 */
$.extend( $.fn.dataTable.defaults, {
	autoWidth: false,
    dom: '<"datatable-header"fl><"datatable-scroll"t><"datatable-footer"ip>',
    language: {
		emptyTable: '데이터가 없습니다.',
		infoEmpty: "",
		info: " _TOTAL_ 개의 데이터가 있습니다.",
	    search: '<span>검색 :</span> _INPUT_',
	    searchPlaceholder: '내용 입력...',
	    lengthMenu: '<span>Show:</span> _MENU_',
	    paginate: { 'first': 'First', 'last': 'Last', 
	    	'next': $('html').attr('dir') == 'rtl' ? '&larr;' : '&rarr;', 'previous': $('html').attr('dir') == 'rtl' ? '&rarr;' : '&larr;' }
	},
	searching: false,
	lengthChange: false,
	pageLength: 10,
	
});

var Datatables = {
	basic: function(id, tableOption, info) {
		var table = $(id).DataTable({
			language: {
				info: info ? info : " _TOTAL_ 개의 데이터가 있습니다." 
			},
			columns: tableOption ? tableOption.columns : null,
			order: [[0, 'asc']]
		});
		
		return table;
	},
	action: function(id, tableOption, info) {
		var table = $(id).DataTable({
			language: {
				info: info ? info : " _TOTAL_ 개의 데이터가 있습니다." 
			},
			columns: tableOption ? tableOption.columns : null,
			columnDefs: [
				{ orderable: false, targets: [-1] }
			],
			order: [[0, 'asc']]
		});
		
		return table;
	},
	order: function(id, tableOption, num, info) {
		var table = $(id).DataTable({
			language: {
				info: info ? info : " _TOTAL_ 개의 데이터가 있습니다." 
			},
		    columns: tableOption ? tableOption.columns : null,
		    columnDefs: [
		    	{ orderable: true, className: 'reorder', targets: 0 },
		    	{ orderable: true, className: 'reorder', targets: num },
		    	{ orderable: false, targets: '_all' }
		    ],
		    order: [[0, 'asc']]
		});
		
		return table;
	},
	student: function(id, tableOption, info) {
		var table = $(id).DataTable({
			language: {
				info: info ? info : " _TOTAL_ 개의 데이터가 있습니다."
			},
			columns: tableOption ? tableOption.columns : null,
			columnDefs: [
				{ orderable: false, targets: [-1] },
				{ visible: false, targets: [7, 8] }
			],
			order: [[0, 'asc']]
		});
		
		return table;
	},
	apply: function(id, tableOption, info) {
		var table = $(id).DataTable({
			dom: '<"datatable-header"fl><"datatable-scroll-wrap"t><"datatable-footer"Bip>',
			language: {
				info: info ? info : " _TOTAL_ 개의 데이터가 있습니다."
			},
			columns: tableOption ? tableOption.columns : null,
			columnDefs: [
				/*{ visible: false, targets: [7, 8] }*/
				{ orderable: true, className: 'reorder', targets: 0 },
		    	{ orderable: true, className: 'reorder', targets: 1 },
		    	{ orderable: false, targets: '_all' }
			],
			buttons: {
		        buttons: [{
	                extend: 'excelHtml5',
	                className: 'btn bg-teal-400',
                    text: '<i class="icon-folder-download mr-2"></i> 다운로드',
                    fieldSeparator: '\t',
		            exportOptions: {
		                columns: [1,2,3,4,5,6,7,8,9]
		            }
	            }]
		    },
			order: [[0, 'asc']]
		});
		
		return table;
	},
	rowsAdd: function(table, url, param) {
		this.UIBlock("#list_content");
		table.clear().draw();
		
		$.ajax({
			url: url,
			type: "POST",
			data: JSON.stringify(param),
			contentType: "application/json",
			success: function(data) {
				$("#list_content").unblock();
				table.rows.add(data).draw();
		   	}
		});
	},
	refresh: function(table, data) {
		table.clear().draw();
		table.rows.add(data).draw();
	},
	UIBlock: function(id) {
		var message = '<div class="bg-slate-700 text-white rounded px-3 py-2">'
				+ '<i class="icon-spinner4 spinner mt-1"></i>'
				+ '<span class="font-weight-bold d-block mt-2">조회 중...</span></div>';
		$(id).block({ 
			message: message,
		    overlayCSS: {
		    	backgroundColor: '#fff',
		        opacity: 0.8,
		        cursor: 'wait'
		    },
		    css: {
		    	width: 'auto',
		        '-webkit-border-radius': 2,
		        '-moz-border-radius': 2,
		        padding: 0,
		        border: 0,
		        backgroundColor: 'transparent'
		    }
		});
	}
}