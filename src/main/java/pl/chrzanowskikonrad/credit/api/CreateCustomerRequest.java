package pl.chrzanowskikonrad.credit.api;

public class CreateCustomerRequest {
    private String firstName;
    private String lastName;
    private String pesel;

    public CreateCustomerRequest(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public CreateCustomerRequest() {
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
