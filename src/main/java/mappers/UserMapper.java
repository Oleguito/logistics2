package mappers;

import dto.UserDTO;
import entities.User;
import entities.UserFields;

import java.util.List;

/*  private     final       UUID uuid;
    private     String      fullName;
    private     final       String login;
    private     String      password;
    private     int         age; */

public class UserMapper {
    public User convertDtoToUser(UserDTO userDto) {
        return new User(UserFields.builder()
                .uuid(userDto.uuid())
                .fullName(userDto.fullName())
                .login(userDto.login())
                .passwordHash(userDto.password())
                .age(55)
                .build());
    }
    
    public static String convertListUsersToString (List <User> users) {
        var sb = new StringBuilder();
        for(var i : users) {
            sb.append(i.toDBstring());
        }
        sb.append("\n");
        return sb.toString();
    }
}
