package com.a14.emart.backendbchr.service;

import com.a14.emart.backendbchr.models.Product;
import com.a14.emart.backendbchr.models.ShoppingCart;
import com.a14.emart.backendbchr.repository.ShoppingCartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceImplTest{
    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartServiceImpl;

    private Product product;
    Map<Product, Integer> productQuantities = new HashMap<>();
    List<Product> products = new ArrayList<>();
    private String pembeliId = "12345";
    private ShoppingCartService shoppingCartService;
    private ShoppingCart shoppingCart = new ShoppingCart();
    @BeforeEach
    public void setUp() {
        shoppingCartService = new ShoppingCartServiceImpl(shoppingCartRepository);
        product = new Product();
        product.setProductId("11111");
        product.setProductName("Snack Tok");
        product.setProductQuantity(10);
        product.setProductPrice(20000);
        products.add(product);
        productQuantities.put(product, 4);
        shoppingCart.addProduct(product, 4);
    }

    @Test
    public void testCreateShoppingCart() {
        ShoppingCart createdCart = shoppingCartServiceImpl.createShoppingCart(shoppingCart);
        assertNotNull(createdCart);
        assertEquals(productQuantities.get(product), createdCart.getProductQuantities().get(product));
    }

//    @Test
//    void addProductToCart() {
//        int initialQuantity = shoppingCart.getProductQuantities().get(products.get(0));
//        int quantityToAdd = 3;
//        shoppingCart.addProduct(product, quantityToAdd);
//        int finalQuantity = shoppingCart.getProductQuantities().get(products.get(0));
//        // Verify that the product quantity has increased by the expected amount
//        assert (finalQuantity - initialQuantity == quantityToAdd);
//    }

//    @Test
//    void removeProductFromCart() {
//        shoppingCartServiceImpl.createShoppingCart(pembeliId);
//        int initialQuantity = productQuantities.get(products.get(0));
//        // Assuming there are some products in the cart initially
//        if (initialQuantity > 0) {
//            shoppingCart.removeProduct(product);
//            int finalQuantity = productQuantities.get(products.get(0));
//            // Verify that the product quantity has decreased
//            assert (finalQuantity < initialQuantity);
//        }
//
//    }


}