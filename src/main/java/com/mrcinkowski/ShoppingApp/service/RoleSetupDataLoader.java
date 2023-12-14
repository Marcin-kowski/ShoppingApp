package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.model.LocalUser;
import com.mrcinkowski.ShoppingApp.model.Privilege;
import com.mrcinkowski.ShoppingApp.model.Role;
import com.mrcinkowski.ShoppingApp.model.repository.LocalUserRepository;
import com.mrcinkowski.ShoppingApp.model.repository.PrivilegeRepository;
import com.mrcinkowski.ShoppingApp.model.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class RoleSetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final LocalUserRepository localUserRepository;
    private final EncryptionService encryptionService;

    public RoleSetupDataLoader(RoleRepository roleRepository,
                               PrivilegeRepository privilegeRepository,
                               LocalUserRepository localUserRepository,
                               EncryptionService encryptionService) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.localUserRepository = localUserRepository;
        this.encryptionService = encryptionService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege deletePrivilege
                = createPrivilegeIfNotFound("DELETE_PRIVILEGE");
        Privilege updatePrivilege
                = createPrivilegeIfNotFound("UPDATE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege, updatePrivilege, deletePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));

        Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");

        LocalUser localUser = new LocalUser();
        localUser.setUsername("admin");
        localUser.setPassword(encryptionService.encryptPassword("password"));
        localUser.setEmail("admin@test.com");
        localUser.setFirstName("John");
        localUser.setLastName("Doe");
        localUser.setRoles(List.of(adminRole.orElseThrow()));
        localUserRepository.save(localUser);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Optional<Privilege> privilegeOptional = privilegeRepository.findByName(name);
        if (privilegeOptional.isEmpty()) {
            Privilege privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
            return privilege;
        }

        return privilegeOptional.get();
    }

    @Transactional
    Role createRoleIfNotFound(String name, List<Privilege> privileges) {

        Optional<Role> roleOptional = roleRepository.findByName(name);
        if (roleOptional.isEmpty()) {
            Role role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
            return role;
        }
        return roleOptional.get();
    }
}
