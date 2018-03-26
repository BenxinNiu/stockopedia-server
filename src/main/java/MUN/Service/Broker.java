package MUN.Service;

import MUN.Factory.Asset;
import MUN.Factory.Transaction;
import MUN.Factory.UserAsset;
import MUN.Factory.price;
import MUN.MongoDocument.UserConsolidator;
import MUN.MongoDocument.UserTransactionConsolidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Broker {


    @Autowired
    private FetchPriceData price_subscriber;

    @Autowired
    private FetchUser user_subscriber;

    @Autowired
    private FetchTransaction trans_subscriber;

    public void update_user_record(UserTransactionConsolidator trans){
        UserConsolidator user=user_subscriber.find_user(trans.getUser_id()).get(0);
        UserAsset user_asset=user.getUser_asset();
        Transaction unfullfilled_trans=trans.getTrans();
        List<Transaction> user_transactions=user_asset.getTrans();

        user_transactions.add(unfullfilled_trans);
        user_asset.setTrans(user_transactions);
        user.setUser_asset(user_asset);
        user_subscriber.update_user(user.get_id(),user);
    }

    public boolean execute(UserTransactionConsolidator trans, boolean now) {
        String ticker = trans.getTrans().getTicker();
        List<price> price = price_subscriber.getRealtimeData(ticker);
        int size = price.size();
        price price_now = price.get(price.size() - 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        String last_avalible_date = price_now.getDate().substring(0, price_now.getDate().lastIndexOf("-"));
        String last_avalible_time = price_now.getDate().substring(price_now.getDate().lastIndexOf("-") + 1);
        boolean marketopen =(last_avalible_date.equals(today) && !last_avalible_time.equals("3:59 PM"));
        System.out.println("market open: " + marketopen);
        System.out.println(today);
        System.out.println(last_avalible_date);
        System.out.println(last_avalible_time);
        if (!marketopen) {
            return false;
            //return tradeOfficer(price_now,trans,now);
        } else {
        return tradeOfficer(price_now,trans,now);
        }

    }

    private boolean tradeOfficer(price price_now, UserTransactionConsolidator trans, boolean now){

        double quote=price_now.getClose();
        switch (trans.getTrans().getType()){
            case "buy" : return BuyOfficer(quote,trans,now);
            case "sell" : return SellOfficer(quote,trans,now);
            default : return false;
        }

    }

    private boolean BuyOfficer(double quote, UserTransactionConsolidator trans, boolean now){
        boolean result;
        UserConsolidator user=user_subscriber.find_user(trans.getUser_id()).get(0);
        double user_balance=user.getUser_asset().getAcc_balance();
        double ask_price=trans.getTrans().getPrice();
        double total_values=ask_price * trans.getTrans().getVolume();
       if (!now){
           if(ask_price<=quote && user_balance >= total_values) {
               UserTransactionConsolidator new_trans=updateTrans(quote,trans);// update trans info
               clientOfficer("buy",quote,new_trans);//update user info
           result = true;
           }
           else{
               result = false;
           }
       }
       else if(user_balance >= total_values){
           UserTransactionConsolidator new_trans=updateTrans(quote,trans);
           clientOfficer("buy",quote,new_trans);
           result = true;
       }
       else {
           result = false;
       }
       return result;
    }


    private boolean SellOfficer(double quote, UserTransactionConsolidator trans, boolean now){
        boolean result;
        UserConsolidator user  =  user_subscriber.find_user(trans.getUser_id()).get(0);
        double  sell_price = trans.getTrans().getPrice();
        boolean findTicker = false;
        String  sellTicker = trans.getTrans().getTicker();
        List <Asset> userTrans = user.getUser_asset().getAsset();
        int availableVol = 0;

        for(int i = 0;i<userTrans.size(); i++){
            if(userTrans.get(i).getSymbol().equals(sellTicker))
                findTicker = true;
                availableVol = userTrans.get(i).getShares();
        }
        if(!now){
             if(sell_price>=quote && findTicker && availableVol>trans.getTrans().getVolume()){
                 UserTransactionConsolidator new_trans=updateTrans(quote,trans);// update trans info
                 clientOfficer("sell",quote,new_trans);//update user info
                 result = true;
             }
             else
                 result = false;
        }
        else{
            if(findTicker && availableVol>=trans.getTrans().getVolume()){
                UserTransactionConsolidator new_trans=updateTrans(quote,trans);
                clientOfficer("sell",quote,new_trans);
                result = true;
            }
            else
                result = false;
        }

        return result;
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

    private List<Asset> updateShare_asset(String type, UserAsset asset, Transaction trans){
    List<Asset> updated_list;
    List<Asset> share_list=asset.getAsset();
    boolean found=false;
        for (Asset data : share_list)
            if(data.getSymbol().equals(trans.getTicker())){
            found=true;
            if(type.equals("buy")){
             data.setBookValue(trans.getFullfilled_price());
             data.setBookTotalValue(trans.getFullfilled_price()*trans.getVolume());
             data.setShares(data.getShares()+trans.getVolume());
             }
             else{
                data.setBookValue(trans.getFullfilled_price());
                data.setBookTotalValue(trans.getFullfilled_price()*trans.getVolume());
                data.setShares(data.getShares()-trans.getVolume());
             }
            }
    if(!found){
     Asset new_asset=new Asset(trans.getTicker(),trans.getTicker(),trans.getVolume(),
             trans.getFullfilled_price(),trans.getVolume()*trans.getFullfilled_price(),"USD");
     share_list.add(new_asset);
    }

return share_list;
    }

    private void clientOfficer(String type, double quote, UserTransactionConsolidator trans) {
        UserConsolidator user=user_subscriber.find_user(trans.getUser_id()).get(0);
        UserAsset user_asset=user.getUser_asset();
        Transaction fullfilled_trans=trans.getTrans();

        List<Asset> shares_asset=user_asset.getAsset();
        List<Transaction> user_transactions=user_asset.getTrans();
        double user_balance=user_asset.getAcc_balance();

        if(type.equals("buy")) {
        user_balance=user_balance - fullfilled_trans.getFullfilled_price()*fullfilled_trans.getVolume();
        shares_asset=updateShare_asset(type,user_asset,fullfilled_trans);
        }
        else if(type.equals("sell")){
            user_balance=user_balance + fullfilled_trans.getFullfilled_price()*fullfilled_trans.getVolume();
            shares_asset=updateShare_asset(type,user_asset,fullfilled_trans);
        }
        user_transactions.add(fullfilled_trans);
        user_asset.setAcc_balance(user_balance);
        user_asset.setAsset(shares_asset);
        user_asset.setTrans(user_transactions);
        user.setUser_asset(user_asset);
        user_subscriber.update_user(user.get_id(),user);
        trans_subscriber.updateTrans(trans.getTrans_id(),trans);
    }

}
