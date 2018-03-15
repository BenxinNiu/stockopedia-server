package MUN.Factory;

import java.util.*;

public class UserAsset {

   private double acc_balance=60000;
    private List<Asset> asset= new ArrayList<>();
    private List<Transaction> trans= new ArrayList<>();

    public UserAsset(double acc_balance, List<Asset> asset, List<Transaction> trans) {
        this.acc_balance = acc_balance;
        this.asset = asset;
        this.trans = trans;
    }

    public void setAcc_balance(double acc_balance) {
        this.acc_balance = acc_balance;
    }

    public void setAsset(List<Asset> asset) {
        this.asset = asset;
    }

    public void setTrans(List<Transaction> trans) {
        this.trans = trans;
    }

    public double getAcc_balance() {
        return acc_balance;
    }

    public List<Asset> getAsset() {
        return asset;
    }

    public List<Transaction> getTrans() {
        return trans;
    }
}
