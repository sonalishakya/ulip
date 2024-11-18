package com.ondc.ulip.DTO;

import java.util.List;

public class SarathiResponse {

    private String error;
    private String code;
    private String message;
    private List<ResponseItem> response;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseItem> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseItem> response) {
        this.response = response;
    }

    public static class ResponseItem {
        private InnerResponse response;
        private String responseStatus;

        public InnerResponse getResponse() {
            return response;
        }

        public void setResponse(InnerResponse response) {
            this.response = response;
        }

        public String getResponseStatus() {
            return responseStatus;
        }

        public void setResponseStatus(String responseStatus) {
            this.responseStatus = responseStatus;
        }
    }

    public static class InnerResponse {
        private String licOj;
        private String nho;

        public String getLicOj() {
            return licOj;
        }

        public void setLicOj(String licOj) {
            this.licOj = licOj;
        }

        public String getNho() {
            return nho;
        }

        public void setNho(String nho) {
            this.nho = nho;
        }
    }
}