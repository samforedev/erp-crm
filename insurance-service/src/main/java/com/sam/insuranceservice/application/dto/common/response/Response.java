package com.sam.insuranceservice.application.dto.common.response;

public record Response<T>(
        boolean success,
        String message,
        T data,
        Error error
) {
    public static class Builder<T> {
        private boolean success;
        private String message;
        private T data;
        private Error error;

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }
        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }
        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }
        public Builder<T> error(Error error) {
            this.error = error;
            return this;
        }

        public Response<T> build() {
            return new Response<T>(success, message, data, error);
        }
    }

    public static <T> Response<T> success(T data, String message) {
        return new Builder<T>()
                .success(true)
                .data(data)
                .message(message)
                .error(null)
                .build();
    }

    public static <T> Response<T> error(Error error) {
        return new Builder<T>()
                .success(false)
                .message(null)
                .error(error)
                .build();
    }

}
