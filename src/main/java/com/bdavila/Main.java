package com.bdavila;

import io.vertx.core.Vertx;

/**
 * Created by barbara on 3/11/19.
 */
public class Main {
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerVerticle());
    }
}
