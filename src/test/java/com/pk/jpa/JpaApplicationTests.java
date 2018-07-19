package com.pk.jpa;

import com.pk.jpa.closingPrice.ClosingPrice;
import com.pk.jpa.closingPrice.ClosingPriceRepo;
import com.pk.jpa.closingPrice.ClosingPriceService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

    @Autowired
    ClosingPriceRepo repo;

    @Test
    public void closingPriceTests() {
        ClosingPrice expected = ClosingPrice.builder().symbol("CRM").date(LocalDate.now()).closingPrice(100.00).build();
        repo.save(expected);

        List<ClosingPrice> closingPrices = repo.findBySymbol("CRM");
        assertThat(closingPrices, is(Lists.newArrayList(expected)));

        Optional<ClosingPrice> crm = repo.findById(ClosingPrice.CompositeKey.builder().date(LocalDate.now()).symbol("CRM").build());
        assertTrue(crm.isPresent());
        assertThat(crm.get(), is(expected));
    }

    @Test
    public void staticAccessTest() {
        ClosingPrice expected = ClosingPrice.builder().symbol("CRM").date(LocalDate.now()).closingPrice(100.00).build();
        ClosingPrice.CompositeKey key = ClosingPrice.CompositeKey.builder().date(LocalDate.now()).symbol("CRM").build();
        repo.save(expected);

        Optional<ClosingPrice> price = ClosingPriceService.get().findById(key);
        assertTrue(price.isPresent());
        assertThat(price.get(), is(expected));
    }
}
