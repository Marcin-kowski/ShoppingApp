package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.repository.LocalUserRepository;
import com.mrcinkowski.ShoppingApp.repository.PrivilegeRepository;
import com.mrcinkowski.ShoppingApp.repository.RoleRepository;
import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.PrivilegeEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.RoleEntity;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class RoleSetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = true;
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
        PrivilegeEntity readPrivilegeEntity
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        PrivilegeEntity writePrivilegeEntity
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        PrivilegeEntity deletePrivilegeEntity
                = createPrivilegeIfNotFound("DELETE_PRIVILEGE");
        PrivilegeEntity updatePrivilegeEntity
                = createPrivilegeIfNotFound("UPDATE_PRIVILEGE");

        List<PrivilegeEntity> adminPrivilegeEntities = Arrays.asList(readPrivilegeEntity, writePrivilegeEntity, updatePrivilegeEntity, deletePrivilegeEntity);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivilegeEntities);
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilegeEntity));


        Optional<RoleEntity> adminRole = roleRepository.findByName("ROLE_ADMIN");
        LocalUserEntity localUserEntity = new LocalUserEntity();
        localUserEntity.setUsername("admin");
        localUserEntity.setPassword(encryptionService.encryptPassword("password"));
        localUserEntity.setEmail("admin@test.com");
        localUserEntity.setFirstName("John");
        localUserEntity.setLastName("Doe");
        localUserEntity.setRoleEntities(List.of(adminRole.orElseThrow()));
        localUserRepository.save(localUserEntity);

        alreadySetup = true;
    }

    @Transactional
    PrivilegeEntity createPrivilegeIfNotFound(String name) {

        Optional<PrivilegeEntity> privilegeOptional = privilegeRepository.findByName(name);
        if (privilegeOptional.isEmpty()) {
            PrivilegeEntity privilegeEntity = new PrivilegeEntity();
            privilegeEntity.setName(name);
            privilegeRepository.save(privilegeEntity);
            return privilegeEntity;
        }

        return privilegeOptional.get();
    }

    @Transactional
    RoleEntity createRoleIfNotFound(String name, List<PrivilegeEntity> privilegeEntities) {

        Optional<RoleEntity> roleOptional = roleRepository.findByName(name);
        if (roleOptional.isEmpty()) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(name);
            roleEntity.setPrivilegeEntities(privilegeEntities);
            roleRepository.save(roleEntity);
            return roleEntity;
        }
        return roleOptional.get();
    }
}
