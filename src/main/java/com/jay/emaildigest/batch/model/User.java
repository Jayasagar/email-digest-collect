package com.jay.emaildigest.batch.model;

import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode(of = "email")
@Builder
public class User {
    private String email;
    private String name;
}
