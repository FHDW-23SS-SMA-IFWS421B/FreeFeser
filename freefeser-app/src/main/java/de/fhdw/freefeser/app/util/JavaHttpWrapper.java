package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.HttpWrapper;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class JavaHttpWrapper implements HttpWrapper {
    private final java.net.http.HttpClient httpClient;

    public JavaHttpWrapper() {
        this.httpClient = java.net.http.HttpClient.newBuilder().build();
    }

    @Override
    public CompletableFuture<HttpResponse<String>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<String> responseBodyHandler) {
        return httpClient.sendAsync(request, responseBodyHandler);
    }
}

