package com.zs.commandev2.service.impl;

import com.zs.commandev2.bean.Client;
import com.zs.commandev2.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl {

    @Autowired
    private ClientDao clientDao;

    public Client findByCin(String cin) {
        return clientDao.findByCin(cin);
    }

    public Client findByLogin(String login) {
        return clientDao.findByLogin(login);
    }

    public List<Client> findAll() {
        return clientDao.findAll();
    }

    public Client save(Client cl) {
        return clientDao.save(cl);
    }

    public int seConnecter(String login,String password){
        Client client = clientDao.findByLogin(login);
        if (client == null) {
            return -1;
        }else if(!client.getPassword().equals(password)){
            return -2;
        }else{
            return  1;
        }
    }
}
