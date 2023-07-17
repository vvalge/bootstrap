package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Roles;
import ru.kata.spring.boot_security.demo.model.Users;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceJPAImpl implements UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserServiceJPAImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users myUser = userRepository.findByEmail(s);
        if (myUser!=null) {
            return new User(myUser.getEmail(), myUser.getPassword(), myUser.getRoles());
        }

        throw new UsernameNotFoundException("User Not Found");
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users findById(Long id){
        return userRepository.getById(id);
    }

    @Override
    public List<Users> index() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(Users users) {
        Users checkUser = userRepository.findByEmail(users.getEmail());
        if(checkUser==null) {
            Roles roles = roleRepository.findByRole("ROLE_USER");
            if (roles != null) {
                ArrayList<Roles> roles1 = new ArrayList<>();
                roles1.add(roles);
                users.setRoles(roles1);
                users.setPassword(passwordEncoder.encode(users.getPassword()));
                //users.setPassword(users.getPassword());
            }
        }
        userRepository.save(users);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
