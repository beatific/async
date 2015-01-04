<html>
<header> </header>
<script language="javascript">
	var allow = true;
	var startUrl;
	var pollUrl;
	var userId;
	var CONTEXT_ROOT = "${pageContext.request.contextPath}";
	var id;
	var messanger;

	function Messanger() {

		this.enter = function enter() {

			// fire off the request to MatchUpdateController
			var request = $.ajax({
				url : CONTEXT_ROOT + "/enter",
				type : "post"
			});

			// This is jQuery 1.8+
			// callback handler that will be called on success
			request.done(function(reply) {

				console.log("You entered chatting room." + reply);
				setInterval(function() {
					if (allow === true) {
						allow = false;
						poll();
					}
				}, 500);
			});

			// callback handler that will be called on failure
			request.fail(function(jqXHR, textStatus, errorThrown) {
				// log the error to the console
				console.log("Start - the following error occured: "
						+ textStatus, errorThrown);
			});
		};

		function send(message) {
			var request = $.ajax({
				url : CONTEXT_ROOT + "/send",
				type : "post",
				data: {"message": message, "id": id}, 
			});
			
			request.fail(function(jqXHR, textStatus, errorThrown) {
				// log the error to the console
				console.log("Start - the following error occured: "
						+ textStatus, errorThrown);
			});
		}
		
		function poll() {

			console.log("Okay let's go...");

			if (request) {
				request.abort(); // abort any pending request
			}

			// fire off the request to MatchUpdateController
			var request = $.ajax({
				url : CONTEXT_ROOT + "/poll",
				type : "post",
			});

			// This is jQuery 1.8+
			// callback handler that will be called on success
			request.done(function(message) {
				console.log("Received a message");

				var update = getUpdate(message);
				$(update).insertAfter('#first_row');
			});

			function getUpdate(message) {

				var update = "<div class='span-4  prepend-2'>"
						+ "<p class='update'>Time:</p>" + "</div>"
						+ "<div class='span-3 border'>"
						+ "<p id='time' class='update'>" + message.matchTime
						+ "</p>" + "</div>"
						+ "<div class='span-13 append-2 last' id='update-div'>"
						+ "<p id='message' class='update'>"
						+ message.messageText + "</p>" + "</div>";
				return update;
			}
			;

			// callback handler that will be called on failure
			request.fail(function(jqXHR, textStatus, errorThrown) {
				// log the error to the console
				console.log("Polling - the following error occured: "
						+ textStatus, errorThrown);
			});

			// callback handler that will be called regardless if the request failed or succeeded
			request.always(function() {
				allow = true;
			});
		}
		;
	};
	function enter() {
		id = $id.text();
		messanger = new Messanger();
		messanger.enter();
	}
	function send() {
		var message = $message.text();
		messanger.send(message);
	}
</script>
<body>
	<div id="first_row" />
	<input type="text" id="id"/>
	<input type="text" id="message"/>
	<input type="button" onclick="send()"/> 
	<input type="button" onclick="enter()"/> 
</body>
</html>