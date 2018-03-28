$(document).ready(function(){
var pull_interval=80000;

var symbol="AAPL";

var my_infor_interval;

acquireData(symbol,"tmp",true);
var myInterval= setInterval(()=>{
   console.log("Refreshing " +symbol);
   acquireData(symbol,"tmp",true); }, pull_interval);


$("#search").submit((e)=>{
  e.preventDefault();
  symbol=$("#search_bar").val();
  acquireData(symbol,"tmp",true);
   clearInterval(myInterval);
  myInterval= setInterval(()=>{
console.log("Refreshing " +symbol);
    acquireData(symbol,"tmp",true) }, pull_interval);
  return false;
})

$("#submit-transaction").submit((e)=>{
  e.preventDefault();
  return false;
})

$(".submit_trans_button").click(function(){
  var type=this.id;
  console.log(type);
  switch (type){
    case "sell_now": submit_transactions("sell","false");
    break;
    case "buy_now": submit_transactions("buy","false");
    break;
    case "ask": submit_transactions("buy","true");
    break;
    case "put": submit_transactions("sell","true");
    break;
  }
})

$("#check_news").click(()=>{
  if($("#news-section").hasClass("hidden")){
    $("#news-section").removeClass("hidden");
  }
  else{
    $("#news-section").addClass("hidden");
  }
})



$("#login").submit((e)=>{
  e.preventDefault();
  var username=$("#user_name").val();
  var pwd=$("#pwd ").val();
 login_and_update(username,pwd);
  return false;
})


$("#signup").submit((e)=>{
  e.preventDefault();
if($("#signup_pwd1").val()!==$("#signup_pwd2").val()){
  alert("Password do not match");
}
else{
  var form= {
    "email": $("#signup_email").val(),
    "firstName":$("#signup_firstname").val(),
    "lastName":$("#signup_lastname").val(),
    "pwd":$("#signup_pwd1").val()
  }
signup(form);
}
  return false;
})

$("#login_navbar").on('click',()=>{
if($("#login_page").hasClass('hidden')){
  $("#login_page").removeClass('hidden')
}
else{
  $("#login_page").addClass('hidden')
    $("#signup_page").addClass('hidden')
}
})

$("#signup_button").on('click',()=>{
    $("#login_page").addClass('hidden')
    if($("#signup_page").hasClass('hidden')){
      $("#signup_page").removeClass('hidden')
    }
    else{
      $("#signup_page").addClass('hidden')
    }

})

$('.quote_button').click(function() {
   var type = this.id;
   if(type!="real_time") {
console.log($(".ticker_dymmy").attr('id'))
clearInterval(myInterval);
acquireData($(".ticker_dymmy").attr('id'),type,false);
   }
   else{
     clearInterval(myInterval);
     acquireData(symbol,"tmp",true);
     myInterval= setInterval(()=>{
       console.log("Refreshing " +symbol);
       acquireData(symbol,"tmp",true) }, pull_interval);
   }
});

});
