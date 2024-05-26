package com.a14.emart.backendbchr.repository;

import com.a14.emart.backendbchr.models.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BalanceRepositoryTest {
    
    @Autowired
    private BalanceRepository balanceRepository;

    @Test
    public void testFindByUserId() {
        UUID userId = UUID.randomUUID(); 
        BigDecimal nominal = BigDecimal.valueOf(0);
        // Balance balance = new Balance(userId, nominal);
        Balance balance = Balance.getBuilder()
                .setUserId(userId)
                .setNominal(nominal)
                .build();
        balanceRepository.save(balance);

        Balance foundBalance = balanceRepository.findByUserId(userId);

        assertNotNull(foundBalance);
        assertEquals(userId, foundBalance.getUserId());
        assertEquals(nominal, foundBalance.getNominal());
    }

    @Test
    public void testFindByUserIdNonExisting() {
        UUID nonExistingUserId = UUID.randomUUID();

        Balance foundBalance = balanceRepository.findByUserId(nonExistingUserId);
        
        assertNull(foundBalance);
    }
}
