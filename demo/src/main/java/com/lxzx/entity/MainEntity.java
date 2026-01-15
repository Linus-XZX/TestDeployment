package com.lxzx.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import jakarta.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_class")
public class MainEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "test_bools", nullable = true)
    private Boolean test;

    @Column(name = "test_bool_array", nullable = true)
    private Boolean[] testArray;

    @Column(name = "test_bool_list", nullable = true)
    private List<Boolean> testList;
}
