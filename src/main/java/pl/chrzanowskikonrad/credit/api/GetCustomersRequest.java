package pl.chrzanowskikonrad.credit.api;

import java.util.List;

public class GetCustomersRequest {
    private List<Long> customersIds;

    public GetCustomersRequest(List<Long> customersIds) {
        this.customersIds = customersIds;
    }

    public GetCustomersRequest() {
    }

    public List<Long> getCustomersIds() {
        return customersIds;
    }
}
