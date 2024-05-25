package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.models.CartItem;
import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.repository.CartItemRepository;
import com.a14.emart.backendbchr.repository.ShoppingCartRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartServiceImplTest {

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    private ShoppingCart shoppingCart;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        shoppingCart = ShoppingCart.getBuilder()
                .setPembeliId(1L)
                .setSupermarketId("supermarket456")
                .build();

        cartItem = CartItem.getBuilder()
                .setPembeliId(1L)
                .setProductId("product789")
                .setAmount(2)
                .build();
    }

    @Test
    void testCreateShoppingCart() {
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(shoppingCart);

        ShoppingCart createdCart = shoppingCartService.createShoppingCart(1L);

        assertNotNull(createdCart);
        assertEquals(1L, createdCart.getPembeliId());
        verify(shoppingCartRepository, times(1)).save(any(ShoppingCart.class));
    }

    @Test
    void testGetShoppingCart() {

            Long pembeliId = 1L;
            when(shoppingCartRepository.findShoppingCartByPembeliId(pembeliId)).thenReturn(Optional.of(shoppingCart));

            Optional<ShoppingCart> result = shoppingCartService.getShoppingCart(pembeliId);

            assertTrue(result.isPresent(), "Shopping cart should be present");
            assertEquals(shoppingCart, result.get(), "The shopping cart returned should match the mock shopping cart");
    }


    @Test
    void testAddItemToCart() {
        shoppingCart.addItem(cartItem);
        when(shoppingCartRepository.findShoppingCartByPembeliId(1L)).thenReturn(Optional.of(shoppingCart));
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(shoppingCart);

        ShoppingCart updatedCart = shoppingCartService.addItemToCart(1L, "newProduct", "supermarket456");

        assertNotNull(updatedCart);
        assertEquals(2, updatedCart.getItems().size());
        assertTrue(updatedCart.getItems().stream().anyMatch(item -> item.getProductId().equals("newProduct") && item.getAmount() == 1));
        verify(shoppingCartRepository, times(1)).findShoppingCartByPembeliId(1L);
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
        verify(shoppingCartRepository, times(1)).save(any(ShoppingCart.class));
    }
    @Test
    void testAddItemToCart_ExistingItem() {
        Long pembeliId = 2L;
        String productId = "product1";
        String supermarketId = "supermarket1";

        CartItem existingItem = new CartItem();
        existingItem.setPembeliId(2L);
        existingItem.setProductId(productId);
        existingItem.setAmount(1);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(existingItem);

        ShoppingCart existingCart = new ShoppingCart();
        existingCart.setPembeliId(pembeliId);
        existingCart.setSupermaketId(supermarketId);
        existingCart.setItems(cartItems);

        when(shoppingCartRepository.findShoppingCartByPembeliId(2L)).thenReturn(Optional.of(existingCart));
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(existingCart);

        ShoppingCart updatedCart = shoppingCartService.addItemToCart(pembeliId, productId, supermarketId);

        assertNotNull(updatedCart);
        assertEquals(pembeliId, updatedCart.getPembeliId());
        assertEquals(supermarketId, updatedCart.getSupermaketId());
        assertEquals(1, updatedCart.getItems().size());
        assertEquals(productId, updatedCart.getItems().get(0).getProductId());
        assertEquals(2, updatedCart.getItems().get(0).getAmount());;
    }

    @Test
    public void testAddItemToCart_ExistingCart_DifferentSupermarket() {
        Long pembeliId = 2L;
        String productId = "product1";
        String supermarketId = "supermarket1";
        String differentSupermarketId = "supermarket2";

        ShoppingCart existingCart = new ShoppingCart();
        existingCart.setPembeliId(pembeliId);
        existingCart.setSupermaketId(differentSupermarketId);
        existingCart.setItems(new ArrayList<>());

        when(shoppingCartRepository.findShoppingCartByPembeliId(2L)).thenReturn(Optional.of(existingCart));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            shoppingCartService.addItemToCart(pembeliId, productId, supermarketId);
        });

        assertEquals("All items in the cart must be from the same supermarket", exception.getMessage());
    }


    @Test
    void testRemoveItemFromCart_decrementAmount() {
        Long pembeliId = 2L;
        String productId = "product1";

        CartItem cartItem = new CartItem();
        cartItem.setPembeliId(pembeliId);
        cartItem.setProductId(productId);
        cartItem.setAmount(2);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        ShoppingCart existingCart = new ShoppingCart();
        existingCart.setPembeliId(pembeliId);
        existingCart.setSupermaketId("supermarket1");
        existingCart.setItems(cartItems);

        when(shoppingCartRepository.findShoppingCartByPembeliId(2L)).thenReturn(Optional.of(existingCart));
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(existingCart);

        ShoppingCart updatedCart = shoppingCartService.removeItemFromCart(pembeliId, productId);

        assertNotNull(updatedCart);
        assertEquals(pembeliId, updatedCart.getPembeliId());
        assertEquals(1, updatedCart.getItems().size());
        assertEquals(productId, updatedCart.getItems().get(0).getProductId());
        assertEquals(1, updatedCart.getItems().get(0).getAmount());
    }

    @Test
    public void testRemoveItemFromCart_RemoveItem() {
        Long pembeliId = 2L;
        String productId = "product1";

        CartItem cartItem = new CartItem();
        cartItem.setPembeliId(pembeliId);
        cartItem.setProductId(productId);
        cartItem.setAmount(1);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        ShoppingCart existingCart = new ShoppingCart();
        existingCart.setPembeliId(pembeliId);
        existingCart.setSupermaketId("supermarket1");
        existingCart.setItems(cartItems);

        when(shoppingCartRepository.findShoppingCartByPembeliId(pembeliId)).thenReturn(Optional.of(existingCart));
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(existingCart);

        ShoppingCart updatedCart = shoppingCartService.removeItemFromCart(pembeliId, productId);

        assertNotNull(updatedCart);
        assertEquals(pembeliId, updatedCart.getPembeliId());
        assertEquals(0, updatedCart.getItems().size());
    }
}
