package com.bdavila;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class HttpServiceVerticleTest {

    @Test
    public void shouldFailWith404(TestContext testContext) {
        Async async = testContext.async();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerVerticle(), res ->{
            if(res.succeeded()){
                HttpClient client = vertx.createHttpClient();
                HttpClientRequest request = client.get("localhost", "/spacex/engines/no");

                request.handler(response->{
                    testContext.assertEquals(404, response.statusCode());
                    async.complete();
                });
                request.end();
            }else{
                testContext.fail(res.cause());
            }
        });
    }
    @Test
    public void shouldReturn200(TestContext testContext) {
        Async async = testContext.async();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerVerticle(), res ->{
            if(res.succeeded()){
                HttpClient client = vertx.createHttpClient();
                HttpClientRequest request = client.get("localhost", "/spacex/engines");

                request.handler(response->{
                    testContext.assertEquals(200, response.statusCode());
                    response.bodyHandler(handler->{
                        testContext.assertEquals("you made it", handler.toString());
                        async.complete();
                    });

                });
                request.end();
            }else{
                testContext.fail(res.cause());
            }
        });
    }
}
