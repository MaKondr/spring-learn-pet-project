package com.example.finance_web_demo.controllers.rest;

import com.example.finance_web_demo.dto.RoleDTO;
import com.example.finance_web_demo.models.Role;
import com.example.finance_web_demo.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/role")
public class RestRoleController {

    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public RestRoleController(ModelMapper modelMapper, RoleService roleService) {
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<RoleDTO> getRoleById() {
        return roleService.getAllRoles().stream().map(this::convertRoleToRoleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Long id) {
        return convertRoleToRoleDTO(roleService.getRoleById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> addRole(@RequestBody RoleDTO roleDTO) {

        try {
            roleService.saveRole(convertRoleDTOToRole(roleDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private Role convertRoleDTOToRole(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, Role.class);
    }

    private RoleDTO convertRoleToRoleDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

}
