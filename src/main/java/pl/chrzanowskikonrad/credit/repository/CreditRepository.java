package pl.chrzanowskikonrad.credit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.chrzanowskikonrad.credit.domain.CreditData;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<CreditData, Long> {

}
