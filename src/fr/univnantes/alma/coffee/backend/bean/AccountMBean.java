package fr.univnantes.alma.coffee.backend.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.jboss.resteasy.spi.HttpRequest;

import fr.univnantes.alma.coffee.backend.entity.Account;
import fr.univnantes.alma.coffee.backend.facade.AccountFacade;

@ManagedBean
@SessionScoped
public class AccountMBean {

	private final static Logger LOGGER = Logger.getLogger(AccountFacade.class
			.getName());

	@EJB
	private AccountFacade facade;

	public String detail() {
		testCreate();
		LOGGER.log(Level.FINE, "Redirect to test");
		return "test?faces-redirect=true";
	}

	public Account addOne(int uid, Double amount) {
		// HttpRequest request = (HttpRequest) FacesContext.getCurrentInstance()
		// .getExternalContext().getRequest();
		Account account = new Account(uid, amount);
		this.facade.create(account);
		return account;
	}
	
	public void testCreate(){
		this.addOne(999, 20.0);
		this.addOne(888, 9.0);
	}

	public void get() {
		
		int x = 1;
		List<Account> accounts = facade.findAll();
		if (accounts.size() == 0)
			LOGGER.log(Level.WARNING, "###############Nothing############");
		else
			for (Account a : accounts)
				LOGGER.log(Level.INFO, "Server responded with : {0}.", a);
	}

}
