package com.example.kadai.user;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> getUsers() {
		// ユーザー一覧を取得
		List<Map<String, Object>> querySet = repo.findAll();
		List<User> users = new ArrayList<>();
		for (var record : querySet) {
			var user = new User();
			user.setId((Integer) (record.get("id")));
			user.setName((String) record.get("name"));
			user.setMail((String) record.get("mail"));
			users.add(user);
		}
		return users;
	}

	public List<User> getUsers(int test) {
		int i = 10 * (test-1);
		List<Map<String, Object>> querySet = repo.findAll2(i);
		List<User> users = new ArrayList<>();
		for (var record : querySet) {
			var user = new User();
			user.setId((Integer) (record.get("id")));
			user.setName((String) record.get("name"));
			user.setMail((String) record.get("mail"));
			users.add(user);
		}
		return users;

	}

	// 新規ユーザ登録
	public boolean createUser(String userName, String userMail) {

		var user = new User();
		user.setName(userName);
		user.setMail(userMail);

		repo.createUserRecord(user);
		return true;
	}

	// ユーザ編集
	public boolean updateUser(int userId, String userName, String userMail) {

		var user = new User();
		user.setId(userId);
		user.setName(userName);
		user.setMail(userMail);

		repo.updateUserRecord(user);
		return true;
	}

	// ユーザ削除
	public boolean deleteUser(int userId) {

		var user = new User();
		user.setId(userId);

		repo.deleteUserRecord(user);
		return true;
	}

}