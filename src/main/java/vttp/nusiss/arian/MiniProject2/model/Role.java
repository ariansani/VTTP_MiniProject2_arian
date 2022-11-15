package vttp.nusiss.arian.MiniProject2.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Role {
    
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public static Role create(SqlRowSet rs) {
        Role role = new Role();

        role.id = rs.getInt("id");
        role.name = rs.getString("name");

        return role;
    }
}
