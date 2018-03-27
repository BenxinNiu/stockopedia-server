package MUN.Factory;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyInformation {
private String symbol;
private String companyName;
private String exchange;
private String industry;
private String website;
private String description;
private String CEO;
private String issueType;
private String sector;

public CompanyInformation(){}

    public CompanyInformation(String symbol, String companyName, String exchange, String industry, String website, String description, String CEO, String issueType, String sector) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.exchange = exchange;
        this.industry = industry;
        this.website = website;
        this.description = description;
        this.CEO = CEO;
        this.issueType = issueType;
        this.sector = sector;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getExchange() {
        return exchange;
    }

    public String getIndustry() {
        return industry;
    }

    public String getWebsite() {
        return website;
    }

    public String getDescription() {
        return description;
    }

    public String getCEO() {
        return CEO;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getSector() {
        return sector;
    }
}
