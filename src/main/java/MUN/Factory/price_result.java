package MUN.Factory;

import java.util.List;

public class price_result {
    CompanyInformation information;
    List<price> price;

    public price_result(CompanyInformation information, List<MUN.Factory.price> price) {
        this.information = information;
        this.price = price;
    }

    public CompanyInformation getInformation() {
        return information;
    }

    public List<MUN.Factory.price> getPrice() {
        return price;
    }
}
