package de.fhdw.freefeser.api.util;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public interface HttpWrapper {
    CompletableFuture<HttpResponse<String>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<String> responseBodyHandler);
}
