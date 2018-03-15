package MUN.Controller;


import MUN.Factory.Asset;
import MUN.Factory.Transaction;
import MUN.Factory.UserAsset;
import MUN.Factory.UserForm;
import MUN.MongoDocument.UserConsolidator;
import MUN.MongoDocument.UserTransactionConsolidator;
import MUN.Service.FetchUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    FetchUser subscriber;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("test") UserForm form){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String now =sdf.format(new Date());
        List<Asset> user_asset= new ArrayList<>();
        List<Transaction> user_trans= new ArrayList<>();
        UserAsset user_asset_form= new UserAsset(60000,user_asset,user_trans);
        UserConsolidator new_user= new UserConsolidator(form.getEmail(),now,form,user_asset_form);
        List<UserConsolidator> user_existing= this.subscriber.find_user(form.getEmail());
        if(user_existing.size()!=0){
            return "user existed";
        }
        else{
            this.subscriber.register_user(new_user);
            return "success";
        }
    }

}
