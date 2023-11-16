package RequestSender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestSender {
    private static final HttpClient _client = HttpClient.newBuilder().build();
    private static final String BASE_URL = "https://catfact.ninja";

    private static HttpRequest buildRequest(String uri) {
        return HttpRequest.newBuilder(URI.create(BASE_URL + uri)).setHeader("Content-Type", "application/json").build();
    }

    public <T> T get(String uri, Class<T> output) throws IOException, InterruptedException {
        var request = buildRequest(uri);
        var response = _client.send(request, HttpResponse.BodyHandlers.ofString());
        return deserialize(response.body(), output);
    }

    private <T> T deserialize(String jsonString, Class<T> output) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, output);
    }
}