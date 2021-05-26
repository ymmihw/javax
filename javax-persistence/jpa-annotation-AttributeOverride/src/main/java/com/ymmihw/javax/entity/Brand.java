package com.ymmihw.javax.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

@Embeddable
@Setter
@Getter
public class Brand {
    private String name;
    private LocalDate foundationDate;
    @Embedded
    private Address address;
}
