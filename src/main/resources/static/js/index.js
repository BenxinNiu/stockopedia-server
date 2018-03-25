$(document).ready(function(){



$("#search").submit((e)=>{
  e.preventDefault();
  return false;
})

$("#submit-transaction").submit((e)=>{
  e.preventDefault();
  return false;
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


});
