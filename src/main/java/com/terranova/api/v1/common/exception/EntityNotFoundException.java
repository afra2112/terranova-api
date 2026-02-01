package com.terranova.api.v1.common.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entity, String entityId) {
        super(entity + " not found with id: " + entityId);
    }
}
