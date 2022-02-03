package pl.chrzanowskikonrad.credit.api;

public class SearchCustomerRequest {

    private String pesel;

    public SearchCustomerRequest() {
    }

    public SearchCustomerRequest(String pesel) {
        this.pesel = pesel;
    }

    public String getPesel() {
        return pesel;
    }

}
