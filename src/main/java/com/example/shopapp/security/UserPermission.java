package com.example.shopapp.security;

public enum UserPermission {
    PRODUCT_READ("product:read"),
    CART_READ("cart:read"),
    ORDER_READ("order:read");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
