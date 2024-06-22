package com.spectralink.API_SLK.model.service;

import com.spectralink.API_SLK.model.dto.OrderRequestDTO;
import com.spectralink.API_SLK.model.dto.OrderResponseDTO;
import com.spectralink.API_SLK.model.dto.ProductRequestDTO;
import com.spectralink.API_SLK.model.dto.ProductResponseDTO;
import com.spectralink.API_SLK.model.entities.Order;
import com.spectralink.API_SLK.model.entities.Product;
import com.spectralink.API_SLK.model.entities.Staff;
import com.spectralink.API_SLK.model.entities.Viewer;
import com.spectralink.API_SLK.model.exception.ResourceNotFoundException;
import com.spectralink.API_SLK.model.mapper.OrderMapper;
import com.spectralink.API_SLK.model.mapper.ProductMapper;
import com.spectralink.API_SLK.model.repository.OrderRepository;
import com.spectralink.API_SLK.model.repository.ProductRepository;
import com.spectralink.API_SLK.model.repository.StaffRepository;
import com.spectralink.API_SLK.model.repository.ViewerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.lang.Number;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final StaffRepository staffRepository;
    private final ViewerRepository viewerRepository;
    private final ProductMapper productMapper;

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setCantidadProducto(orderRequestDTO.getCantidadProducto());
        order.setTotalProductos(orderRequestDTO.getTotalProductos());
        order.setEstado(orderRequestDTO.getEstado());

        Staff staff = staffRepository.findById(orderRequestDTO.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id "));
        order.setStaff(staff);

        Viewer viewer = viewerRepository.findById(orderRequestDTO.getViewer())
                .orElseThrow(() -> new ResourceNotFoundException("Viewer not found with id "));
        order.setViewer(viewer);

        // Ajuste para manejo de la lista de productos y sus cantidades
        Map<Product, Integer> productosConCantidad = new HashMap<>();
        for (ProductRequestDTO dto : orderRequestDTO.getProductos()) {

            Product product = productRepository.findById((long) productMapper.convertToEntity(dto).getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id "));
            product.setStock(product.getStock() - dto.getStock());
            productosConCantidad.put(product, dto.getStock());
        }

        order.setProductos(productosConCantidad);
        Order savedOrder = orderRepository.save(order);

        return mapToResponseDTO(savedOrder);
    }
    private OrderResponseDTO mapToResponseDTO(Order order) {
        List<ProductResponseDTO> products = order.getProductos().keySet().stream()
                .map(integer -> new ProductResponseDTO(
                        integer.getId(),
                        integer.getNombre(),
                        integer.getPrecio(),
                        integer.getDescripcion(),
                        integer.getImagen(),
                        integer.getStock(),
                        order.getId()))
                .toList();

        return new OrderResponseDTO(
                order.getId(),
                order.getCantidadProducto(),
                order.getTotalProductos(),
                order.getEstado(),
                order.getStaff().getId(),
                order.getViewer().getId(),
                products);
    }

    public List<OrderResponseDTO> getAllOrders(){
        List<Order> order = orderRepository.findAll();
        return orderMapper.convertToDTO(order);
    }

    public OrderResponseDTO getByOrderID(Long id){
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        return orderMapper.convertToDTO(order);
    }







}
