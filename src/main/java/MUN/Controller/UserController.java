package MUN.Controller;


import MUN.Factory.Asset;
import MUN.Factory.Transaction;
import MUN.Factory.UserAsset;
import MUN.Factory.UserForm;
import MUN.MongoDocument.UserConsolidator;
import MUN.MongoDocument.UserTransactionConsolidator;
import MUN.Service.FetchUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private FetchUser subscriber;




    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser(@RequestBody UserForm form) {
        System.out.println("hello");
        System.out.println(form.getFirstName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String now = sdf.format(new Date());
        List<Asset> user_asset = new ArrayList<>();
        List<Transaction> user_trans = new ArrayList<>();
        UserAsset user_asset_form = new UserAsset(60000, user_asset, user_trans);
        UserConsolidator new_user = new UserConsolidator(form.getEmail(), now, form, user_asset_form);
        List<UserConsolidator> user_existing = this.subscriber.find_user(form.getEmail());
        if (user_existing.size() != 0) {
            return "user existed";
        } else {
            this.subscriber.register_user(new_user);
            return "success";
        }
    }

    @RequestMapping("/userlogin/{email}/{password}")
    public String userLogin(@PathVariable("email") String email, @PathVariable("password") String password) {
        List<UserConsolidator> result = subscriber.find_user(email);
        if (result.size() == 0) {
            return "User name does not exist";
        } else {
            String realpwd = result.get(0).getUser_infor().getPwd();
            if(realpwd.equals(password))
                return "you are logged in!";
             else
                 return "User name and password do not match, please try again";
            }
        }
    }
