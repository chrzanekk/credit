package pl.chrzanowskikonrad.credit.api;

import pl.chrzanowskikonrad.credit.domain.CustomerData;

import java.util.List;

public class GetCustomersResponse {
    private List<CustomerData> customers;

    public GetCustomersResponse() {
    }

    public GetCustomersResponse(List<CustomerData> customers) {
        this.customers = customers;
    }

    public List<CustomerData> getCustomers() {
        return customers;
    }
}
