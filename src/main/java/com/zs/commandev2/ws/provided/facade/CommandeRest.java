package com.zs.commandev2.ws.provided.facade;


import com.zs.commandev2.bean.Commande;
import com.zs.commandev2.service.facade.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

@RestController
@RequestMapping("ecom-api/commande")
public class CommandeRest {

    @Autowired
    private CommandeService commandeService;


   @GetMapping("/reference/{reference}")
    public Commande findByReference(@PathVariable String reference) {
        return commandeService.findByReference(reference);
    }
    @DeleteMapping("/reference/{reference}")
    public int deleteByReference(@PathVariable String reference) {
        return commandeService.deleteByReference(reference);
    }
    @PostMapping("/delete-multiple-by-reference")
    public int deleteByReference(@RequestBody List<Commande> commandes) {
        return commandeService.deleteByReference(commandes);
    }
    @PostMapping("/")
    public Commande save(@RequestBody Commande commande) {
        return commandeService.save(commande);
    }

    @PutMapping("/")
    public Commande update(@RequestBody Commande commande) {
        return commandeService.update(commande);
    }

    @GetMapping("/")
    public List<Commande> findAll() {
        return commandeService.findAll();
    }


}
