package pl.chrzanowskikonrad.credit.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.chrzanowskikonrad.credit.api.GetCreditRequest;
import pl.chrzanowskikonrad.credit.api.NewCreditRequest;
import pl.chrzanowskikonrad.credit.domain.CreditData;
import pl.chrzanowskikonrad.credit.domain.CustomerData;
import pl.chrzanowskikonrad.credit.repository.CreditRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditService {
    private final CreditRepository creditRepository;
    private final CustomerService customerService;


    public CreditService(CreditRepository creditRepository, CustomerService customerService) {
        this.creditRepository = creditRepository;
        this.customerService = customerService;
    }

    @Transactional
    public Long createCredit(NewCreditRequest request) {
        DataValidationUtil.validateCreditNameField(request.getCredit().getCreditName());
        DataValidationUtil.validateCreditValue(request.getCredit().getValue());
        List<CustomerData> customer = customerService.searchCustomer(request.getCustomer().getPesel());
        Long lastCreditId;
        if (!customer.isEmpty() && !customer.contains(null)) {
            lastCreditId = createCreditWithCustomerId(
                    new CreditData(
                            request.getCredit().getCreditName(),
                            request.getCredit().getValue()),
                    customer.get(0).getCustomerId());
        } else {
            Long newCustomerId = customerService.createCustomer(
                    new CustomerData(
                            request.getCustomer().getFirstName(),
                            request.getCustomer().getLastName(),
                            request.getCustomer().getPesel()));
            lastCreditId = createCreditWithCustomerId(new CreditData(request.getCredit().getCreditName(),
                            request.getCredit().getValue()),
                    newCustomerId);
        }
        return lastCreditId;
    }

    public List<GetCreditRequest> findAll() {
        List<CreditData> credits = creditRepository.findAll();
        List<Long> customersIds = prepareCustomersIdsFromList(credits);
        List<CustomerData> customers = customerService.getCustomers(customersIds);
        return prepareCredits(credits, customers);
    }


    @Transactional
    private Long createCreditWithCustomerId(CreditData creditData, Long customerId) {
        CreditData newCredit = creditRepository.saveAndFlush(new CreditData(customerId, creditData.getCreditName(),
                creditData.getValue()));

        return newCredit.getCreditId();
    }

    private List<GetCreditRequest> prepareCredits(List<CreditData> listOfCredits,
                                                  List<CustomerData> listOfCustomers) {
        List<GetCreditRequest> list = new ArrayList<>();
        for (CreditData credit : listOfCredits) {
            for (CustomerData customer : listOfCustomers) {
                if (credit.getCustomerId().equals(customer.getCustomerId())) {
                    list.add(new GetCreditRequest(
                            new CustomerData(
                                    customer.getFirstName(),
                                    customer.getLastName(),
                                    customer.getPesel()),
                            new CreditData(
                                    credit.getCreditName(),
                                    credit.getValue(),
                                    credit.getCreditId())));
                }
            }
        }
        return list;
    }

    private List<Long> prepareCustomersIdsFromList(List<CreditData> list) {
        List<Long> customerIds = new ArrayList<>();
        for (CreditData data : list) {
            customerIds.add(data.getCustomerId());
        }
        return customerIds;
    }


}
