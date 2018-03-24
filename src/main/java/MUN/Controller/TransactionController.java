package MUN.Controller;


import MUN.Factory.*;
import MUN.MongoDocument.UserConsolidator;
import MUN.MongoDocument.UserTransactionConsolidator;
import MUN.Service.Broker;
import MUN.Service.FetchTransaction;
import MUN.Service.FetchUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class TransactionController {

    @Autowired
    private FetchTransaction subscriber;

    @Autowired
    private Broker middleman;

    @RequestMapping(value="/submitTransaction", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sumbitTransaction(@RequestBody TransactionSubmissionForm form) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String now = sdf.format(new Date());
        Transaction user_trans = new Transaction(now, "CAD", form.getPrice(), 0, form.getType(), form.getSymbol(), form.getVolume(), "N/A", false);
        String user_id = form.getUser_id();
        int serial_num = new Random().nextInt(80000) + 1;
        String trans_id = now + "-" + serial_num;
        UserTransactionConsolidator final_trans = new UserTransactionConsolidator(user_id, trans_id, user_trans);
        subscriber.saveTrans(final_trans);
        if (!form.getBid()) {

        }
    }


}
