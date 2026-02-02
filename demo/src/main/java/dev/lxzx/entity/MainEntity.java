package dev.lxzx.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonRawValue;

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

    @Column(name = "group_id", nullable = true)
    private Long groupId;

    @JsonRawValue
    @Column(name = "test", nullable = true)
    private String test;

    @Column(name = "test_wrapped", nullable = true)
    private String testWrapped;
}
