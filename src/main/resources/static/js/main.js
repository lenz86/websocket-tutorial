var messageArea = document.querySelector('#name');
var messageLog = document.querySelector('#message-log');

var stompClient = null;
var message = null;

function connect() {
    var socket = new SockJS('/javatechie');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function send() {
    message = messageArea.value.trim();
    stompClient.send("/app/chat.send", {}, message);
}

function onConnected() {
    window.alert("Connected!")
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);
}

function onError(error) {
    window.alert("Could not connect to WebSocket server. Please refresh this page to try again!");
}

function onMessageReceived(payload) {
    var message = payload.body;
    messageLog.value = (messageLog.value + "\n" + message);
}