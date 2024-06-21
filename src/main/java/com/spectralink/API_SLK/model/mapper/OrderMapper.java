package com.spectralink.API_SLK.model.mapper;

import com.spectralink.API_SLK.model.dto.OrderRequestDTO;
import com.spectralink.API_SLK.model.dto.OrderResponseDTO;
import com.spectralink.API_SLK.model.entities.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;

    public Order convertToEntity(OrderRequestDTO orderRequestDTO) {
        return modelMapper.map(orderRequestDTO, Order.class);
    }

    public OrderResponseDTO convertToDTO(Order order) {
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    public List<OrderResponseDTO> convertToDTO(List<Order> order) {
        return order.stream().map(this::convertToDTO).toList();
    }

}
