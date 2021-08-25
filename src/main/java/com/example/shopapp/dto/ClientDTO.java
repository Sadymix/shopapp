package com.example.shopapp.dto;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class ClientDTO{

    private Long clientId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String postalCode;

    @NotNull
    private String country;


}
