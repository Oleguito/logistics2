package dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Builder
public record UserDTO (
    @NonNull
    UUID uuid,
    String fullName,
    String login,
    String password,
    int age
) {}
