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
        search: '<span>Filter:</span> _INPUT_',
        searchPlaceholder: 'Type to filter...',
        lengthMenu: '<span>Show:</span> _MENU_',
        paginate: { 'first': 'First', 'last': 'Last', 
        	'next': $('html').attr('dir') == 'rtl' ? '&larr;' : '&rarr;', 'previous': $('html').attr('dir') == 'rtl' ? '&rarr;' : '&larr;' }
    },
    searching: false,
    lengthChange: false,
    pageLength: 10
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
	rowsAdd: function(table, url, param) {
		table.clear().draw();
		
		$.ajax({
			url: url,
			type: "POST",
			data: JSON.stringify(param),
			contentType: "application/json",
			success: function(data) {
				table.rows.add(data).draw();
		   	}
		});
	},
	refresh: function(table, data) {
		table.clear().draw();
		table.rows.add(data).draw();
	}
}