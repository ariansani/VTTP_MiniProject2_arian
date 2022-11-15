package vttp.nusiss.arian.MiniProject2.repository;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.nusiss.arian.MiniProject2.model.User;

@Repository
public class LoginRepository {
    
    @Autowired
    private JdbcTemplate template;


       public Optional<User> findUserByName(String username) {
        
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_FIND_EXISTING_USER, username);
        if (!rs.next())
            return Optional.empty();

        return Optional.of(User.create(rs));
    }
    

    public boolean findExistingUser(String username) {
        
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_FIND_EXISTING_USER, username);
        if (!rs.next())
            return false;

        return true;
    }

    public boolean findExistingEmail(String email) {
        
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_FIND_EXISTING_EMAIL, email);
        if (!rs.next())
            return false;

        return true;
    }

    public boolean insertUser(User user,String userString) {
        
        int count = template.update(Queries.SQL_INSERT_USER, user.getUsername(),user.getPassword(),user.getEmail(),userString);
      

        return 1 == count;
    }

   
}
