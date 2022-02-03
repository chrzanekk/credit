package pl.chrzanowskikonrad.credit.api;

public class NewCreditResponse {
    private Long creditId;

    public NewCreditResponse(Long creditId) {
        this.creditId = creditId;
    }

    public Long getCreditId() {
        return creditId;
    }
}
