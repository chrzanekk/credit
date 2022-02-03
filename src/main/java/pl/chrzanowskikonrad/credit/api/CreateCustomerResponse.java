package pl.chrzanowskikonrad.credit.api;

public class CreateCustomerResponse {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String pesel;

    public CreateCustomerResponse(Long customerId, String firstName, String lastName, String pesel) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public CreateCustomerResponse() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }
}
