package com.bdavila;

import com.bdavila.impl.EngineServiceImpl;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.serviceproxy.ServiceBinder;

/**
 * Created by barbara on 2/27/19.
 */
public class HttpServerVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        EngineService service = new EngineServiceImpl();
        // Register the handler
        new ServiceBinder(vertx)
                .setAddress("spacex_service.get")
                .register(EngineService.class, service);
        OpenAPI3RouterFactory.create(vertx, "open-api-specs/server.yaml", res -> {
            if (res.failed()) {
                startFuture.fail(res.cause());
            } else {
                OpenAPI3RouterFactory routerFactory = res.result();
                routerFactory.mountServicesFromExtensions();
                Router router = routerFactory.getRouter();
                HttpServerOptions options = new HttpServerOptions()
                        .setHost("0.0.0.0")
                        .setPort(8080);
                HttpServer server = vertx.createHttpServer(options);
                server.requestHandler(router).listen();
                startFuture.complete();
            }
        });

    }
}
