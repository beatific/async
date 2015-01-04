<html>
<header> </header>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script language="javascript">
	var allow = true;
	var CONTEXT_ROOT = "${pageContext.request.contextPath}";
	var id;
	var messanger;

	function Messanger() {
		this.enter = function enter() {
			setInterval(function() {
					if (allow === true) {
						allow = false;
						poll();
					}
				}, 500);
		};

		function send(message) {
			var request = $.ajax({
				url : CONTEXT_ROOT + "/send",
				type : "post", 
			});
			
			request.fail(function(jqXHR, textStatus, errorThrown) {
				console.log("Start - the following error occured: "
						+ textStatus, errorThrown);
			});
		}
		
		function poll() {

			var request = $.ajax({
				url : CONTEXT_ROOT + "/poll",
				type : "get",
			});
			
			request.done(function(message) {
				$("<p>message</p>").insertAfter('#first_row');
			});

			request.fail(function(jqXHR, textStatus, errorThrown) {
				console.log("Polling - the following error occured: "
						+ textStatus, errorThrown);
			});

			request.always(function() {
				allow = true;
			});
		}
		;
	};
	function enter() {
		id = "id";
		messanger = new Messanger();
		messanger.enter();
	}
	function send() {
			var request = $.ajax({
				url : CONTEXT_ROOT + "/send",
				type : "post", 
			});
			
			request.fail(function(jqXHR, textStatus, errorThrown) {
				console.log("Start - the following error occured: "
						+ textStatus, errorThrown);
			});
	}
</script>
<body>
	id : <input type="text" id="id" name="id" value="id"/>
	<input type="button" value="enter" onclick="enter();"/> 
	<br> 
	message : <input type="text" id="message" name="message"/>
    <input type="button" value="send" onclick="send();"/>	
    <div id="first_row" />
</body>
</html>