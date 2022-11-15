package vttp.nusiss.arian.MiniProject2.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User {
    
    public User() {
    }
  
    public User(String username, String email, String password) {
      this.username = username;
      this.email = email;
      this.password = password;
    }

    private Integer id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles = new HashSet<>();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public static User create(SqlRowSet rs) {
        User user = new User();

        user.id = rs.getInt("id");
        user.username = rs.getString("username");
        user.email=rs.getString("email");
        user.password = rs.getString("password");

        return user;
    }

    public Set<Role> getRoles() {
        return roles;
      }
    
      public void setRoles(Set<Role> roles) {
        this.roles = roles;
      }
    
}
