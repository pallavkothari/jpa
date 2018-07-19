package com.pk.jpa.closingPrice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@IdClass(ClosingPrice.CompositeKey.class)
public class ClosingPrice {
    @Id
    private String symbol;
    @Id
    private LocalDate date;
    private double closingPrice;

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static final class CompositeKey implements Serializable {
        private String symbol;
        private LocalDate date;
    }
}
