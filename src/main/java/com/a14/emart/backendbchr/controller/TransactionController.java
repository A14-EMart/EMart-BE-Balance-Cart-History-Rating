package com.a14.emart.backendbchr.controller;

import com.a14.emart.backendbchr.DTO.GetProductResponse;
import com.a14.emart.backendbchr.DTO.GetSupermarketResponse;
import com.a14.emart.backendbchr.DTO.GetTransactionResponse;
import com.a14.emart.backendbchr.models.*;
import com.a14.emart.backendbchr.rest.ProductService;
import com.a14.emart.backendbchr.rest.SupermarketService;
import com.a14.emart.backendbchr.service.JwtService;
import com.a14.emart.backendbchr.service.ShoppingCartService;
import com.a14.emart.backendbchr.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ShoppingCartService shoppingCartServices;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupermarketService supermarketService;

    private final JwtService jwtService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Transaction>> createTransaction(
            @RequestParam("komentar") String komentar,
            @RequestParam("rating") Integer rating,
            @RequestHeader("Authorization") String token) {
        try {
            String tokenWithoutBearer = token.replace("Bearer ", "");
            String role = jwtService.extractRole(tokenWithoutBearer);
            Long id = jwtService.extractUserId(tokenWithoutBearer);

            if (!role.equalsIgnoreCase("customer")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponse<>(false, null, "You have no access"));
            }

            Optional<ShoppingCart> buyerCart = shoppingCartServices.getShoppingCart(id);
            if (buyerCart.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, null, "Shopping cart not found"));
            }

            if (buyerCart.get().getItems().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, null, "Shopping cart is empty"));
            }

            if (!Objects.equals(buyerCart.get().getPembeliId(), jwtService.extractUserId(tokenWithoutBearer))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponse<>(false, null, "Error when fetching cart"));
            }

            List<CartItem> items = buyerCart.get().getItems();
            List<ProductInTransaction> products = new ArrayList<>();
            ProductInTransaction tempProduct;

            for (CartItem item : items) {
                products.add(
                    tempProduct = ProductInTransaction.builder()
                    .id(item.getProductId())
                    .quantity(item.getAmount())
                    .build()
                );
            }

            for (ProductInTransaction product : products) {
                GetProductResponse productResponse = productService.getProductById(UUID.fromString(product.getId()));
                if (productResponse != null) {
                    product.setName(productResponse.getName());
                    product.setPrice(productResponse.getPrice());
                }
            }

            long totalPrice = 0;
            for (ProductInTransaction product : products) {
                long price = product.getTotalPrice();
                totalPrice += price;
            }

            String stringSupermarketId = buyerCart.get().getSupermaketId();
            stringSupermarketId = stringSupermarketId.replace("\"", "");
            UUID supermarketId = UUID.fromString(stringSupermarketId);

            GetSupermarketResponse supermarketResponse = supermarketService.getSupermarketById(supermarketId);
            String supermarketName = supermarketResponse.getName();

            String username = "DIDI";
            Transaction transaction = Transaction.builder()
                    .supermarketId(supermarketResponse.getId())
                    .supermarketName(supermarketName)
                    .pembeliId(id)
                    .buyerName(username)
                    .tanggalDanWaktuPembelian(LocalDateTime.now())
                    .totalHarga(totalPrice)
                    .products(products)
                    .rating(rating)
                    .komentar(komentar)
                    .build();
            Transaction savedTransaction = transactionService.create(transaction);
            if (savedTransaction == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, null, "Failed to create transaction"));
            }

            return ResponseEntity.ok(new ApiResponse<>(true, savedTransaction, "Transaction created successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/find-by-buyer/{buyerId}")
    public ResponseEntity<ApiResponse<List<GetTransactionResponse>>> getTransactionByBuyer (
            @PathVariable Long buyerId,
            @RequestHeader("Authorization") String token) {

        try {
            String tokenWithoutBearer = token.replace("Bearer ", "");
            String role = jwtService.extractRole(tokenWithoutBearer);
            Long id = jwtService.extractUserId(tokenWithoutBearer);
            System.out.println(role);
            System.out.println(id);

            if (!role.equalsIgnoreCase("customer")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponse<>(false, null, "You have no access"));
            }

            if (!buyerId.equals(id)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponse<>(false, null, "You have no access"));
            }

            List<GetTransactionResponse> history = new ArrayList<>();
            List<Transaction> results = transactionService.findByBuyer(buyerId);

            for (Transaction result : results) {
                GetTransactionResponse transaction = new GetTransactionResponse(result);
                history.add(transaction);
            }
            return ResponseEntity.ok(new ApiResponse<>(true, history, "Success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/find-by-buyer-per-supermarket/")
    public ResponseEntity<ApiResponse<List<GetTransactionResponse>>> getTransactionByBuyerPerSupermarket (
            @RequestParam("buyerId") Long buyerId,
            @RequestParam("supermarketId") UUID supermarketId,
            @RequestHeader("Authorization") String token) {
        try {
            String tokenWithoutBearer = token.replace("Bearer ", "");
            String role = jwtService.extractRole(tokenWithoutBearer);
            Long id = jwtService.extractUserId(tokenWithoutBearer);
            System.out.println(role);
            System.out.println(id);

            if (!role.equalsIgnoreCase("customer")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponse<>(false, null, "You have no access"));
            }

            if (!buyerId.equals(id)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponse<>(false, null, "You have no access"));
            }

            List<GetTransactionResponse> history = new ArrayList<>();
            List<Transaction> results = transactionService.findyByBuyerPerSupermarket(buyerId, supermarketId);

            for (Transaction result : results) {
                GetTransactionResponse transaction = new GetTransactionResponse(result);
                history.add(transaction);
            }
            return ResponseEntity.ok(new ApiResponse<>(true, history, "Success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/find-by-supermarket/{idSupermarket}")
    public ResponseEntity<ApiResponse<List<GetTransactionResponse>>> getTransactionPerSupermarket(
            @PathVariable UUID idSupermarket,
            @RequestHeader("Authorization") String token) {
        try {
            GetSupermarketResponse supermarketResponse = supermarketService.getSupermarketById(idSupermarket);
            Long idManagerSupermarket = supermarketResponse.getPengelola();
            String tokenWithoutBearer = token.replace("Bearer ", "");
            String role = jwtService.extractRole(tokenWithoutBearer);
            Long id = jwtService.extractUserId(tokenWithoutBearer);

            if (!role.equalsIgnoreCase("manager") || !idManagerSupermarket.equals(id)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ApiResponse<>(false, null, "You have no access"));
            }

            List<GetTransactionResponse> history = new ArrayList<>();
            List<Transaction> results = transactionService.findBySupermarket(idSupermarket);

            for (Transaction result : results) {
                GetTransactionResponse transaction = new GetTransactionResponse(result);
                history.add(transaction);
            }
            return ResponseEntity.ok(new ApiResponse<>(true, history, "Success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}