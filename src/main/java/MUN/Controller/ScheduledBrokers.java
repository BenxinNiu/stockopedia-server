package MUN.Controller;


import MUN.MongoDocument.UserTransactionConsolidator;
import MUN.Service.Broker;
import MUN.Service.FetchTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledBrokers {

    @Autowired
    FetchTransaction trans_subscriber;
    @Autowired
    Broker middleman;

@Scheduled(fixedRate= 240000)
    public void callBrokers(){
    System.out.println("Running brokers");
    List<UserTransactionConsolidator> trans_list=trans_subscriber.findAllTrans(false);
    System.out.println("Found " + trans_list.size() + " unprocessed trans");
  //  System.out.println(trans_list.get(0).getTrans().getFullfilled_date());
    for(UserTransactionConsolidator trans : trans_list){
       boolean result= middleman.execute(trans,false);
       System.out.println("is successful: " + result);
    }
}
}
