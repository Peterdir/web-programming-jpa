package project.service.impl;

import project.entity.User;
import project.model.UserModel;
import project.repository.UserRepository;
import project.repository.impl.UserRepositoryImpl;
import project.service.UserService;

public class UserServiceImpl implements UserService {
	private UserRepository userRepo = new UserRepositoryImpl();

	@Override
	public UserModel findByUsername(String username) {
		User entity = userRepo.findByUsername(username);
		if (entity == null)
			return null;

		UserModel model = new UserModel();
		model.setFullname(entity.getFullname());
		model.setFullname(entity.getFullname());
		model.setPhone(entity.getPhone());
		model.setEmail(entity.getEmail());
		model.setImages(entity.getImages());
		model.setRole(entity.getRole());
		model.setActive(entity.isActive());

		return model;
	}

	@Override
	public void update(UserModel model) {

		User entity = userRepo.findByUsername(model.getFullname());
		if (entity == null)
			return;

		entity.setFullname(model.getFullname());
		entity.setPhone(model.getPhone());
		entity.setImages(model.getImages());
		entity.setEmail(model.getEmail());
		entity.setActive(model.isActive());
		entity.setRole(model.getRole());

		userRepo.update(entity);
	}

	@Override
	public UserModel login(String userName, String password) {

	    // Lấy entity từ DB
	    User entity = userRepo.findByUsername(userName);

	    // Không tồn tại user → trả null
	    if (entity == null)
	        return null;

	    // Mật khẩu sai → trả null
	    if (!entity.getPassword().equals(password))
	        return null;

	    // Mapping entity → model
	    UserModel model = new UserModel();
	    model.setId(entity.getId());
	    model.setFullname(entity.getFullname());
	    model.setEmail(entity.getEmail());
	    model.setPhone(entity.getPhone());
	    model.setImages(entity.getImages());
	    model.setRole(entity.getRole());
	    model.setActive(entity.isActive());

	    return model;
	}
}
