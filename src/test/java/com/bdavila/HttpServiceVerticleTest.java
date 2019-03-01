package com.bdavila;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class HttpServiceVerticleTest {

    private static Vertx vertx;

    @BeforeClass
    public static void standUpServer(TestContext testContext) {
        Async async = testContext.async();
        vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerVerticle(), res -> {
            if (res.failed()) {
                testContext.fail();
            }
            async.complete();
        });
    }

    @Test
    public void shouldFailWith404(TestContext testContext) {
        Async async = testContext.async();
        HttpClientOptions options = new HttpClientOptions()
                .setDefaultHost("0.0.0.0")
                .setDefaultPort(8080);
        HttpClient client = vertx.createHttpClient(options);
        HttpClientRequest request = client.get("localhost", "/spacex/engines/no");

        request.handler(response -> {
            testContext.assertEquals(404, response.statusCode());
            async.complete();
        });
        request.end();
    }

    @Test
    public void shouldReturn200(TestContext testContext) {
        Async async = testContext.async();
        HttpClientOptions options = new HttpClientOptions()
                .setDefaultHost("0.0.0.0")
                .setDefaultPort(8080);
        HttpClient client = vertx.createHttpClient(options);
        HttpClientRequest request = client.get("localhost", "/spacex/engines");

        request.handler(response -> {
            testContext.assertEquals(200, response.statusCode());
            response.bodyHandler(handler -> {
                testContext.assertEquals("you made it", handler.toString());
                async.complete();
            });
        });
        request.end();
    }
}
