package com.example.shopapp.wrappers;

import com.example.shopapp.dto.ClientDTO;
import lombok.Data;

@Data
public class ClientWrapper {
    private ClientDTO client;
    private String orderProductIds;
}
