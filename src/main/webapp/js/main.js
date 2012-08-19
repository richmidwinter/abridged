function go(e) {
	$.ajax({
	  type: 'POST',
	  url: '/api/mappings',
	  data: e.childNodes[1].value,
	  complete: function(result) {
		  if (result.responseText.length == 7) {
			  $('#result').text(window.location.href +result.responseText);
			  
			  selectText('result');
		  }
	  },
	  contentType: "text/plain",
	  dataType: "text/plain"
	});
	
    return false;
}

function selectText(element) {
    var doc = document;
    var text = doc.getElementById(element);    

    if (doc.body.createTextRange) { // ms
        var range = doc.body.createTextRange();
        range.moveToElementText(text);
        range.select();
    } else if (window.getSelection) { // moz, opera, webkit
        var selection = window.getSelection();            
        var range = doc.createRange();
        range.selectNodeContents(text);
        selection.removeAllRanges();
        selection.addRange(range);
    }
}