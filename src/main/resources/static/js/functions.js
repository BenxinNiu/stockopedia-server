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

$(".dummy").append("<span class='user_id hidden' id='" + user_infor._id + "'> </span>")
$(".dummy").append("<span class='user_pwd hidden' id='" + user_infor.user_infor.pwd + "'> </span>")

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
var chart=  new google.visualization.LineChart(document.getElementById('curve_chart'));
  var options = {
    title: 'Stock Price',
    curveType: 'function',
    legend: { position: 'bottom' }
  };
  chart.clearChart();
  chart.draw(google.visualization.arrayToDataTable(getData), options);
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
              }
              },
            error:function(err){
              console.log("errorssss");
            console.log(err);
         }
      });
}
