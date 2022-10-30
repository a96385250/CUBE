package com.example.cube.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "CURRENCY")
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Currency", nullable = false)
    private String currency;

    @Column(name = "CurrencyCH", nullable = false)
    private String currencyCH;


}
