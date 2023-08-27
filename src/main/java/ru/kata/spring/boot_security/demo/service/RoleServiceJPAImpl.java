package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Roles;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceJPAImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceJPAImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Roles> getRoles() {
        return roleRepository.findAll();
    }
}
