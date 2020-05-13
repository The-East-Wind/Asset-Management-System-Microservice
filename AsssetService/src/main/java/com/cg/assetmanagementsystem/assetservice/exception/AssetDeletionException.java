package com.cg.assetmanagementsystem.assetservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AssetDeletionException extends Exception {
    public AssetDeletionException(String message) {
        super(message);
    }

    public AssetDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
