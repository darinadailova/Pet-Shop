package com.s14.petshop.service;

import com.s14.petshop.model.beans.*;
import com.s14.petshop.model.compositekeys.ProductQuantityKey;
import com.s14.petshop.model.dtos.order.OrderResponseDTO;
import com.s14.petshop.model.dtos.product.ProductForAddingInCartDTO;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService extends AbstractService{
    @Transactional
    public OrderResponseDTO makeAnOrder(int addressId, int currentUserId, HttpSession session) {
        User user = userRepository.getById(currentUserId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<ProductForAddingInCartDTO> cart = (List<ProductForAddingInCartDTO>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            throw new BadRequestException("You don't have any products in you cart");
        }

        Order order = new Order();
        order.setOrderedAt(LocalDateTime.now());
        order.setOrderedBy(user);
        order.setAddressForDelivery(checkIfAddressIsValid(user, addressId));
        order.setPrice(findTotalPriceOfOrder(cart));
        orderRepository.save(order);

        for (ProductForAddingInCartDTO productFromCart : cart) {
            Product product = productRepository.findProductById(productFromCart.getId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));

            ProductQuantity productQuantity = createProductQuantity(order, product, productFromCart.getRequestedQuantity());
            productQuantityRepository.save(productQuantity);
        }

        cart.clear();
        session.setAttribute("cart", cart);

        orderRepository.save(order);
        user.getOrders().add(order);
        userRepository.save(user);
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    private Address checkIfAddressIsValid(User user, int addressId) {
        Address address = addressRepository.getById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found"));
        if (!user.getAddresses().contains(address)) {
            throw new BadRequestException("This address is not added to you profile");
        }
        return address;
    }

    private ProductQuantity createProductQuantity(Order order, Product product, int quantity) {
        ProductQuantityKey productQuantityKey = new ProductQuantityKey(order.getId(), product.getId());
        return new ProductQuantity(productQuantityKey, product, order, quantity);
    }

    private double findTotalPriceOfOrder(List<ProductForAddingInCartDTO> cart) {
        double price = 0;
        for (ProductForAddingInCartDTO product : cart) {
            price += product.getPrice();
        }
        return price;
    }

    public List<ProductResponseDTO> getProducts(int uid, int oid) {
        Order order = orderRepository.findById(oid)
                .orElseThrow(() -> new NotFoundException("Order does not exist!"));
        User user = userRepository.getById(uid)
                .orElseThrow(() -> new NotFoundException("User does not exist"));

        if(order.getOrderedBy().getId() != user.getId()){
            throw  new BadRequestException("You can not see the products");
        }

        List<ProductQuantity> productsQuantity = productQuantityRepository.findAllByOrder_Id(oid);

        List<ProductResponseDTO> result = new ArrayList<>();

        for (ProductQuantity productQuantity : productsQuantity) {
            result.add(modelMapper.map(productQuantity.getProduct(), ProductResponseDTO.class));
        }

        return result;
    }

    public OrderResponseDTO getOrder(int uid, int oid) {
        Order order = orderRepository.findById(oid)
                .orElseThrow(() -> new NotFoundException("Order does not exist!"));
        User user = userRepository.getById(uid)
                .orElseThrow(() -> new NotFoundException("User does not exist"));

        if(order.getOrderedBy().getId() != user.getId()){
            throw new BadRequestException("You can not see the order");
        }

        return modelMapper.map(order,OrderResponseDTO.class);
    }
}
