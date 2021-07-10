package com.zs.commandev2.dao;

import com.zs.commandev2.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;




@Repository
public interface RoleDao extends JpaRepository<Role,Long> {

	   Role findByAuthority(String authority);
       int deleteByAuthority(String authority);


}
