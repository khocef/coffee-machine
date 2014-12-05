package fr.univnantes.alma.coffee.backend.facade;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import fr.univnantes.alma.coffee.backend.entity.Account;

/**
 * Session Bean implementation class AccountMBean
 */
@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
@Remote
public class AccountFacade {

	@PersistenceContext
	protected EntityManager em;

	protected String uid;

	/**
	 * Default constructor.
	 */
	public AccountFacade() {
	}

	protected EntityManager getEntityManager() {
		return this.em;
	}

	public String ehlo() {
		if (this.uid == null)
			return "unauthorized";
		else
			return "ok";
	}

	public String callBack(String token) {
		// appel au service de gestion d'identité
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost request = new HttpPost(
				"http://localhost:8080/ame-services-rest-0.1-SNAPSHOT/service/sample/sayHello");

		request.setHeader("accept", "application/json");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("code", token));
		UrlEncodedFormEntity encodedFormEntity = null;
		try {
			encodedFormEntity = new UrlEncodedFormEntity(
					params, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setEntity(encodedFormEntity);
		try {
			CloseableHttpResponse response = httpClient.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Account findAccount() {
		return em.find(Account.class, uid);
	}

	public Account buycoffee() {
		Account account = this.findAccount();
		account.setAmount(account.getAmount() - 0.40);
		em.persist(account);
		return account;
	}

	public Account addBalance(Double balance) {
		Account account = this.findAccount();
		account.setAmount(account.getAmount() + balance);
		em.persist(account);
		return account;
	}

}
