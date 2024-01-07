package user;

import java.util.UUID;

public class UserService implements UserDao {

    private static final UserModel[] users;

    static {
        users = new UserModel[]{
                new UserModel(UUID.fromString("40738cd0-9dfc-11ee-8c90-0242ac120002"), "Jackson"),
                new UserModel(UUID.fromString("e38f55c3-bc64-4337-9fee-1a0435d2baf4"), "Mercury"),
                new UserModel(UUID.fromString("1c83a154-1fc3-4f45-b129-df913b0907b6"), "Pluto"),
                new UserModel(UUID.fromString("3c314e7a-c4dc-4706-bacb-4c33f4a22bd7"), "Atlas")

        };
    }

    @Override
    public UserModel[] getUsers() {
        return users;
    }

    @Override
    public UserModel getUserById(UUID id) {
        for (UserModel user : getUsers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
