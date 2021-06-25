<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js" integrity="sha512-tL4PIUsPy+Rks1go4kQG8M8/ItpRMvKnbBjQm4d2DQnFwgcBYRRN00QdyQnWSCwNMsoY/MfJY8nHp2CzlNdtZA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script type="text/javascript">
var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
	alert("test");
    var socket = new SockJS('${pageContext.request.contextPath}/gs-guide-websocket');
    console.log("new sockjs"); 
    console.log(socket);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        
        stompClient.subscribe('/topic/greetings2', function (greeting) {
            showGreeting2(JSON.parse(greeting.body).content);
        });
        
        stompClient.subscribe('/topic/chat', function (chat) {
    		showChat(JSON.parse(chat.body));
    	});
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendName2() {
    stompClient.send("/app/hello2", {}, JSON.stringify({'name': $("#chatMessage").val()}));
}

function sendChat() {
	stompClient.send("/app/chat", {}, JSON.stringify({'name': $("#name").val(), 'message': $("#chatMessage").val()})); 
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showGreeting2(message) {
    $("#greetings").append("<tr><td>" +"test :"+ message + "</td></tr>");
}

function showChat(chat) {
  if( $("#name").val() == chat.name){
  	$("#greetings").append("<tr><td align='right'>" + chat.name + " :: " + chat.message + "</td></tr>");
  }else{
  	$("#greetings").append("<tr><td align='left'>" + chat.name + " :: " + chat.message + "</td></tr>");
  }
  $("#chatMessage").val('')  ;
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#send2" ).click(function() { sendName2(); });
    $( "#chatSend" ).click(function(){ sendChat(); }); 
});

</script>
<meta charset="UTF-8">
<title>소켓 테스트</title>
</head>
<body>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" class="btn btn-default" >Connect</button>
                    <button id="disconnect" class="btn btn-default"  disabled="disabled">Disconnect
                    </button>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="name">What is your name?</label>
                    <input type="text" id="name" class="form-control" placeholder="Your name here...">
                </div>
                <div class="form-group">
				  <label for="message">Input Message</label>
				  <input type="text" id="chatMessage" class="form-control" placeholder="message.." />
				</div>
				<button id="chatSend" class="btn btn-default" type="button">Chat Send</button>
                <button id="send" class="btn btn-default" type="submit">Send</button>
                <button id="send2" class="btn btn-default" type="submit">Send2</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
           		<tr>
           			<td align="right">test</td>
           		</tr>
                <tr>
                    <th>Greetings?</th>
                </tr>
                </thead>
                <tbody id="greetings">
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>