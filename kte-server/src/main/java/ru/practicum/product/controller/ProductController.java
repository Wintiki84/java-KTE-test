package ru.practicum.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.util.StatisticProduct;
import ru.practicum.model.util.TotalPriceShopingListRequest;
import ru.practicum.model.util.TotalPriceShopingListResponse;
import ru.practicum.product.dto.ProductDto;
import ru.practicum.product.dto.ProductInfo;
import ru.practicum.product.model.Product;
import ru.practicum.product.service.ProductService;
import ru.practicum.reviews.service.ReviewsService;
import ru.practicum.shopping.dto.ShoppingDtoRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest/admin/product")
@Slf4j
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    private final ProductService productService;
    private final ReviewsService reviewsService;

    @GetMapping("/all")
    ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.ok(productService.allProduct());
    }

    @GetMapping("/additional/{productId}/{clientId}")
    ResponseEntity<ProductInfo> getAdditionalProductInfo(
            @PathVariable("productId") long productId,
            @PathVariable("clientId") long clientId){
        return ResponseEntity.ok(productService.GettingProductInformation(clientId, productId));
    }

    @GetMapping("/final-price/{shoppingList}/{clientId}")
    ResponseEntity<Long> getFinalPrice(
            @PathVariable("shoppingList") List<ShoppingDtoRequest> shoppingList,
            @PathVariable("clientId") Long clientId){
        return ResponseEntity.ok(productService.getFinalPrice(shoppingList, clientId));
    }
}
