package com.devcourse.springbootbasic.application.customer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    @ParameterizedTest
    @DisplayName("고객 정보가 출력되면 성공한다.")
    @MethodSource("provideCustomers")
    void ToString_Customer_ReturnCustomerString(UUID customerId, String name, Customer customer) {
        var expected = MessageFormat.format("Customer(id: {0}, name: {1})", customerId, name);
        var result = customer.toString();
        assertEquals(expected, result);
    }

    static Stream<Arguments> provideCustomers() {
        return Stream.of(
                Arguments.of(
                        UUID.fromString("061d89ad-1a6a-11ee-aed4-0242ac110002"), "사과",
                        new Customer(UUID.fromString("061d89ad-1a6a-11ee-aed4-0242ac110002"), "사과")),
                Arguments.of(UUID.fromString("06201b27-1a6a-11ee-aed4-0242ac110002"), "딸기",
                        new Customer(UUID.fromString("06201b27-1a6a-11ee-aed4-0242ac110002"), "딸기")),
                Arguments.of(UUID.fromString("06223606-1a6a-11ee-aed4-0242ac110002"), "포도",
                        new Customer(UUID.fromString("06223606-1a6a-11ee-aed4-0242ac110002"), "포도")),
                Arguments.of(UUID.fromString("06223606-1a6a-11ee-aed4-0242ac110003"), "배",
                        new Customer(UUID.fromString("06223606-1a6a-11ee-aed4-0242ac110003"), "배"))
        );
    }

}