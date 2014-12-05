package fr.univnantes.alma.coffee.backend.facade;

import javax.ejb.Remote;

import fr.univnantes.alma.coffee.backend.entity.Account;

@Remote
public interface AccountFacadeRemote {

	public String ehlo();

	public String callBack(String token);

	public Account buycoffee();

	public Account addBalance(Double balance);

}
