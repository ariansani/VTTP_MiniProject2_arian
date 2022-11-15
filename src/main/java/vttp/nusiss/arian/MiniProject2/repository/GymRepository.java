package vttp.nusiss.arian.MiniProject2.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.nusiss.arian.MiniProject2.model.Gym;

@Repository
public class GymRepository {
    
    @Autowired
    private JdbcTemplate template;
    

    public Optional<List<Gym>> selectAllGyms(){
        List<Gym> gyms = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_ALL_GYMS);

        while(rs.next()){
            Gym gym = new Gym();
            gym.setId(rs.getInt("id"));
            gym.setName(rs.getString("name"));
            gym.setLocation(rs.getString("location"));
            gym.setRouteReset(rs.getDate("route_reset"));
            gym.setLatitude(rs.getString("latitude"));
            gym.setLongitude(rs.getString("longitude"));
            gyms.add(gym);
        }

        if (gyms.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(gyms);
    }

    public Optional<Gym> findGymById(Integer id) {
         
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_FIND_GYM_BY_ID, id);
        if (!rs.next())
            return Optional.empty();

        return Optional.of(Gym.create(rs));
    }

    


}
