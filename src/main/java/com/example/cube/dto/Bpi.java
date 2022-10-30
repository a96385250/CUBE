package com.example.cube.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BPI")
public class Bpi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "CurrencyID", nullable = false)
    private int currencyID;

    @Column(name = "Code", nullable = false)
    private String code;

    @Column(name = "Rate", nullable = false)
    private String rate;

    @Column(name = "Description")
    private String description;

    @Column(name = "Ratefloat")
    private BigDecimal ratefloat;
}
