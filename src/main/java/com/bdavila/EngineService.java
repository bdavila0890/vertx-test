package com.bdavila;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import io.vertx.ext.web.api.generator.WebApiServiceGen;

/**
 * Created by barbara on 2/27/19.
 */
@WebApiServiceGen
public interface EngineService {
    void getEngineList(OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler);
}
