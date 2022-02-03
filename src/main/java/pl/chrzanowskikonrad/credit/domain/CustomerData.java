package pl.chrzanowskikonrad.credit.domain;


public class CustomerData {

    private Long customerId;

    private String firstName;

    private String lastName;

    private String pesel;


    public CustomerData() {
    }

    public CustomerData(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public CustomerData(Long customerId, String firstName, String lastName, String pesel) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
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
