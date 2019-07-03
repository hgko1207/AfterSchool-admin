/*******************************************************************************************************
 * 위젯 공통 기능
*******************************************************************************************************/
var CommonWidget = function() {
	var _componentSelect2 = function() {
		if (!$().select2) {
        	console.warn('Warning - select2.min.js is not loaded.');
            return;
        }
    	
        var $select = $('.form-control-select2').select2({
            minimumResultsForSearch: Infinity,
            width: '100%'
        });
        
        $('.select2-size').select2({
            minimumResultsForSearch: Infinity,
            width: '90'
        });
        
        $('.select-search').select2();
        
        $select.on('change', function() {
            $(this).trigger('blur');
        });
    };
    
    var _componentSwal = function() {
    	swal.setDefaults({
	        buttonsStyling: false,
	        confirmButtonClass: 'btn btn-primary',
	        cancelButtonClass: 'btn btn-light'
	    });
    };
    
    var _componentJQuery = function() {
    	/** form 데이터들을 JSON 형식으로 변환 */
    	jQuery.fn.serializeObject = function() { 
    		var obj = null; 
    		try { 
    			if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
				var arr = this.serializeArray(); 
				if(arr){ 
					obj = {}; 
					jQuery.each(arr, function() { 
						obj[this.name] = this.value; }); 
					} 
				} 
			} catch(e) { 
				alert(e.message); 
			} finally {} 
			return obj; 
		}
    };
    
    return {
        init: function() {
        	_componentSelect2();
        	_componentSwal();
        	_componentJQuery();
        }
    }
}();

document.addEventListener('DOMContentLoaded', function() {
	CommonWidget.init();
});