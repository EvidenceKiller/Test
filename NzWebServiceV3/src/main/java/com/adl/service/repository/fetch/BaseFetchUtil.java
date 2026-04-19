package com.adl.service.repository.fetch;

import com.adl.service.data.BaseResponse;
import com.adl.service.exception.NzEmptyResponseException;
import com.adl.service.exception.NzNetworkException;

public class BaseFetchUtil {

    public static <T> T scheduleResponse(BaseResponse<T> response) throws NzNetworkException, NzEmptyResponseException {
        if (response == null) {
            throw new NzEmptyResponseException("response is null");
        }
        if (!response.getSucceed().booleanValue() || response.getCode() != 200) {
            throw new NzNetworkException(response.getCode(), response.getMsg());
        }
        return response.getData();
    }
}
