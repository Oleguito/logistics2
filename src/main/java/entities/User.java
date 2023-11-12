package entities;


import java.text.MessageFormat;

public class User {
    UserFields fields;
    
    public UserFields getFields() {
        return fields;
    }
    
    public User(UserFields fields) {
        this.fields = fields;
    }
    
    public String toDBstring () {
        return MessageFormat.format("{0};{1};{2};{3};{4}\n",
                fields.getUuid(),
                fields.getFullName(),
                fields.getLogin(),
                fields.getPasswordHash(),
                fields.getAge());
    }
}

