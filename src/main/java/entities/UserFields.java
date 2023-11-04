package entities;

public record UserFields(
    String fullName,
    String login,
    String password,
    int age
) {}
