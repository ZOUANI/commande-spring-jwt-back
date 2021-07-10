package com.zs.commandev2.service.facade;


import com.zs.commandev2.bean.Role;

import java.util.List;

public interface RoleService {

        /**

     * find all Role in database 
     * @return List<Role> , If database is empty return  null.
     */
	List<Role> findAll();
	   
	/**

     * find Role from database by authority (reference)
     * @param authority - reference of Role 
     * @return the founded Role , If no Role were
     *         found in database return  null.
     */
	Role findByAuthority(String authority);

    /**
     * find Role from database by id (id)
     * @param id - id of Role 
     * @return the founded  Role , If no Role were
     *         found in database return  null.
     */
	Role findById(Long id);
     
     /**
     * delete Role from database
     * @param id - id of Role to be deleted
     * 
     */
	void deleteById(Long id);


			 
    /**
     * create Role in database
     * @param role - Role to be created 
     * @return the created Role, If the Role can't be created return null.
     */
	Role save(Role role);

	/**
     * create list Role in database
     * @param roles - list of Role to be created 
     * @return the created Role list
     */
	List<Role> create(List<Role> roles);
    
     /**
     * update Role in database
     * @param role - Role to be updated
     * @return the updated Role, If the Role can't be updated return null.
     */
    Role update(Role role);
    
       /**
     * delete Role from database
     * @param role - Role to be deleted
     * @return 1 if Role deleted successfully, If the Role can't be deleted return negative int
     */
	int delete(Role role);


    
        /**
     * delete Role from database by authority (reference)
     * 
     * @param authority - reference of Role to be deleted
     * @return 1 if Role deleted successfully
     */
	int deleteByAuthority(String authority);

	/**
	 * create list Role in database
	 * @param username - list of Role to be created
	 * @return the created Role list
	 */
	List<Role> findByUserName(String username);


}
