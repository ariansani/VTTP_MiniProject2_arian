package vttp.nusiss.arian.MiniProject2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.nusiss.arian.MiniProject2.exceptions.GymException;
import vttp.nusiss.arian.MiniProject2.model.Gym;
import vttp.nusiss.arian.MiniProject2.repository.GymRepository;

@Service
public class GymService {
    
    @Autowired
    private GymRepository gymRepo;


    public Gym findGymById(Integer id) throws GymException{
        
        Optional<Gym> optGym = gymRepo.findGymById(id);
        if(optGym.isEmpty())
        throw new GymException("Unable to find gym : %s".formatted(id));

        return optGym.get();
        
    }

    public List<Gym> selectAllGyms() throws GymException{

        Optional<List<Gym>> optListGym = gymRepo.selectAllGyms();
        
        if(optListGym.isEmpty()){
            throw new GymException("No Gyms Available");
        }
        return optListGym.get();
    }

}
