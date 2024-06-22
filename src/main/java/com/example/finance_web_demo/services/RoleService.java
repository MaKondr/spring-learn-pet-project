package com.example.finance_web_demo.services;

import com.example.finance_web_demo.models.Role;
import com.example.finance_web_demo.repository.RoleRepository;
import com.example.finance_web_demo.util.user.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return roleRepository.getReferenceById(id);
    }

    public void saveRole(Role role) {
        role.setName(role.getName());
        roleRepository.save(role);
    }

    public void saveRole(Role role, long id) {
        Optional<Role> oldRole = roleRepository.findById(id);
        if (oldRole.isEmpty()) {
            throw new RoleNotFoundException("Role with id=" + id + "not found");
        }
        role.setId(id);
        roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

}
