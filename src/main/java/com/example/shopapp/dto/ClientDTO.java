package com.example.shopapp.dto;


import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
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

    @Builder
    public ClientDTO(Long clientId, @NotNull String firstName, @NotNull String lastName, @NotNull String city,
                     @NotNull String street, @NotNull String postalCode, @NotNull String country) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.country = country;
    }
}
