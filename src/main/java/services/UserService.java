package services;

import models.User;

public interface UserService {
	User addUser(String userId, String name, double funds);
}
