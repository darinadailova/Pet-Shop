package com.s14.petshop.service;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.dtos.product.ProductForAddingInCartDTO;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService extends AbstractService {
    public void addProductToCart(HttpServletRequest request, int productId, int quantity) {
        Product product = productRepository.findProductById(productId)
                .orElseThrow(() -> new NotFoundException("Product wasn't found"));
        if (product.getQuantity() < quantity) {
            throw new NotFoundException("We only have " + product.getQuantity() + " from this product");
        }
        HttpSession session = request.getSession();
        List<ProductForAddingInCartDTO> cart;
        if (session.getAttribute("cart") == null) {
            cart = new ArrayList<>();
        } else {
            cart = (List<ProductForAddingInCartDTO>) session.getAttribute("cart");
        }

        ProductForAddingInCartDTO productForAddingInCart = modelMapper.map(product, ProductForAddingInCartDTO.class);
        // calculate price for a single item
        double price = product.getPrice() - (product.getPrice() * (product.getDiscount().getPercentDiscount() * 0.01));

        for (ProductForAddingInCartDTO product2 : cart) {
            if (product2.getId() == productForAddingInCart.getId()) {
                product.setQuantity(product.getQuantity() - quantity);
                quantity = product2.getRequestedQuantity() + quantity;
                productForAddingInCart.setRequestedQuantity(quantity);
                productForAddingInCart.setPrice(price * quantity);
                cart.remove(product2);
                cart.add(productForAddingInCart);
                session.setAttribute("cart", cart);

                productRepository.save(product);
                return;
            }
        }

        productForAddingInCart.setRequestedQuantity(quantity);
        productForAddingInCart.setPrice(price * quantity);
        productForAddingInCart.setBrandName(product.getBrand().getName());
        cart.add(productForAddingInCart);
        session.setAttribute("cart", cart);

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

    public List<ProductForAddingInCartDTO> getProducts(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            throw new NotFoundException("The cart is empty");
        }
        List<ProductForAddingInCartDTO> products = (List<ProductForAddingInCartDTO>) session.getAttribute("cart");
        return products;
    }

    public List<ProductForAddingInCartDTO> deleteCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            throw new NotFoundException("The cart is already empty");
        }
        session.setAttribute("cart", null);
        List<ProductForAddingInCartDTO> products = (List<ProductForAddingInCartDTO>) session.getAttribute("cart");
        return products;
    }
}
