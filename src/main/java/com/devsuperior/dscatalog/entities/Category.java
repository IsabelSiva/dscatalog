package com.devsuperior.dscatalog.entities;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
}
