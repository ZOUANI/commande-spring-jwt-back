package com.zs.commandev2.service.impl;


import com.zs.commandev2.bean.Commande;
import com.zs.commandev2.dao.CommandeDao;
import com.zs.commandev2.service.facade.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {
    @Autowired
    private CommandeDao commandeDao;
    @Override
    public Commande findByReference(String reference) {
        return commandeDao.findByReference(reference);
    }

    @Override
    public Commande save(Commande commande) {
        if(findByReference(commande.getReference()) == null)
        commandeDao.save(commande);

        return commande;
    }

    @Override
    public Commande update(Commande commande) {
        return commandeDao.save(commande);
    }

    @Override
    public List<Commande> findAll() {
        return commandeDao.findAll();
    }

    @Override
    @Transactional
    public int deleteByReference(String reference) {
        return commandeDao.deleteByReference(reference);
    }
    @Override
    @Transactional
    public int deleteByReference(List<Commande> commandes) {
        int res=0;
        for (int i = 0; i < commandes.size(); i++) {
            res+=deleteByReference(commandes.get(i).getReference());
        }
        return res;
    }
}
