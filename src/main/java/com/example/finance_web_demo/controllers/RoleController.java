package com.example.finance_web_demo.controllers;

import com.example.finance_web_demo.models.Role;
import com.example.finance_web_demo.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping
    public String listRole(Model model) {
        model.addAttribute("roles",roleService.getAllRoles());
        return "role/roles";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createRole(@ModelAttribute("role") Role role) {
        return "role/create-role";
    }

    @PostMapping("/create")
    public String createRole(@ModelAttribute @Valid Role role, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "role/create-role";
        }

        roleService.saveRole(role);

        return "redirect:/role";
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable long id) {
        roleService.deleteRole(id);
        return "redirect:/role";
    }

    @GetMapping("update/{id}")
    public String updateRole(@PathVariable long id, Model model) {
        model.addAttribute("role",roleService.getRoleById(id));
        return "role/role-modified";
    }

    @PatchMapping("update/{id}")
    public String updateRole(@PathVariable long id, @ModelAttribute @Valid Role role,
                             Model model,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "role/role-modified";
        }
        roleService.saveRole(role,id);

        return "redirect:/role";
    }


}
