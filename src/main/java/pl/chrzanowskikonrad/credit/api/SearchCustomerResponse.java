package pl.chrzanowskikonrad.credit.api;

import pl.chrzanowskikonrad.credit.domain.CustomerData;

import java.util.List;

public class SearchCustomerResponse {
    private List<CustomerData> searchResult;

    public SearchCustomerResponse() {
    }

    public SearchCustomerResponse(List<CustomerData> searchResult) {
        this.searchResult = searchResult;
    }

    public List<CustomerData> getSearchResult() {
        return searchResult;
    }
}
