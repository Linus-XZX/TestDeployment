package com.lxzx.entity;

import lombok.Data;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable{
    protected static final long serialVersionUID = 1L;
}
