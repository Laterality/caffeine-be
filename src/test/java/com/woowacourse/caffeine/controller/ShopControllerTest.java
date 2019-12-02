package com.woowacourse.caffeine.controller;

import com.woowacourse.caffeine.application.ShopCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.woowacourse.caffeine.controller.ShopController.V1_SHOP;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void create_shop() {
        // given
        String name = "어디야 커피";
        ShopCreateRequest request = new ShopCreateRequest(name);

        // when
        EntityExchangeResult<byte[]> response = webTestClient.post()
                .uri(V1_SHOP)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), ShopCreateRequest.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader()
                .valueMatches("Location", "\\/v1\\/shop\\/\\d*")
                .expectBody().returnResult();

        // then
        webTestClient.get()
                .uri(response.getResponseHeaders().getLocation().toASCIIString())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo(name);
    }
}
