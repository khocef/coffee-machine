/**
 * 
 */
package fr.univnantes.alma.coffee.backend.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.univnantes.alma.coffee.backend.entity.Account;

/**
 * @author khocef
 *
 */

@Stateless
public class AccountFacade {

	@PersistenceContext(name="CoffeeMachinePU")
	protected EntityManager em;
	
	public Account findOne(Object id){
		return em.find(Account.class, id);
	}
	
	public List<Account> findAll(){
		return em.createQuery("").getResultList();
	}
}
