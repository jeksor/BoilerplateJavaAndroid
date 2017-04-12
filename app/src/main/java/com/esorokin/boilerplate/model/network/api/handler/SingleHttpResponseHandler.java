package com.esorokin.boilerplate.model.network.api.handler;

import io.reactivex.Single;
import retrofit2.Response;

public interface SingleHttpResponseHandler<BaseResponse> {
	Single<BaseResponse> handleHttpResponse(Response<BaseResponse> response);
}
