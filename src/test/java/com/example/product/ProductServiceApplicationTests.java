package com.example.product;

import com.example.product.dto.ProductRequest;
import io.restassured.parsing.Parser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import io.restassured.*;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDbContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDbContainer.start();
    }

    @Test
    void shouldCreateProduct() {
        final ProductRequest productRequest = new ProductRequest("iphone 13", "iphone 13", BigDecimal.valueOf(1200));

        RestAssured.given()
                .contentType("application/json")
                .body(productRequest)
                .when()
                .post("/products")
                .then()
                .log().all()
                .statusCode(201);
    }

}
