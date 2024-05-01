package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.model.CartItem;
import com.a14.emart.backendbchr.model.Product;
import com.a14.emart.backendbchr.model.ShoppingCart;
import com.a14.emart.backendbchr.repository.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceImplTest{
    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartServiceImpl;

    private ShoppingCart shoppingCart;
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setProductId("11111");
        product.setProductName("Snack Tok");
        product.setProductQuantity(10);
        product.setProductPrice(20000);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem(product, 1));
        shoppingCart = new ShoppingCart(cartItems, "123");
        shoppingCart.setId(1L);

        when(shoppingCartRepository.findById(anyLong())).thenReturn(Optional.of(shoppingCart));
    }

    @Test
    void addProductToCart() {
        shoppingCartServiceImpl.addProductToCart(shoppingCart.getId(), product, 4);

        // Verify that the product is added to the cart and the repository save method is called
        verify(shoppingCartRepository, times(1)).save(shoppingCart);
        assertEquals(1, shoppingCart.getItems().size());
        assertEquals(4, shoppingCart.getItems().get(0).getQuantity());
        assertEquals(product, shoppingCart.getItems().get(0).getProduct());
    }

    @Test
    void removeProductFromCart() {
        // First add a product to the cart
        shoppingCartServiceImpl.addProductToCart(shoppingCart.getId(), product, 3);
        shoppingCartServiceImpl.removeProductFromCart(shoppingCart.getId(), product);

        // Verify the remove operation
        verify(shoppingCartRepository, times(2)).save(shoppingCart);  // Called once for add and once for remove
        assertTrue(shoppingCart.getItems().isEmpty());
    }

    @Test
    void getShoppingCartById() {
        Optional<ShoppingCart> retrievedCart = shoppingCartServiceImpl.getShoppingCartById(1L);
        // Check that the correct cart is retrieved
        assertTrue(retrievedCart.isPresent());
        assertEquals(shoppingCart.getId(), retrievedCart.get().getId());
    }

}