chrome.extension.sendMessage({action: "isRecordingOn"}, function(response) {
	if(response.action == 'true'){
	       if (window.location.href == "http://rise45.com/cart") {
            $(function() {
                window.setTimeout(function() {
                  $('input[name=goto_pp]')[0].click();
                }, 100);
            });
             }
		var size = response.shoesSize;
		$(function(){
			vv = setInterval(function(){			
				$('select[name="id"] option:contains(' + size + ')').first().attr('selected', true);		
				try{
					$('#add')[0].click();
					 window.setTimeout(function() {
                        window.location.href = 'http://rise45.com/cart';
                    }, 1000);
				}catch(err){
					console.log("erreur: "+err);
				}
				clearInterval(vv);
			},1000);
		});
	}
});