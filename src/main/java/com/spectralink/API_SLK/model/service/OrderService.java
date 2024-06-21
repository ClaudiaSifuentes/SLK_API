package com.spectralink.API_SLK.model.service;

import com.spectralink.API_SLK.model.dto.OrderRequestDTO;
import com.spectralink.API_SLK.model.dto.OrderResponseDTO;
import com.spectralink.API_SLK.model.dto.ProductRequestDTO;
import com.spectralink.API_SLK.model.dto.ProductResponseDTO;
import com.spectralink.API_SLK.model.entities.Order;
import com.spectralink.API_SLK.model.entities.Product;
import com.spectralink.API_SLK.model.entities.Staff;
import com.spectralink.API_SLK.model.entities.Viewer;
import com.spectralink.API_SLK.model.mapper.OrderMapper;
import com.spectralink.API_SLK.model.repository.OrderRepository;
import com.spectralink.API_SLK.model.repository.ProductRepository;
import com.spectralink.API_SLK.model.repository.StaffRepository;
import com.spectralink.API_SLK.model.repository.ViewerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Nodes.collect;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final StaffRepository staffRepository;
    private final ViewerRepository viewerRepository;
    
   public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order orden = new Order();
        orden.setCantidadProducto(orderRequestDTO.getCantidadProducto());
        orden.setTotalProductos(orderRequestDTO.getTotalProductos());
        orden.setEstado(orderRequestDTO.isEstado());

        Staff staff = staffRepository.findById(orderRequestDTO.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff no encontrado"));
        orden.setStaff(staff);

        Viewer viewer = viewerRepository.findById(orderRequestDTO.getViewer())
                .orElseThrow(() -> new RuntimeException("viewer no encontrado"));
        orden.setViewer(viewer);

        Map<Product, Integer> productos = orderRequestDTO.getProductos().stream()
                .collect(Collectors.toMap(
                        dto -> productRepository.findById(dto.getId()
                                .orElseThrow(() -> new RuntimeException("Producto no encontrado")),
                        ProductRequestDTO::getStock));

        orden.setProductos(productos);
        Order savedOrder = orderRepository.save(orden);

        return mapToResponseDTO(savedOrder);
    }

    private OrderResponseDTO mapToResponseDTO(Order orden) {
        List<ProductResponseDTO> productos = orden.getProductos().entrySet().stream()
                .map(entry -> new ProductResponseDTO(

                        entry.getKey().getId(),
                        entry.getKey().getNombre(),
                        entry.getKey().getPrecio(),
                        entry.getKey().getDescripcion(),
                        entry.getKey().getImagen(),
                        entry.getKey().getStock(),
                        orden.getId()))
                .collect(Collectors.toList());

        return new OrderResponseDTO(
                orden.getId().intValue(),
                orden.getCantidadProducto(),
                orden.getTotalProductos(),
                orden.getEstado(),
                orden.getStaff().getId(),
                orden.getViewer().getId(),
                productos);
    }

}
