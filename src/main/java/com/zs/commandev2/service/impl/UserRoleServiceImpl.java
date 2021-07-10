package com.zs.commandev2.service.impl;

import com.zs.commandev2.bean.UserRole;
import com.zs.commandev2.dao.UserRoleDao;
import com.zs.commandev2.service.facade.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Override
    public List<UserRole> findByUserUsername(String username) {
        return userRoleDao.findByUserUsername(username);
    }

    @Override
    public int deleteByUserUsername(String username) {
        return userRoleDao.deleteByUserUsername(username);
    }

    public UserRole save(UserRole s) {
        return userRoleDao.save(s);
    }

    @Autowired
    private UserRoleDao userRoleDao;

}
