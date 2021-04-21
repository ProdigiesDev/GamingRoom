$(function() {
    var webSocket = WS.connect("ws://127.0.0.1:1337");

    let sessionGL=null;
    webSocket.on("socket/connect", function (session) {
        sessionGL=session;
        //session is an AutobahnJS WAMP session.
    //the callback function in "subscribe" is called everytime an event is published in that channel.
        session.subscribe("acme/channel", function (uri, payload) {
            if(payload.msg.user)
                generate_message(payload.msg, 'self');
        });

    })
var INDEX = 0; 
$("#chat-submit").click(function(e) {
    e.preventDefault();
    var msg = $("#chat-input").val(); 
    if(msg.trim() == ''){
    return false;
    }
    const obj={user:$("#userName").val(),msg}
    sessionGL.publish("acme/channel", obj);
    
})

function generate_message(obj, type) {
    INDEX++;
    var str="";
    str += "<div id='cm-msg-"+INDEX+"' class=\"chat-msg "+type+"\">";
   str += "         <span class='userName'> "+obj.user+" : </span> ";
    str += "          <div class=\"cm-msg-text\">";
    str += obj.msg;
    str += "          <\/div>";
    str += "        <\/div>";
    $(".chat-logs").append(str);
    $("#cm-msg-"+INDEX).hide().fadeIn(300);
    if(type == 'self'){
    $("#chat-input").val(''); 
    }    
    $(".chat-logs").stop().animate({ scrollTop: $(".chat-logs")[0].scrollHeight}, 1000);    
}  

$(document).delegate(".chat-btn", "click", function() {
    var value = $(this).attr("chat-value");
    var name = $(this).html();
    $("#chat-input").attr("disabled", false);
    generate_message(name, 'self');
})

$("#chat-circle").click(function() {    
    $("#chat-circle").toggle('scale');
    $(".chat-box").toggle('scale');
})

$(".chat-box-toggle").click(function() {
    $("#chat-circle").toggle('scale');
    $(".chat-box").toggle('scale');
})

})