package pl.chrzanowskikonrad.credit.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "credit")
public class CreditData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creditID")
    private Long creditId;

    @Column(name = "creditName")
    private String creditName;


    @Column(name = "customerID")
    private Long customerId;

    @Column(name = "creditValue")
    private BigDecimal value;

    public CreditData(String creditName, BigDecimal value, Long creditId) {
        this.creditId = creditId;
        this.creditName = creditName;
        this.customerId = customerId;
        this.value = value;
    }

    public CreditData(Long customerId, String creditName, BigDecimal value) {
        this.creditName = creditName;
        this.customerId = customerId;
        this.value = value;
    }

    public CreditData(String creditName, BigDecimal value) {
        this.creditName = creditName;
        this.value = value;
    }

    public CreditData() {
    }

    public Long getCreditId() {
        return creditId;
    }

    public String getCreditName() {
        return creditName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getValue() {
        return value;
    }
}
