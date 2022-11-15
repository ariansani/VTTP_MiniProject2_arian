package vttp.nusiss.arian.MiniProject2.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.nusiss.arian.MiniProject2.model.User;
import vttp.nusiss.arian.MiniProject2.repository.LoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
  @Autowired
  LoginRepository loginRepo;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = loginRepo.findUserByName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

}