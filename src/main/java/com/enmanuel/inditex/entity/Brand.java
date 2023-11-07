package com.enmanuel.inditex.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("BRAND")
public class Brand {

    @Id
    private Long id;
    private String name;
}
