package com.grocery.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Long userId;
    private List<OrderDetailDTO> orderDetails;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderDetailDTO {
        private Long itemId;
        private int quantity;

    }

}
