package com.pk.jpa.closingPrice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClosingPriceRepo extends CrudRepository<ClosingPrice, ClosingPrice.CompositeKey> {
    List<ClosingPrice> findBySymbol(String symbol);
}
