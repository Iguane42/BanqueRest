/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import dal.Compte;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import outils.Utilitaire;

/**
 *
 * @author Epulapp
 */
@Stateless
@LocalBean
public class CompteFacade {

    @PersistenceContext(unitName = "Banque-ejbPU")
    private EntityManager em;
    protected EntityManager getEntityManager(){
        return this.em;
    }
    
    public Compte lireCompte(int id){
        try{
            return em.find(Compte.class, id);
        } catch (Exception e){
            System.err.println(Utilitaire.getExceptionCause(e));
            throw e;
        }
    }
}
