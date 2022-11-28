package com.api.systemaccount.exceptions;

import java.util.List;

public record ErrorResponse(
        Integer code,
        String message
) {
}