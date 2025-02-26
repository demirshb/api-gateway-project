package org.user.userservice.config;

public class Constants {
    public static class ErrorCode {
        public static final int CUSTOMER_DOES_NOT_EXIST = 1000;
        public static final int INVALID_ROLE = 2000;
        public static final int INTERNAL_ERROR = 5000;
        public static final int CUSTOMER_EXIST = 3000;
        public static final int VALIDATION_ERROR = 10000;

    }

    public static class ErrorMessage {
        public static final String CUSTOMER_DOES_NOT_EXIST = "Customer does not exist";
        public static final String INVALID_ROLE = "Invalid role";
        public static final String CUSTOMER_EXIST = "Customer already exist";
        public static final String VALIDATION_ERROR = "Validation error";

    }
    public static class Api {
        public static final String CLIENT_ID = "clientId";
    }
}
