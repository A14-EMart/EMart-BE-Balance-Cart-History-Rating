package com.a14.emart.backendbchr.repository;
import com.a14.emart.backendbchr.model.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ShoppingCartRepository {
    private Map<String, ShoppingCart> shoppingCarts;

    public ShoppingCartRepository() {
        this.shoppingCarts = new HashMap<>();
    }

    public void addShoppingCart(String pembeli, List<Product> products) {
        ShoppingCart shoppingCart = new ShoppingCart(products, pembeli);
        shoppingCarts.put(pembeli, shoppingCart);
    }

    public ShoppingCart getShoppingCart(String pembeli) {
        return shoppingCarts.get(pembeli);
    }

    public void removeShoppingCart(String pembeli) {
        shoppingCarts.remove(pembeli);
    }

    public boolean containsShoppingCart(String pembeli) {
        return shoppingCarts.containsKey(pembeli);
    }
}
