package com.pk.jpa.closingPrice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Component
public class ClosingPriceService {
    // a little trick to allow code to use the repo statically
    private static ClosingPriceService INSTANCE;

    private final ClosingPriceRepo repo;

    @Autowired
    public ClosingPriceService(ClosingPriceRepo repo) {
        this.repo = repo;
    }

    public static ClosingPriceService get() {
        return INSTANCE;
    }

    @PostConstruct
    public void postConstruct() {
        INSTANCE = this;
        log.info("closing price service constructed");
    }

    public Optional<ClosingPrice> findById(ClosingPrice.CompositeKey key) {
        return repo.findById(key);
    }
}
