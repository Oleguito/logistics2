package entities;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class UserFields {
    private final UUID uuid;
    private String fullName;
    private final String login;
    private String passwordHash;
    private int age;
}
