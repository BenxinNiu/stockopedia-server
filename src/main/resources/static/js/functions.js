console.log("hello")

function submit_transactions(type,bid){
  var user_id=$(".user_id_dummy").attr('id');
  var pwd=$(".user_pwd_dymmy").attr('id');

if(user_id==undefined){
  alert("Please sign in first");
}
else{
  var symbol=$("#symbol_trans").val();
  var price=$("#price_trans").val();
  var volume=$("#volume_trans").val();
  var trans={

             "user_id": "test_acc@mun.ca",
             "symbol":"AAPL",
             "type":"buy",
             "bid":"false",
             "price":"0",
             "volume":"20"
           };
           console.log(trans);
             $.ajax({
                     url:"/submitTransaction",
                     type: "POST",
                     data:trans,
                     headers: {
        "Content-Type":"application/json"
      },
                     success: (result)=>{
                      login_and_update(user_id,pwd);
                     },
                     error: (err)=>{
                     	alert("Server is experiencing some trouble");
                     }

                 });

}

  }

function login_and_update(username, pwd){
  $.ajax({
          url:"/userlogin/"+username+"/"+pwd,
          type: "GET",
          contenttype:'json',
          success: (result)=>{
            console.log(result);
          	if(result.length==0)
            alert("Please check username or password");
            else{
            $("#login_page").addClass('hidden')
              $("#signup_page").addClass('hidden')
            update_information(result[0]);
          }
          },
          error: (err)=>{
          	alert("Server is experiencing some trouble");
          }

      });
}

function update_information(user_infor){

$(".dummy").append("<span class='user_id_dummy hidden' id='" + user_infor._id + "'> </span>")
$(".dummy").append("<span class='user_pwd_dymmy hidden' id='" + user_infor.user_infor.pwd + "'> </span>")

var balance=user_infor.user_asset.acc_balance;
var num_trans=user_infor.user_asset.trans.length;
var num_fulfilled_trans=0;

//alert(user_infor.user_asset.trans[0].currency);
user_infor.user_asset.trans.forEach((a)=>{
  if(a.fullfilled)
  num_fulfilled_trans++;
})

var num_asset=user_infor.user_asset.asset.length;

$("#user_name_infor").html(user_infor.user_infor.lastName + " " + user_infor.user_infor.firstName)
$("#user_id_infor").html(user_infor._id)
$("#user_balance_infor").html("$ " + balance)
$("#user_trans_infor").html(num_trans)
$("#user_fulfilled_trans_infor").html(num_fulfilled_trans)
$("#user_asset_infor").html(num_asset)

}

function drawChart(getData) {

  var data = google.visualization.arrayToDataTable(getData);
  var options = {
    title: 'Stock Price',
    curveType: 'function',
    legend: { position: 'bottom' }
  };
  var chart=  new google.visualization.LineChart(document.getElementById('curve_chart'));
  chart.clearChart();
  chart.draw(data, options);
}

function update_summary(ticker,prices){
  var now= new Date();
  var time_now=now.getFullYear() + "-" + now.getMonth() + "-" + now.getDay() + " "
  + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
var change;
var prev_q = prices[prices.length-2][1];
var now_q = prices[prices.length-1][1];
if(now>prev_q){
change=((now_q - prev_q)/prev_q)*100;
change=change.toString()+"%";
$("#current_change").css("color","green");
$("#current_change").html(change);
}
else{
  change=((prev_q-now_q)/prev_q)*100;
  change=change.toString()+"%";
  $("#current_change").css("color","red");
  $("#current_change").html(change);
}
$("#company_name").html(ticker.toUpperCase());
$("#current_quote").html("$"+prices[prices.length-1][1]);
$("#current_time").html(time_now);
$("#current_change").html()
}

function acquireData(ticker, type, real_time){
  //var x = "/real/" +ticker +"/" + type;
  var uri;
  if(real_time)
  uri= "/real-time/" +ticker;
  else
  uri="/historicalprice/" +ticker +"/" + type;

  $.ajax({
            url:uri,
            type: "GET",
            success: function(result){
              var getData=[['time','price']];
              var length = result.length;
              if(result.length == 0)
                alert("opppppsss");
              else{

              for(i=0;i<length;i++){
              getData.push([result[i].date,result[i].close]);
                } // forloop
                 drawChart(getData);
                 update_summary(ticker, getData);
              }
              },
            error:function(err){
              console.log("errorssss");
            console.log(err);
         }
      });
}
