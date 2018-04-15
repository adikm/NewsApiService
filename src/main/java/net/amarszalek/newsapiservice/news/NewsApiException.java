package net.amarszalek.newsapiservice.news;

public class NewsApiException extends RuntimeException {

    private final int status;
    private final String message;

    public NewsApiException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
