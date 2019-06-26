/*******************************************************************************************************
 * 위젯 공통 기능
*******************************************************************************************************/
var CommonWidget = function() {
	var _componentSelect2 = function() {
		if (!$().select2) {
        	console.warn('Warning - select2.min.js is not loaded.');
            return;
        }
    	
    	// Initialize
        var $select = $('.form-control-select2').select2({
            minimumResultsForSearch: Infinity,
            width: '100%'
        });
        
        $('.select2-size').select2({
            minimumResultsForSearch: Infinity,
            width: '90'
        });
        
        // Select with search
        $('.select-search').select2();
        
        // Trigger value change when selection is made
        $select.on('change', function() {
            $(this).trigger('blur');
        });
    };
    
    var _componentSwal = function() {
    	/** Sweet Alerts Defaults */
    	swal.setDefaults({
	        buttonsStyling: false,
	        confirmButtonClass: 'btn btn-primary',
	        cancelButtonClass: 'btn btn-light'
	    });
    };
    
    return {
        init: function() {
        	_componentSelect2();
        	_componentSwal();
        }
    }
}();

document.addEventListener('DOMContentLoaded', function() {
	CommonWidget.init();
});