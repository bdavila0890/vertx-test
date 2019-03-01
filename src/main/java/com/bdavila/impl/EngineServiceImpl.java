package com.bdavila.impl;

import com.bdavila.EngineService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

/**
 * Created by barbara on 2/27/19.
 */
public class EngineServiceImpl implements EngineService {


    @Override
    public void getEngineList(OperationRequest context, Handler<AsyncResult<OperationResponse>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(OperationResponse.completedWithPlainText(Buffer.buffer().appendString("you made it"))));//new JsonArray().add(new JsonObject().put("name", "name").put("description", "description")))));

    }
}
