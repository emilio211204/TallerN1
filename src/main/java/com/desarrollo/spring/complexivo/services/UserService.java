package com.desarrollo.spring.complexivo.services;

import com.desarrollo.spring.complexivo.models.Role;
import com.desarrollo.spring.complexivo.models.User;
import com.desarrollo.spring.complexivo.repository.RoleRepository;
import com.desarrollo.spring.complexivo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    RoleRepository roleRepository;


    //Método para guardar usuario
    public User save(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(()->{
                    Role newRole = new Role();
                    newRole.setName("ROLE_ADMIN");
                    return roleRepository.save(newRole);
                });
        user.getRoles().add(userRole);
        return this.repository.save(user);
    }
}
