package christmas.domain;

import christmas.domain.menuManage.Menu;
import christmas.domain.menuManage.OrderHistory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {

    @Test
    public void testCheckTheDiscountBeginPrice() {
        OrderHistory order1 = new OrderHistory(Menu.MUSHROOM_SOUP, 1, 1);
        OrderHistory order2 = new OrderHistory(Menu.ZERO_COLA, 1, 1);

        OrderManager orderManager = new OrderManager(Arrays.asList(order1, order2), 1);

        OrderResult result = orderManager.processOrder();

        assertEquals(0, result.getTotalDiscount());
    }

    @Test
    public void testDuplicateMenuExists() {
        OrderHistory order1 = new OrderHistory(Menu.MUSHROOM_SOUP, 1, 1);
        OrderHistory order2 = new OrderHistory(Menu.MUSHROOM_SOUP, 1, 1);

        try {
            new OrderManager(Arrays.asList(order1, order2), 1);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("[ERROR]"));
        }
    }

    @Test
    public void testOrderQuantityIsLessThanOne() {
        // 주문 수량이 0인 주문 내역을 생성
        OrderHistory order = new OrderHistory(Menu.MUSHROOM_SOUP, 0, 1);

        try {
            new OrderManager(Arrays.asList(order), 1);
        } catch (IllegalArgumentException e) {
            // 예외 메시지에 "[ERROR]" 문자열이 포함되어 있는지 확인
            assertTrue(e.getMessage().contains("[ERROR]"));
        }
    }

}