package pl.chrzanowskikonrad.credit.api;

import pl.chrzanowskikonrad.credit.domain.CreditData;
import pl.chrzanowskikonrad.credit.domain.CustomerData;


public class GetCreditRequest {
    private CreditData credit;
    private CustomerData customer;


    public GetCreditRequest(CustomerData customer, CreditData credit) {
        this.credit = credit;
        this.customer = customer;
    }

    public GetCreditRequest() {
    }

    public CreditData getCredit() {
        return credit;
    }

    public CustomerData getCustomer() {
        return customer;
    }
}
