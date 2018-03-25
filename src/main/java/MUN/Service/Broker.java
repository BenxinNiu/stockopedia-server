package MUN.Service;

import MUN.Factory.Transaction;
import MUN.Factory.price;
import MUN.MongoDocument.UserTransactionConsolidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Broker {


    @Autowired
    FetchPriceData subscriber;

    public boolean executeNow(UserTransactionConsolidator trans) {
        String ticker = trans.getTrans().getTicker();
        List<price> price = subscriber.getRealtimeData(ticker);
        int size = price.size();
        price price_now = price.get(price.size() - 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        String last_avalible_date = price_now.getDate().substring(0, price_now.getDate().lastIndexOf("-"));
        String last_avalible_time = price_now.getDate().substring(price_now.getDate().lastIndexOf("-") + 1);
        boolean marketClosed = last_avalible_date.equals(today) && last_avalible_time.equals("3:59 PM");
        System.out.println(marketClosed);
        System.out.println(today);
        System.out.println(last_avalible_date);
        System.out.println(last_avalible_time);
        if (marketClosed) {
            return false;
        } else {
        return tradeOfficer(price_now,trans,true);
        }

    }

    public boolean tradeOfficer(price price_now, UserTransactionConsolidator trans, boolean now){
        boolean result=false;
        double quote=price_now.getClose();
        switch (trans.getTrans().getType()){
            case "buy" : result=BuyOfficer(quote,trans,now);
            case "sell" : result=SellOfficer(quote,trans,now);
        }
        return result;
    }

    private boolean BuyOfficer(double quote, UserTransactionConsolidator trans, boolean now){
       if (!now){
           double ask_price=trans.getTrans().getPrice();
           if(ask_price<=quote) {
               UserTransactionConsolidator new_trans=updateTrans(quote,trans);
               clientOfficer("buy",quote,new_trans);
           return true;
           }
           else{
               return false;
           }
       }
       else {
           UserTransactionConsolidator new_trans=updateTrans(quote,trans);
           clientOfficer("buy",quote,new_trans);
       }
        return false;
    }

    private UserTransactionConsolidator updateTrans(double quote,UserTransactionConsolidator trans){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String today = sdf.format(new Date());
        Transaction trans_infor=trans.getTrans();
        trans_infor.setCurrency("USD");
        trans_infor.setFullfilled_price(quote);
        trans_infor.setFullfilled_date(today);
        trans_infor.setFullfilled(true);
        UserTransactionConsolidator new_trans=new UserTransactionConsolidator(trans.getUser_id(),trans.getTrans_id(),trans_infor);
        return new_trans;
    }

    private boolean SellOfficer(double quote, UserTransactionConsolidator trans, boolean now){
        return false;
    }

    private void clientOfficer(String type, double quote, UserTransactionConsolidator trans){

    }

}
