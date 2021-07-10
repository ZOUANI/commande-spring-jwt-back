package com.zs.commandev2.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.zs.commandev2.bean.Role;
import com.zs.commandev2.bean.UserRole;
import com.zs.commandev2.dao.RoleDao;
import com.zs.commandev2.service.facade.RoleService;
import com.zs.commandev2.service.facade.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;



@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findByAuthority(String authority) {
        if (authority == null)
            return null;
        return roleDao.findByAuthority(authority);
    }


    @Override
    @Transactional
    public int deleteByAuthority(String authority) {
        return roleDao.deleteByAuthority(authority);
    }

    @Override
    public List<Role> findByUserName(String username) {
        List<UserRole> byUserUsername = userRoleService.findByUserUsername(username);
        List<Role> res= new ArrayList<>();
        if(byUserUsername!=null){
            byUserUsername.forEach(a->res.add(a.getRole()));
        }
        return res;
    }

    @Override
    public Role findById(Long id) {
        if (id == null)
            return null;
        return roleDao.getOne(id);
    }

    @Transactional
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }

    @Override
    public Role save(Role role) {
        Role foundedRole = findByAuthority(role.getAuthority());
        if (foundedRole != null)
            return null;

        Role savedRole = roleDao.save(role);
        return savedRole;
    }

    @Override
    public List<Role> create(List<Role> roles) {
        List<Role>  resultat = new ArrayList<>();
        roles.forEach(r->resultat.add(save(r)));
        return resultat;
    }

    @Override
    public Role update(Role role) {
        Role foundedRole = findById(role.getId());
        if (foundedRole == null)
            return null;
        return roleDao.save(role);

    }

    @Override
    @Transactional
    public int delete(Role role) {

        if (role.getAuthority() == null)
            return -1;

        Role foundedRole = findByAuthority(role.getAuthority());
        if (foundedRole == null)
            return -1;
        roleDao.delete(foundedRole);
        return 1;
    }



}
