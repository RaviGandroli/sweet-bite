package com.tap.model;

import java.util.HashMap;
import java.util.Map;

public class CartCreator {

    private Map<Integer, CartItem> cart;

    public CartCreator() {
        cart = new HashMap<>();
    }

    public double getTotalAmount() {
        double totalAmount = 0.0;
        for (CartItem item : cart.values()) {
            totalAmount += item.getPrice();
        }
        return totalAmount;
    }
    public void addCartItem(CartItem ci) {
        if (cart.containsKey(ci.getItemId())) {
            // Item already exists, update quantity and price
            CartItem existingItem = cart.get(ci.getItemId());
            existingItem.setQuantity(existingItem.getQuantity() + ci.getQuantity());
            existingItem.setPrice(existingItem.getPrice() + ci.getPrice() * ci.getQuantity());
            
        } else {
            // New item, add to cart
            ci.setPrice(ci.getPrice() * ci.getQuantity());
            cart.put(ci.getItemId(), ci);
        }
    }

    public void updateCartItem(int itemId, int quantity, double price) {
        if (cart.containsKey(itemId)) {
            CartItem item = cart.get(itemId);
            item.setQuantity(quantity);
            item.setPrice(quantity * price); // Update price based on quantity and new price
        }
    }

    public void increaseCartItem(int itemId, double unitPrice) {
        if (cart.containsKey(itemId)) {
            CartItem item = cart.get(itemId);
            item.setQuantity(item.getQuantity() + 1);
            item.setPrice(item.getPrice() + unitPrice); // Increase price by unit price
        }
    }

    public void decreaseCartItem(int itemId, double unitPrice) {
        if (cart.containsKey(itemId)) {
            CartItem item = cart.get(itemId);
            int newQuantity = item.getQuantity() - 1;
            if (newQuantity <= 0) {
                cart.remove(itemId);
            } else {
                item.setQuantity(newQuantity);
                item.setPrice(item.getPrice() - unitPrice); // Decrease price by unit price
            }
        }
    }

    public void removeCartItem(int itemId) {
        cart.remove(itemId);
    }

    public Map<Integer, CartItem> getAllItems() {
        return cart;
    }
}
