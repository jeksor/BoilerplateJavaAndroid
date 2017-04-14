package com.esorokin.boilerplate.model.network.api.handler;

import retrofit2.Response;

public interface HttpResponseAdapter<BaseResponse> {
	BaseResponse adaptHttpResponse(Response<BaseResponse> response);
}
