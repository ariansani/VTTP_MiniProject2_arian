package vttp.nusiss.arian.MiniProject2.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.nusiss.arian.MiniProject2.model.SubPackage;

@Repository
public class PackageRepository {
    
    @Autowired
    private JdbcTemplate template;
    
    public Optional<List<SubPackage>> findPackagesByUserId(Integer id){

        List<SubPackage> packages = new LinkedList<>();

        
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_PACKAGES_BY_USERID, id);

        while (rs.next()) {
            SubPackage subPackage = new SubPackage();
            subPackage.setPackageUUID(rs.getString("uuid"));
            subPackage.setGymId(rs.getInt("gym_id"));
            subPackage.setGymName(rs.getString("name"));
            subPackage.setUserId(rs.getInt("user_id"));

            subPackage.setEntryPasses(rs.getInt("entry_passes"));
            subPackage.setExpiryDate(rs.getDate("expiry_date"));

            subPackage.setExpired(rs.getBoolean("expired"));
            packages.add(subPackage);
        }

        if (packages.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(packages);
    }

    public Optional<SubPackage> findPackageByUUID(String uuid){

        SubPackage subPackage = new SubPackage();

        
        final SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_SUBPACKAGE, uuid);

        if (rs.next()) {
            subPackage.setPackageUUID(rs.getString("uuid"));
            subPackage.setGymId(rs.getInt("gym_id"));
            subPackage.setGymName(rs.getString("name"));
            subPackage.setUserId(rs.getInt("user_id"));

            subPackage.setEntryPasses(rs.getInt("entry_passes"));
            subPackage.setExpiryDate(rs.getDate("expiry_date"));

            subPackage.setExpired(rs.getBoolean("expired"));
            return Optional.of(subPackage);
        }else{
            return Optional.empty();
        }

    }

    public boolean usePackage(String uuid){
        int count = template.update(Queries.SQL_USE_PACKAGE, uuid);

        return 1 == count;
    }

    public boolean addSubPackage(SubPackage subPackage){
        int count = template.update(Queries.SQL_INSERT_SUBPACKAGE, subPackage.getPackageUUID(),subPackage.getGymId(), subPackage.getUserId(), subPackage.getEntryPasses(), subPackage.getExpiryDate(), subPackage.isExpired());

        return 1 == count;
    }

    public boolean editSubPackage(SubPackage subPackage){
        int count = template.update(Queries.SQL_UPDATE_SUBPACKAGE, subPackage.getEntryPasses(), subPackage.getExpiryDate(),subPackage.getPackageUUID());

        return 1 == count;
    }

    public boolean deletePackage(String uuid) {
        int count = template.update(Queries.SQL_DELETE_PACKAGE, uuid);

        return 1 == count;
    }



}
