package com.jay.emaildigest.model;

import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode(of = "email")
@Builder
public class User {
    private String email;
    private String name;
}
