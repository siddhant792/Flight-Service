package services.servicesImpl;

import dao.Storage;
import models.User;
import services.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User addUser(String userId, String name, double funds) {
		User u = new User(userId, name, funds);
		Storage.addUser(userId, u);
		return u;
	}
}
