<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js" integrity="sha512-tL4PIUsPy+Rks1go4kQG8M8/ItpRMvKnbBjQm4d2DQnFwgcBYRRN00QdyQnWSCwNMsoY/MfJY8nHp2CzlNdtZA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
var stompClient = null;

// 채팅방 번호를 주소줄에서 잘라서 가져왔다. 
var roomId = document.location.href.split('/')[5];

$( document ).ready(function() {
    console.log( "ready!" );
    connect(); 
});


$(window).bind("beforeunload", function (e){
	disconnect(); // 창 닫으면 연결 종료 되도록 처리 
});


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
	// 이렇게 해줘야 연결이 되더라. contextPath 넣어줘야.
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
        
        stompClient.subscribe('/topic/chat/'+roomId, function (chat) {
    		showChat(JSON.parse(chat.body));
    	});
        
        stompClient.send("/app/chat/"+roomId, {}, JSON.stringify({'name':'system', 'message': '환영합니다'}));
    });
    
    
}


// 연결 종료 시 방에서 퇴장하였다는 메시지를 출력해주고 방에서 나가게 할 것. 
// 또한, DB 쪽에도 처리할 수 있도록 컨트롤러에게도 뭔가 보내줘야 한다. 
function disconnect() {
	
	// 창 닫기 하였을 때도 소켓 닫게 하는 방법이 있을까? 
	
	stompClient.send("/app/chat/"+roomId, {}, JSON.stringify({'name':'system', 'message': $("#name").val()+'님이 방에서 나가셨습니다'}));
	
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    
    location.href="/sock/room/list" ; 
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

// 채팅방 별로 처리하기 위한 용도
// 메시지 형태에 대해서는 바꿔줘야할 수도. 
// DB에 저장되는 것을 생각한다면 roomId 값도 줘야한다. 
function sendChat2() {
	stompClient.send("/app/chat/"+roomId, {}, JSON.stringify({'name': $("#name").val(), 'message': $("#chatMessage").val()})); 
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showGreeting2(message) {
    $("#greetings").append("<tr><td>" +"test :"+ message + "</td></tr>");
}

// 채팅이 보여지는 부분.
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
    //$( "#chatSend" ).click(function(){ sendChat(); }); 
    $( "#chatSend" ).click(function(){ sendChat2(); }); 
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