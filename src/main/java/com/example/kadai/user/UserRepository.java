package com.example.kadai.user;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Map<String, Object>> findAll() {
    // ユーザー一覧抽出クエリ
    String query = "select * from training_users  limit 10;";
    List<Map<String, Object>> users = jdbcTemplate.queryForList(query);
    return users;
  }

  public List<Map<String, Object>> findAll2(int i) {
    // ユーザー一覧抽出クエリ
    String query = "select * from training_users  limit 10 offset ?;";
    List<Map<String, Object>> users = jdbcTemplate.queryForList(query,i);
    return users;
  }
  
  public Integer countAll() {
    // ユーザー一覧抽出クエリ
    String query = "select count(id) from training_users;";
   Integer users = jdbcTemplate.queryForObject(query,Integer.class);
    return users;
  }

  // 新規ユーザ登録
  public boolean createUserRecord(User user) {
    String query = "insert into training_users(name,mail) values(?,?)";
    jdbcTemplate.update(query, user.getName(), user.getMail());
    return true;
  }

  // ユーザ編集
  public boolean updateUserRecord(User user) {
    String query = "update training_users set name=?,mail =? where id=?";
    jdbcTemplate.update(query, user.getName(), user.getMail(), user.getId());
    return true;
  }

  // ユーザ削除
  public boolean deleteUserRecord(User user) {
    String query = "delete from training_users where id=?";
    jdbcTemplate.update(query, user.getId());
    return true;
  }
}
