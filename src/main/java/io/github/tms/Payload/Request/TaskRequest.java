package io.github.tms.Payload.Request;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequest(
    @NotBlank
    @NonNull
    String title,
    
    @NotNull
    @NotBlank
    String descreption

) {
}
