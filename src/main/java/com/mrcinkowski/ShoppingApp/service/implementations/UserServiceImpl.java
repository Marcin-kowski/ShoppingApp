package com.mrcinkowski.ShoppingApp.service.implementations;

import com.mrcinkowski.ShoppingApp.api.dto.LoginBody;
import com.mrcinkowski.ShoppingApp.api.dto.RegistrationBody;
import com.mrcinkowski.ShoppingApp.exception.UserAlreadyExistsException;
import com.mrcinkowski.ShoppingApp.repository.LocalUserRepository;
import com.mrcinkowski.ShoppingApp.repository.RoleRepository;
import com.mrcinkowski.ShoppingApp.repository.entities.LocalUserEntity;
import com.mrcinkowski.ShoppingApp.repository.entities.RoleEntity;
import com.mrcinkowski.ShoppingApp.service.EncryptionService;
import com.mrcinkowski.ShoppingApp.service.JWTService;
import com.mrcinkowski.ShoppingApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final LocalUserRepository localUserRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private final RoleRepository roleRepository;

    public UserServiceImpl(LocalUserRepository localUserRepository, EncryptionService encryptionService, JWTService jwtService, RoleRepository roleRepository) {
        this.localUserRepository = localUserRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
    }


    public LocalUserEntity registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (localUserRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
            || localUserRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        LocalUserEntity user = new LocalUserEntity();
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setUsername(registrationBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        Optional<RoleEntity> userRole = roleRepository.findByName("ROLE_USER");
        user.setRoleEntities(List.of(userRole.orElseThrow()));
        return localUserRepository.save(user);
    }

    public String loginUser(LoginBody loginBody) {
        Optional<LocalUserEntity> opUser = localUserRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
            LocalUserEntity user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

}
