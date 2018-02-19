package MUN.Controller;

import MUN.MongoDocument.TransactionConsolidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import MUN.Service.FetchTransData;


import java.util.Arrays;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private FetchTransData subscriber;

    //TODO:::::::  add OAuth


   @RequestMapping("/trans/id/{id}")
   public List<TransactionConsolidator> getTransById(@PathVariable("id") String usrID){
       return this.subscriber.queryTransByID(usrID);
   }

   @RequestMapping("/trans/fullfilled/{status}")
   public List<TransactionConsolidator> getTransByStatus(@PathVariable("status") String status){
       return this.subscriber.queryTransByFulfilled(status);
   }

   @RequestMapping("/trans/transtype/{type}")
   public List<TransactionConsolidator> getTransBytype(@PathVariable ("type") String type){
       return this.subscriber.queryTransByType(type);
   }

  @RequestMapping("/trans/user/{ID}/filter")
    public List<TransactionConsolidator> getTransByusrIDandType (@PathVariable("ID") String usrId,
                                                                 @RequestParam(value ="type") String type){
    return this.subscriber.queryByusrIdAndType(usrId,type);
  }

}
