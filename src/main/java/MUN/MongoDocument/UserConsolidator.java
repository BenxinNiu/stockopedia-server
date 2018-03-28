package MUN.MongoDocument;

import MUN.Factory.UserAsset;
import MUN.Factory.UserForm;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_base")
public class UserConsolidator {
    @Id
    private String _id;
    private String creation_date;
    private UserForm user_infor;
    private UserAsset user_asset;

    public UserConsolidator(String _id, String creation_date, UserForm user_infor, UserAsset user_asset) {
        this._id = _id;
        this.creation_date = creation_date;
        this.user_infor = user_infor;
        this.user_asset = user_asset;
    }

    public String get_id() {
        return _id;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public UserForm getUser_infor() {
        return user_infor;
    }

    public UserAsset getUser_asset() {
        return user_asset;
    }

    public void setUser_asset(UserAsset user_asset) {
        this.user_asset = user_asset;
    }
}
