package com.zs.commandev2.service.facade;

import com.zs.commandev2.bean.Commande;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface CommandeService {
    Commande findByReference(String reference);

    Commande save(Commande commande);

    Commande update(Commande commande);

    List<Commande> findAll();

    int deleteByReference(String reference);

    int deleteByReference(List<Commande> commandes);
}
