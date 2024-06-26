package com.a14.emart.backendbchr.Service;

import com.a14.emart.backendbchr.DTO.GetProductResponse;
import com.a14.emart.backendbchr.models.CartItem;
import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.repository.CartItemRepository;
import com.a14.emart.backendbchr.repository.ShoppingCartRepository;

import com.a14.emart.backendbchr.rest.ProductService;
import com.a14.emart.backendbchr.service.ShoppingCartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductService productService;

    private ShoppingCart shoppingCart;
    private CartItem cartItem;
    private GetProductResponse productResponse;

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

        productResponse = new GetProductResponse();
        productResponse.setPrice(50L);


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
    void testAddItemToCart_NewItem() {
        Long pembeliId = 2L;
        String productId = "ebeae1be-db03-4e67-83a1-1f63639a4c61";
        String supermarketId = "supermarket1";

        ShoppingCart existingCart = new ShoppingCart();
        existingCart.setPembeliId(pembeliId);
        existingCart.setSupermaketId(supermarketId);
        existingCart.setItems(new ArrayList<>());

        GetProductResponse productResponse = new GetProductResponse();
        productResponse.setStock(10);

        when(shoppingCartRepository.findShoppingCartByPembeliId(pembeliId)).thenReturn(Optional.of(existingCart));
        when(productService.getProductById(UUID.fromString(productId))).thenReturn(productResponse);
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(existingCart);

        ShoppingCart updatedCart = shoppingCartService.addItemToCart(pembeliId, productId, supermarketId);

        assertNotNull(updatedCart);
        assertEquals(pembeliId, updatedCart.getPembeliId());
        assertEquals(supermarketId, updatedCart.getSupermaketId());
        assertEquals(1, updatedCart.getItems().size());
        assertEquals(productId, updatedCart.getItems().get(0).getProductId());
        assertEquals(1, updatedCart.getItems().get(0).getAmount());
    }
    @Test
    void testAddItemToCart_ExistingItem() {
        Long pembeliId = 2L;
        String productId = "ebeae1be-db03-4e67-83a1-1f63639a4c61";
        String supermarketId = "supermarket1";

        CartItem existingItem = new CartItem();
        existingItem.setPembeliId(pembeliId);
        existingItem.setProductId(productId);
        existingItem.setAmount(1);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(existingItem);

        ShoppingCart existingCart = new ShoppingCart();
        existingCart.setPembeliId(pembeliId);
        existingCart.setSupermaketId(supermarketId);
        existingCart.setItems(cartItems);

        GetProductResponse productResponse = new GetProductResponse();
        productResponse.setStock(10);

        when(shoppingCartRepository.findShoppingCartByPembeliId(pembeliId)).thenReturn(Optional.of(existingCart));
        when(productService.getProductById(UUID.fromString(productId))).thenReturn(productResponse);
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(existingCart);

        ShoppingCart updatedCart = shoppingCartService.addItemToCart(pembeliId, productId, supermarketId);

        assertNotNull(updatedCart);
        assertEquals(pembeliId, updatedCart.getPembeliId());
        assertEquals(supermarketId, updatedCart.getSupermaketId());
        assertEquals(1, updatedCart.getItems().size());
        assertEquals(productId, updatedCart.getItems().get(0).getProductId());
        assertEquals(2, updatedCart.getItems().get(0).getAmount());
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