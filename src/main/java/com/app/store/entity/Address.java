package com.app.store.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "main_address")
    private String mainAddress;
    @Column(name = "complement")
    private String complement;
    @Column(name = "department")
    private String department;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip_code")
    private String zipCode;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;
}
