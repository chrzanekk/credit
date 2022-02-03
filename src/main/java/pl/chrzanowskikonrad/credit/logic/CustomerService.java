package pl.chrzanowskikonrad.credit.logic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.chrzanowskikonrad.credit.api.*;
import pl.chrzanowskikonrad.credit.domain.CustomerData;

import java.util.List;

@Service
public class CustomerService {
    @Value("${customer.service.url}")
    private String customersServiceUrl;

    private RestTemplate restTemplate;

    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Long createCustomer(CustomerData data) {
        CreateCustomerResponse response = restTemplate.postForObject(customersServiceUrl + "/customer",
                new HttpEntity<>(new CreateCustomerRequest(data.getFirstName(), data.getLastName(), data.getPesel())),
                CreateCustomerResponse.class);
        return response.getCustomerId();
    }

    public List<CustomerData> searchCustomer(String pesel) {
        SearchCustomerResponse response = restTemplate.postForObject(customersServiceUrl + "/customer/search",
                new HttpEntity<>(new SearchCustomerRequest(pesel)),
                SearchCustomerResponse.class);
        return response.getSearchResult();
    }

    public List<CustomerData> getCustomers(List<Long> listOfIds) {
        GetCustomersResponse response = restTemplate.postForObject(customersServiceUrl + "/customer/filtered",
                new HttpEntity<>(new GetCustomersRequest(listOfIds)), GetCustomersResponse.class);
        return response.getCustomers();
    }

}
