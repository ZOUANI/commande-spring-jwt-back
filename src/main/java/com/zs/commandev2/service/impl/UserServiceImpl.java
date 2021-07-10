package com.zs.commandev2.service.impl;

import java.util.*;

import com.zs.commandev2.bean.Role;
import com.zs.commandev2.bean.User;
import com.zs.commandev2.bean.UserRole;
import com.zs.commandev2.dao.UserDao;
import com.zs.commandev2.service.facade.RoleService;
import com.zs.commandev2.service.facade.UserRoleService;
import com.zs.commandev2.service.facade.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public List<User> findAll() {
            return userDao.findAll();
    }



    @Override
    public User findByUsername(String username) {
        if (username == null)
            return null;
        return userDao.findByUsername(username);
    }

    @Override
    public User findByUsernameWithRoles(String username) {
        if (username == null)
            return null;
        User user= userDao.findByUsername(username);
        if(user!=null){
            List<UserRole> userRoles = userRoleService.findByUserUsername(username);
            user.setUserRoles(userRoles);
            if (userRoles != null) {
                user.setAuthorities(new ArrayList<>());
                userRoles.forEach(u->user.getAuthorities().add(u.getRole()));
            }
        }
        return user;
    }


    @Override
    @Transactional
    public int deleteByUsername(String username) {
        return userDao.deleteByUsername(username);
    }

    @Override
    public User findById(Long id) {
        if (id == null)
            return null;
        return userDao.getOne(id);
    }

    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User save(User user) {
        User foundedUserByUsername = findByUsername(user.getUsername());
        User foundedUserByEmail = userDao.findByEmail(user.getEmail());
        if (foundedUserByUsername != null || foundedUserByEmail != null)
            return null;
        else {
            if(user.getPassword()==null || user.getPassword().isEmpty()){
                user.setPassword((user.getUsername()));
            }
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            user.setPasswordChanged(false);
            user.setCreatedAt(new Date());

            User mySaved= userDao.save(user);
            if (user.getAuthorities() != null) {
                for (Role role : user.getAuthorities()) {
                    userRoleService.save(new UserRole(mySaved,role));
                }
            }
            return mySaved;
        }
    }

    @Override
    public User update(User user) {
        User foundedUser = findById(user.getId());
        if (foundedUser == null)
            return null;
        else {
            foundedUser.setEmail(user.getEmail());
            foundedUser.setUsername(user.getUsername());
            foundedUser.setEnabled(user.isEnabled());
            foundedUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
            foundedUser.setAccountNonLocked(user.isAccountNonLocked());
            foundedUser.setAccountNonExpired(user.isAccountNonExpired());
            foundedUser.setAuthorities(new ArrayList<>());
            return userDao.save(foundedUser);
        }


    }

    @Override
    @Transactional
    public int delete(User user) {

        if (user.getUsername() == null)
            return -1;

        User foundedUser = findByUsername(user.getUsername());
        if (foundedUser == null)
            return -1;
        userDao.delete(foundedUser);
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser=findByUsernameWithRoles(username);
        if(appUser==null) throw new UsernameNotFoundException("invalid user");
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        appUser.getAuthorities().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getAuthority()));
        });
        return appUser;
    }
}
