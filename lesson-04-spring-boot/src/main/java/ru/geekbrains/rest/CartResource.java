package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.LineItem;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartResource {

    private final CartService cartService;

    @Autowired
    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Добавление продукта в корзину пользователя
     * @param userId
     * @param productId
     * @param qty
     */
    @PostMapping("/{userId}/user/{productId}/product")
    public void addProductForUser(@PathVariable("userId") Long userId,
                                  @PathVariable("productId") Long productId,
                                  @RequestParam("qty") Integer qty) {
        cartService.addProductForUserQty(productId, userId, qty);
    }

    /**
     * Получение списка продуктов в корзине пользователя
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/user")
    public List<LineItem> findItemsForUser(@PathVariable("userId") Long userId) {
        return cartService.findAllItemsForUser(userId);
    }

    /**
     * Удаление продука у пользователя из корзины
     * @param userId
     * @param productId
     * @param qty
     */
    @PostMapping("/remove/{userId}/user/{productId}/product")
    public void removeProductForUser(@PathVariable("userId") Long userId,
                                     @PathVariable("productId") Long productId,
                                     @RequestParam("qty") Integer qty) {
        cartService.removeProductForUser(productId, userId, qty);
    }

    /**
     * Удаление всех продуктов из корзины пользователя
     * @param userId
     */
    @DeleteMapping("/remove/{userId}/user")
    public void removeAllForUser(@PathVariable("userId") Long userId) {
        cartService.removeAllForUser(userId);
    }
}
