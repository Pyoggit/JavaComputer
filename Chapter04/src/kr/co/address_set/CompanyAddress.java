package kr.co.address_set;

public class CompanyAddress extends Address {
    private String companyName;

    public CompanyAddress(String name, String city, String detailedAddress, String zipcode, String companyName) {
        super(name, city, detailedAddress, zipcode);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "회사 주소: " + super.toString() + ", " + companyName + "]";
    }
}