package project.service;

import project.entity.User;
import project.model.UserModel;

public interface UserService {
	UserModel findByUsername(String username);

    void update(UserModel model);
    UserModel login(String userName, String password);
}
