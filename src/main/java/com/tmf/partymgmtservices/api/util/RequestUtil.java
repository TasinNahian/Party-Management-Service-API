package com.tmf.partymgmtservices.api.util;

import com.tmf.partymgmtservices.api.exceptions.RequestException;

import java.util.Map;

public class RequestUtil {
    private static final String LIMIT="limit";
    private static final String OFFSET="offset";

    public static int[] validateRequestParams(Map<String, Object> requestParams) {
        if (!requestParams.containsKey(LIMIT)) {
            requestParams.put(LIMIT, 20);
        }
        if(!requestParams.containsKey(OFFSET)){
            requestParams.put(OFFSET, 0);
        }

        Integer limit = Integer.valueOf(requestParams.get(LIMIT).toString());
        Integer offset = Integer.valueOf(requestParams.get(OFFSET).toString());
        if (limit < 0 || limit > 25) {
            throw new RequestException("Invalid limit value, valid values between 1-25");
        }
        if (offset < 0) {
            throw new RequestException("Invalid offset value");
        }
        requestParams.remove(LIMIT);
        requestParams.remove(OFFSET);
        return new int[]{limit, offset};
    }
}
