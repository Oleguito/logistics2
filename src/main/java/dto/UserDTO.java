package dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

/*  Тут я создаю ДТО. Я не пользуюсь им в программе, но создал чтобы продемонстрировать
    свое умение работать с этим паттерном
    Обратите внимание, что я также умею делать @NonNull-поля в записях */

@Builder
public record UserDTO (
    @NonNull
    UUID uuid,
    String fullName,
    String login,
    String password,
    int age
) {}
