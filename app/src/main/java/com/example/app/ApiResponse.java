package com.example.app;
// L'ApiResponse sert pour les messages de réponse
public class ApiResponse {
        private String code;
        private String message;
        private Object data;

        public ApiResponse(String code, String message, Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }
}
