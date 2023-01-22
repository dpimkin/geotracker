package com.dpimkin.geotracker.locationtracker;

import com.dpimkin.geotracker.locationtracker.api.Endpoints;
import com.dpimkin.geotracker.locationtracker.api.FindCouriersNearRequest;
import com.dpimkin.geotracker.locationtracker.api.UpdatePositionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@ThreadSafe
@RequiredArgsConstructor
public class LocationTrackerClient {
    private final String baseUrl;

    private final WebClient webClient;

    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    @Nonnull
    public Mono<String> updateCourierDisposition(@Nonnull String id, double latitude, double longitude) {
        return webClient.put()
                .uri(baseUrl + Endpoints.TRACKER_ENDPOINT + '/' + id)
                .contentType(APPLICATION_JSON)
                .bodyValue(UpdatePositionRequest.of(latitude, longitude))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode() == HttpStatus.ACCEPTED) {
                        return Mono.just("OK");
                    } else {
                        return Mono.error(new RuntimeException("TODO")); // TODO
                    }
                });


    }

    @Nonnull
    public Flux<String> findCourierNear(double latitude, double longitute, int distanceMeters) {
        return webClient.post()
                .uri(baseUrl + Endpoints.TRACKER_ENDPOINT + "/find-near")
                .contentType(APPLICATION_JSON)
                .bodyValue(FindCouriersNearRequest.of(latitude, longitute, distanceMeters))
                .exchangeToFlux(clientResponse -> Flux.empty()); // TODO
    }

    @NotThreadSafe
    public static final class Builder {
        private String baseUrl;

        private WebClient webClient;

        private Builder() {
        }

        @Nonnull
        Builder baseUrl(@Nonnull String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        @Nonnull
        Builder webClient(@Nonnull WebClient webClient) {
            this.webClient = webClient;
            return this;
        }

        @Nonnull
        LocationTrackerClient build() {
            var url = requireNonNull(baseUrl);
            return new LocationTrackerClient(url.endsWith("/") ? url : url + "/",
                    ofNullable(webClient).orElseGet(() -> WebClient.builder()
                            .build()));
        }
    }
}
