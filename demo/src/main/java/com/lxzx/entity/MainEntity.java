package com.lxzx.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "main_class")
public class MainEntity extends BaseEntity {

    public MainEntity(Boolean test) {
        super();
        this.setTest(test);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "test_bools", nullable = true)
    private Boolean test;
}
