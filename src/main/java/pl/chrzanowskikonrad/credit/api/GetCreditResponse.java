package pl.chrzanowskikonrad.credit.api;

import java.util.List;

public class GetCreditResponse {
    private List<GetCreditRequest> credits;

    public GetCreditResponse(List<GetCreditRequest> credits) {
        this.credits = credits;
    }

    public List<GetCreditRequest> getCredits() {
        return credits;
    }
}
