package pl.chrzanowskikonrad.credit.api;


import pl.chrzanowskikonrad.credit.domain.CreditData;
import pl.chrzanowskikonrad.credit.domain.CustomerData;


public class NewCreditRequest {
    private CustomerData customer;
    private CreditData credit;

    public NewCreditRequest() {
    }

    public NewCreditRequest(CreditData credit, CustomerData customer) {
        this.customer = customer;
        this.credit = credit;
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public CreditData getCredit() {
        return credit;
    }
}

