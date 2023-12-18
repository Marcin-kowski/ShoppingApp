package com.mrcinkowski.ShoppingApp.service;

import com.mrcinkowski.ShoppingApp.model.LocalUser;
import com.mrcinkowski.ShoppingApp.model.Privilege;
import com.mrcinkowski.ShoppingApp.model.Role;
import com.mrcinkowski.ShoppingApp.model.repository.LocalUserRepository;
import com.mrcinkowski.ShoppingApp.model.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;



@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final LocalUserRepository localUserRepository;

    public MyUserDetailsService(LocalUserRepository localUserRepository) {
        this.localUserRepository = localUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<LocalUser> userOp = localUserRepository.findByUsernameIgnoreCase(username);
        LocalUser user = userOp.orElseThrow();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true, true, true,
                true, getAuthorities(user.getRoles()));
    }

    private List<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
            System.out.println(role.getName());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}

