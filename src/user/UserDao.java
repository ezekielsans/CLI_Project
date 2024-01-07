package user;

import java.util.UUID;

public interface UserDao {

    UserModel[] getUsers();

    UserModel getUserById(UUID id);

}

