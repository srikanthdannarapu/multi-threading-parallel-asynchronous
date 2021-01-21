package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static com.learnjava.util.LoggerUtil.log;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void no_Of_cores() {
        //given

        //when
        System.out.println("no of cores :" + Runtime.getRuntime().availableProcessors());

        //then
    }

    @Test
    void parallelism() {
        //given

        //when
        log("parallelism :" + ForkJoinPool.getCommonPoolParallelism());

        //then
    }

    @Test
    void checkout_6_items() {
        //given
        Cart cart= DataSet.createCart(6);

        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        //then
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }

    @Test
    void checkout_13_items() {
        //given
        Cart cart= DataSet.createCart( 13 );

        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        //then
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void modify_parallelism() {
        //given
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100");
        Cart cart= DataSet.createCart( 100 );

        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        //then
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }



}