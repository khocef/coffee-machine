package fr.univnantes.alma.coffee.backend.facade;

import java.util.concurrent.TimeUnit;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.univnantes.alma.coffee.backend.entity.Account;

/**
 * Session Bean implementation class AccountMBean
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
@Remote
public class AccountFacade extends AbstractFacade<Account> {

	@PersistenceContext(name = "CoffeeMachinePU")
	protected EntityManager em;

	/**
	 * Default constructor.
	 */
	public AccountFacade() {
		super(Account.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
