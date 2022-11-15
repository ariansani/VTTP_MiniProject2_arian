package vttp.nusiss.arian.MiniProject2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import vttp.nusiss.arian.MiniProject2.exceptions.PackageException;
import vttp.nusiss.arian.MiniProject2.model.SubPackage;
import vttp.nusiss.arian.MiniProject2.repository.PackageRepository;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class PackageService {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PackageRepository packageRepo;

    public List<SubPackage> findPackagesByUserId(Integer id)throws PackageException{

        Optional<List<SubPackage>> optListSubPackage = packageRepo.findPackagesByUserId(id);
        
        if(optListSubPackage.isEmpty()){
            throw new PackageException("No Subscriptions currently");
        }
        return optListSubPackage.get();
    }

    public SubPackage findPackageByUUID(String uuid)throws PackageException{

        Optional<SubPackage> optSubPackage = packageRepo.findPackageByUUID(uuid);
        
        if(optSubPackage.isEmpty()){
            throw new PackageException("Unable to find this package");
        }
        return optSubPackage.get();
    }



    public boolean addSubPackage(SubPackage subPackage) throws PackageException{
        
        boolean add = packageRepo.addSubPackage(subPackage);
        if(!add)
        throw new PackageException("Cannot add this package. Please check with admin");

        return add;
    }

    public boolean editSubPackage(SubPackage subPackage) throws PackageException{

        boolean edit = packageRepo.editSubPackage(subPackage);
        if(!edit)
        throw new PackageException("Cannot update this package. Please check with admin");

        return edit;
    }

    public boolean usePackage(String uuid) throws PackageException{


        boolean consume = packageRepo.usePackage(uuid);
        if(!consume)
        throw new PackageException("Cannot consume this package. Please check with admin");

        SubPackage subPackage = packageRepo.findPackageByUUID(uuid).get();
        JsonObject pkgJson = SubPackage.usePackageToJson(subPackage);

        Document toInsert = Document.parse(pkgJson.toString());

        mongoTemplate.insert(toInsert, "packagesHistory");
        
       
        return consume;
    }

    public boolean deletePackageByUUID(String uuid) throws PackageException{

        boolean deleted = packageRepo.deletePackage(uuid);
        if(!deleted)
        throw new PackageException("Cannot consume this package. Please check with admin");

        return deleted;
    }


}
