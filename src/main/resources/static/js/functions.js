console.log("hello")

function signup(infor){
var email= '"' + infor.email + '"' ;
var firstName= '"' + infor.firstName + '"' ;
var lastName=  '"' + infor.lastName + '"' ;
var pwd='"' + infor.pwd + '"' ;
  $.ajax({
          url:"/register",
          type: "POST",
          contentType:'application/json',
          processData:false,
          //data:'{"user_id": "test_acc@mun.ca","symbol":"AAPL","type":"buy","bid":"false","price":"0","volume":"20"}',
          data:'{"email":' +  email +',"firstName":' + firstName + ',"lastName":'  + lastName + ',"pwd":' + pwd + '}',
          headers: {
"Content-Type":"application/json"
},
          success: (result)=>{
            if(result=="success")
           login_and_update(email.replace('"',"").replace('"',""),pwd.replace('"',"").replace('"',""));
           else if(result=="user existed")
           alert("This email has been used")
          },
          error: (err)=>{
           alert("Server is experiencing some trouble");
          }

      });
}


function acquireNews(ticker){
$.ajax({
   url:"/news/"+ticker,
   type:"GET",
   success: function(list){
  updateNews(list);
   },
   error: function(error){
     alert("Server is having some troubles");
   }
});

}

function updateNews(news){
  $("#news-section").empty();
  $("#news-section").append("<h3>Company News</h3>");
news.forEach((a)=>{
var date=a.datetime;
var headline=a.headline;
var source=a.source;
var link=a.url;
$("#news-section").append("<div class='news'>" +
"<p class='news_source'>"+source+" : </p>" +
"<a class='news_link' href="+link+">" + headline + "</a>" +
"<p class='news_date'>"+date+"</p> <br>" +
 "</div>");
});
}

function submit_transactions(type,bid){
  var user= $(".user_id_dummy").attr('id')
  var user_id='"' + $(".user_id_dummy").attr('id') + '"';
  var pwd=$(".user_pwd_dymmy").attr('id');

if(user_id==undefined){
  alert("Please sign in first");
}
else{
  var symbol='"' +$("#symbol_trans").val() + '"';
  var price='"' +$("#price_trans").val() + '"';
  var volume='"' +$("#volume_trans").val() + '"';
  var trans={"user_id": user_id.toString(),"symbol":symbol.toString(),"type":type.toString(),"bid":bid.toString(),"price":price.toString(),"volume":volume.toString()};
           console.log(trans);
             $.ajax({
                     url:"/submitTransaction",
                     type: "POST",
                     contentType:'application/json',
                     processData:false,
                     //data:'{"user_id": "test_acc@mun.ca","symbol":"AAPL","type":"buy","bid":"false","price":"0","volume":"20"}',
                     data:'{"user_id":' +  user_id +',"symbol":' + symbol + ',"type":' + '"' + type + '"'+ ',"bid":' + bid + ',"price":' + price +',"volume":' + volume + '}',
                     headers: {
        "Content-Type":"application/json"
          },
                     success: (result)=>{
                      login_and_update(user,pwd);
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

var trans_history=user_infor.user_asset.trans;

$("#transaction-section").empty();
$("#transaction-section").append("<h3>Transaction History</h3>");

trans_history.reverse().forEach((a)=>{
  var date=a.date;
var ticker=a.ticker.toUpperCase();
var fulfilled=a.fullfilled;
var type=a.type.toUpperCase();
var fullfilled_date=a.fullfilled_date;
var fullfilled_price=a.fullfilled_price;
var volume=a.volume;
$("#transaction-section").append(
  '<div class="transaction" style="margin-top:8px;">' +
  '<h4 style="margin-left:15px;">' + ticker + '</h4>' +
  '<h5 style="margin-left:15px;"> Submission date: ' + date + '</h4>' +
  '<h5 style="margin-left:15px;"> Successful: ' +  fulfilled + '</h5>' +
  '<h5 style="margin-left:15px;"> Type: '+ type + '</h5>' +
  '<h5 style="margin-left:15px;">Fulfilled date: ' +fullfilled_date + '</h5>' +
  '<h5 style="margin-left:15px;">Fulfilled price: ' + fullfilled_price + '</h5>' +
  '<h5 style="margin-left:15px;">volume: ' + volume + '</h5>' +
  '</div>');
});

$("#Asset-section").empty();
$("#Asset-section").append("<h3>Asset Statistics</h3>");

var user_asset_list=user_infor.user_asset.asset;

user_asset_list.reverse().forEach((a)=>{
var ticker=a.symbol;
var currency=a.currency;
var bookValue=a.bookValue;
var bookTotalValue=a.bookTotalValue;
var shares=a.shares;
$("#Asset-section").append(
  '<div class="asset" style="margin-top:8px;">' +
  '<h4 style="margin-left:15px;">' + ticker + '</h4>' +
  '<h5 style="margin-left:15px;"> Book Value: ' +bookValue+ '</h5>' +
  '<h5 style="margin-left:15px;"> Book value total: ' + bookTotalValue + '</h5>' +
  '<h5 style="margin-left:15px;">Sahres: ' + shares + '</h5>' +
  '</div>');
});


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
  var time_now=prices[prices.length-1][0];
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
                alert("no company found");
              else{
              for(i=0;i<length;i++){
                if(parseFloat(result[i].close) > 0)
              getData.push([result[i].date,result[i].close]);
                } // forloop
                 drawChart(getData);
                 acquireNews(ticker);
                 update_summary(ticker, getData);
              }
              },
            error:function(err){
              console.log("errorssss");
            console.log(err);
         }
      });
}
