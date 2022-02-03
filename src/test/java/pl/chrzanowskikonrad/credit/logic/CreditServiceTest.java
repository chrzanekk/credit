package pl.chrzanowskikonrad.credit.logic;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import pl.chrzanowskikonrad.credit.api.GetCreditRequest;
import pl.chrzanowskikonrad.credit.api.GetCustomersResponse;
import pl.chrzanowskikonrad.credit.api.NewCreditRequest;
import pl.chrzanowskikonrad.credit.api.SearchCustomerResponse;
import pl.chrzanowskikonrad.credit.domain.CreditData;
import pl.chrzanowskikonrad.credit.domain.CustomerData;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditServiceTest {
    @Value("${customer.service.url}")
    private String customersServiceUrl;

    @Autowired
    private CreditService creditService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testIfNewCreditCanBeSavedInDBWhenCustomerExists() throws URISyntaxException, JsonProcessingException {
        CustomerData customer = new CustomerData(1L, "Jan", "Kowalski", "80010112345");
        List<CustomerData> searchResult = new ArrayList<>();
        searchResult.add(customer);
        SearchCustomerResponse response = new SearchCustomerResponse(searchResult);
        mockServer.expect(
                        ExpectedCount.once(), requestTo(new URI(customersServiceUrl + "/customer/search")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response))
                );

        Long newCreditId = creditService.createCredit(new NewCreditRequest(
                new CreditData("Testowy kredyt", new BigDecimal("20000.00")),
                new CustomerData( "Jan","Kowalski", "80010112345")));

        Long expectedCreditId = 3L;
        mockServer.verify();
        Assert.assertEquals(expectedCreditId, newCreditId);
    }

    @Test
    public void testIfNewCreditCanBeSavedInDBWhenCustomerDoesntExists() throws URISyntaxException,
            JsonProcessingException {
        CustomerData newCustomer = new CustomerData(1L,"Henryk", "Nowak", "82010112345");
        List<CustomerData> searchResult = new ArrayList<>();
        SearchCustomerResponse response = new SearchCustomerResponse(searchResult);

        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI(customersServiceUrl + "/customer/search")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response))
                );

        mockServer.expect(
                        ExpectedCount.once(), requestTo(new URI(customersServiceUrl + "/customer")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(newCustomer))
                );
        Long newCreditId = creditService.createCredit(new NewCreditRequest(
                new CreditData("Testowy kredyt", new BigDecimal("20000.00")),
                new CustomerData( "Jan","Kowalski", "80010112345")));

        Long expectedCreditId = 2L;
        mockServer.verify();
        Assert.assertEquals(expectedCreditId, newCreditId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNewCreditCanBeSavedInDBWhenCustomerExistsAndCreditValueIsNegative() throws URISyntaxException,
            JsonProcessingException {
        CustomerData customer1 = new CustomerData(1L, "Jan", "Kowalski", "80010112345");
        CustomerData customer2 = new CustomerData(2L,"Henryk", "Nowak", "82010112345");

        List<CustomerData> searchResult = new ArrayList<>();
        searchResult.add(customer1);
        searchResult.add(customer2);
        SearchCustomerResponse response = new SearchCustomerResponse(searchResult);
        mockServer.expect(
                        ExpectedCount.once(), requestTo(new URI(customersServiceUrl + "/customer/search")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response))
                );

        Long newCreditId = creditService.createCredit(new NewCreditRequest(
                new CreditData("Testowy kredyt", new BigDecimal("-20000.00")),
                new CustomerData( "Jan","Kowalski", "80010112345")));

        Long expectedCreditId = 3L;
        mockServer.verify();
        Assert.assertEquals(expectedCreditId, newCreditId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNewCreditCanBeSavedInDBWhenCustomerExistsAndCreditNameIsEmpty() throws URISyntaxException,
            JsonProcessingException {
        CustomerData customer = new CustomerData(1L, "Jan", "Kowalski", "80010112345");
        List<CustomerData> searchResult = new ArrayList<>();
        searchResult.add(customer);
        SearchCustomerResponse response = new SearchCustomerResponse(searchResult);
        mockServer.expect(
                        ExpectedCount.once(), requestTo(new URI(customersServiceUrl + "/customer/search")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response))
                );

        Long newCreditId = creditService.createCredit(new NewCreditRequest(
                new CreditData("", new BigDecimal("20000.00")),
                new CustomerData( "Jan","Kowalski", "80010112345")));

        Long expectedCreditId = 3L;
        mockServer.verify();
        Assert.assertEquals(expectedCreditId, newCreditId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNewCreditCanBeSavedInDBWhenCustomerExistsAndCreditNameIsNull() throws URISyntaxException,
            JsonProcessingException {
        CustomerData customer = new CustomerData(1L, "Jan", "Kowalski", "80010112345");
        List<CustomerData> searchResult = new ArrayList<>();
        searchResult.add(customer);
        SearchCustomerResponse response = new SearchCustomerResponse(searchResult);
        mockServer.expect(
                        ExpectedCount.once(), requestTo(new URI(customersServiceUrl + "/customer/search")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response))
                );

        Long newCreditId = creditService.createCredit(new NewCreditRequest(
                new CreditData(null, new BigDecimal("20000.00")),
                new CustomerData( "Jan","Kowalski", "80010112345")));

        Long expectedCreditId = 3L;
        mockServer.verify();
        Assert.assertEquals(expectedCreditId, newCreditId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNewCreditCanBeSavedInDBWhenCustomerExistsAndCreditNameLengthIsOutOfBound() throws URISyntaxException,
            JsonProcessingException {
        CustomerData customer = new CustomerData(1L, "Jan", "Kowalski", "80010112345");
        List<CustomerData> searchResult = new ArrayList<>();
        searchResult.add(customer);
        SearchCustomerResponse response = new SearchCustomerResponse(searchResult);
        mockServer.expect(
                        ExpectedCount.once(), requestTo(new URI(customersServiceUrl + "/customer/search")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response))
                );
        Long newCreditId = creditService.createCredit(new NewCreditRequest(
                new CreditData("CreditNameCreditNameCreditNameCreditNameCreditNameCreditName", new BigDecimal("20000.00")),
                new CustomerData( "Jan","Kowalski", "80010112345")));

        Long expectedCreditId = 3L;
        mockServer.verify();
        Assert.assertEquals(expectedCreditId, newCreditId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNewCreditCanBeSavedInDBWhenCustomerExistsAndValueIsEmpty() throws URISyntaxException,
            JsonProcessingException {
        CustomerData customer = new CustomerData(1L, "Jan", "Kowalski", "80010112345");
        List<CustomerData> searchResult = new ArrayList<>();
        searchResult.add(customer);
        SearchCustomerResponse response = new SearchCustomerResponse(searchResult);
        mockServer.expect(
                        ExpectedCount.once(), requestTo(new URI(customersServiceUrl + "/customer/search")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response))
                );

        Long newCreditId = creditService.createCredit(new NewCreditRequest(
                new CreditData("Testowy kredyt", new BigDecimal("")),
                new CustomerData( "Jan","Kowalski", "80010112345")));

        Long expectedCreditId = 3L;
        mockServer.verify();
        Assert.assertEquals(expectedCreditId, newCreditId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNewCreditCanBeSavedInDBWhenCustomerExistsAndValueIsNull() throws URISyntaxException,
            JsonProcessingException {
        CustomerData customer = new CustomerData(1L, "Jan", "Kowalski", "80010112345");
        List<CustomerData> searchResult = new ArrayList<>();
        searchResult.add(customer);
        SearchCustomerResponse response = new SearchCustomerResponse(searchResult);
        mockServer.expect(
                        ExpectedCount.once(), requestTo(new URI(customersServiceUrl + "/customer/search")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response))
                );

        Long newCreditId = creditService.createCredit(new NewCreditRequest(
                new CreditData("Testowy kredyt", null),
                new CustomerData( "Jan","Kowalski", "80010112345")));

        Long expectedCreditId = 3L;
        mockServer.verify();
        Assert.assertEquals(expectedCreditId, newCreditId);
    }

    @Test
    public void testIfAnyCreditExistsInDB() throws URISyntaxException, JsonProcessingException {
        List<CustomerData> customers = new ArrayList<>();
        CustomerData customer = new CustomerData(1L, "Jan", "Kowalski", "80010112345");
        customers.add(customer);
        GetCustomersResponse response = new GetCustomersResponse(customers);

        mockServer.expect(
                        ExpectedCount.once(), requestTo(new URI(customersServiceUrl + "/customer/filtered")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(response)));

        List<GetCreditRequest> credits = creditService.findAll();
        mockServer.verify();
        Assert.assertEquals(1,credits.size());
    }
}
