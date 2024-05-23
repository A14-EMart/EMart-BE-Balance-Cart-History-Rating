//package com.a14.emart.backendbchr.controller;
//
//import com.a14.emart.backendbchr.model.Pembeli;
//import com.a14.emart.backendbchr.model.Transaction;
//import com.a14.emart.backendbchr.service.TransactionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api")
//public class TransactionController {
//    @Autowired
//    private TransactionService transactionService;
//
//    @RequestMapping(value = "api/transaction", method = RequestMethod.POST)
//    public ResponseEntity addTransaction(@RequestBody Transaction transaction) {
//        ResponseEntity responseEntity = null;
//
//        try {
//            transactionService.create(transaction);
//            responseEntity = ResponseEntity.ok().build();
//        }
//        catch (Exception e) {
//            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }
//
//    @RequestMapping(value = "api/transaction", method = RequestMethod.GET)
//    public ResponseEntity getAllTransaction() {
//        ResponseEntity responseEntity = null;
//
//        try {
//            List<Transaction> transactions = transactionService.findAll();
//            responseEntity = ResponseEntity.ok(transactions);
//        }
//        catch (Exception e) {
//            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }
//
//    @RequestMapping(value = "api/transaction/{idTransaction}", method = RequestMethod.GET)
//    public ResponseEntity getTransactionById(@PathVariable UUID id) {
//        ResponseEntity responseEntity = null;
//
//        try {
//            Transaction transaction = transactionService.findById(id);
//            responseEntity = ResponseEntity.ok(transaction);
//            }
//        catch (Exception e) {
//            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }
//
//    @RequestMapping(value = "api/transaction/byBuyer", method = RequestMethod.GET)
//    public ResponseEntity getTransactionByBuyer(@RequestBody Pembeli pembeli) {
//        ResponseEntity responseEntity = null;
//
//        try {
//            List<Transaction> transactions = transactionService.findByBuyer(pembeli);
//            responseEntity = ResponseEntity.ok(transactions);
//        }
//        catch (Exception e) {
//            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }
//
//    @RequestMapping(value = "api/transaction/{idTransaction}", method = RequestMethod.DELETE)
//    public ResponseEntity deleteTransactionById(@PathVariable UUID id) {
//        ResponseEntity responseEntity = null;
//
//        try {
//            transactionService.deleteById(id);
//            responseEntity = ResponseEntity.ok().build();
//        }
//        catch (Exception e) {
//            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }
//}
