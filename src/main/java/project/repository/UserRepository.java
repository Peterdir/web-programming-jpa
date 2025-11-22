package project.repository;

import project.entity.User;

public interface UserRepository {
	User findByUsername(String username);

    void update(User user);
}
