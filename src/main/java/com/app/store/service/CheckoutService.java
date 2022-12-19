package com.app.store.service;

import com.app.store.dto.Purchase;
import com.app.store.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

}
