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
    case "sell_now": submit_transactions("sell",false);
    break;
    case "buy_now": submit_transactions("buy","false");
    break;
    case "ask": submit_transactions("buy",true);
    break;
    case "put": submit_transactions("sell",true);
    break;
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
  console.log("hello")
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
clearInterval(myInterval);
acquireData(symbol,type,false);
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
