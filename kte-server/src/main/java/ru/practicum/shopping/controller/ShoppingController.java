package ru.practicum.shopping.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.product.service.ProductService;
import ru.practicum.shopping.dto.ShoppingDtoRequest;
import ru.practicum.shopping.service.ShoppingService;
import ru.practicum.validator.Details;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rest/shopping")
@Slf4j
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingController {
    private final ShoppingService shoppingService;
    private final ProductService productService;

    @JsonView({Details.class})
    @PostMapping("{shoppingList}/{clientId}/{totalPrice}")
    public ResponseEntity<String> createShopping(@PathVariable("shoppingList") List<ShoppingDtoRequest> shoppingList,
                                                 @PathVariable("clientId") Long clientId,
                                                 @PathVariable("totalPrice") Long totalPrice) {
        log.info("покупка товаров");
        if (Objects.equals(productService.getFinalPrice(shoppingList, clientId), totalPrice))
            return ResponseEntity.status(HttpStatus.CREATED).body(shoppingService.RegistrationSale(shoppingList,
                    clientId, totalPrice));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Итоговая цена изменилась");
    }
}
