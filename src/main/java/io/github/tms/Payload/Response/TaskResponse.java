package io.github.tms.Payload.Response;
import java.time.LocalDateTime;
import io.github.tms.Enum.EStatus;

public record TaskResponse(
    String id,
    String title,
    String descreption,
    EStatus status,
    LocalDateTime dueDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

   
}
