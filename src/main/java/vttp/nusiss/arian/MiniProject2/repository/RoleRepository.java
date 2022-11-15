package vttp.nusiss.arian.MiniProject2.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.nusiss.arian.MiniProject2.model.Role;

@Repository
public class RoleRepository {

    @Autowired
    private JdbcTemplate template;

    public Optional<Role> findByName(String name) {
        
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_FIND_ROLE_NAME, name);
        if (!rs.next())
            return Optional.empty();

        return Optional.of(Role.create(rs));
    }

}
