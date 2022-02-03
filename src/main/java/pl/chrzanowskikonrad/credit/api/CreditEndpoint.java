package pl.chrzanowskikonrad.credit.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.chrzanowskikonrad.credit.logic.CreditService;
import pl.chrzanowskikonrad.credit.logic.CustomerService;

import java.util.List;

@RestController
public class CreditEndpoint {
    private final CreditService creditService;
    private final CustomerService customerService;

    public CreditEndpoint(CreditService creditService, CustomerService customerService) {
        this.creditService = creditService;
        this.customerService = customerService;
    }

    @PostMapping(path = "/credit", consumes = "application/json")
    public NewCreditResponse createCredit(@RequestBody NewCreditRequest request) {
        Long newCreditId = creditService.createCredit(request);
        return new NewCreditResponse(newCreditId);
    }

    @GetMapping(path = "/credits", produces = "application/json; charset=UTF-8")
    public GetCreditResponse getCredits() {
        List<GetCreditRequest> credits = creditService.findAll();
        return new GetCreditResponse(credits);
    }

}
