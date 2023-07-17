package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Roles;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByRole(String role);
}