package de.fhdw.freefeser.app.util;

import de.fhdw.freefeser.api.util.HttpClientWrapper;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class JavaHttpClientWrapper implements HttpClientWrapper {
    private final HttpClient httpClient;

    public JavaHttpClientWrapper() {
        this.httpClient = HttpClient.newBuilder().build();
    }

    @Override
    public CompletableFuture<HttpResponse<String>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<String> responseBodyHandler) {
        return httpClient.sendAsync(request, responseBodyHandler);
    }
}

