package com.capgemini.bankapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.exceprtion.BankAccountNotFoundException;
import com.capgemini.bankapp.model.BankAccount;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public boolean addNewBankAccount(BankAccount account) {
		String query = "INSERT INTO bankaccounts (customer_name,account_type,account_balance) VALUES(?,?,?)";
		int result=jdbcTemplate.update(query,new Object[] {account.getAccountHolderName(),account.getAccountType(),account.getAccountBalance()});
		
			if (result == 1){
				return true;
			}
			return false;
	}

	public boolean updateAccountDetails(long accountId, String accountHolderName, String accountType) {
		String query= "UPDATE bankaccounts SET customer_name=? ,account_type=? WHERE account_id=?";
		int result=jdbcTemplate.update(query,new Object[] {accountHolderName,accountType,accountId});		
		if(result==1){
				return true;
		}
		return false;
	}

	public boolean deleteBankAccount(long accountId) {
		String query = "DELETE FROM bankaccounts WHERE account_id=?";
		int result;
		
		result = jdbcTemplate.update(query,new Object[] {accountId});
			if (result == 1)
				return true;
 
		return false;
	}

	public List<BankAccount> findAllBankAccounts() {
		String query = "SELECT * FROM bankaccounts";
		List<BankAccount> accounts= jdbcTemplate.query(query,(rs,rowNum)->{
				long id= rs.getLong(1);
				String name=rs.getString(2);
				String type= rs.getString(3);
				double balance= rs.getDouble(4);
				BankAccount bankAcc= new BankAccount(id,name,type,balance);
				return bankAcc;
			});
		
		return accounts;
	}

	public BankAccount searchAccount(long accountId) throws BankAccountNotFoundException  {
		BankAccount account=null;
		try{
			String query = "SELECT * FROM bankaccounts WHERE account_id="+accountId ;
			 account=jdbcTemplate.queryForObject(query,(rs,rowNum)->{
				long id= rs.getLong(1);
				String name=rs.getString(2);
				String type= rs.getString(3);
				double balance= rs.getDouble(4);
				BankAccount bankAcc= new BankAccount(id,name,type,balance);
				return bankAcc;
			});
		}
		catch(EmptyResultDataAccessException ex){
			BankAccountNotFoundException  re= new BankAccountNotFoundException("Bank Account not Found");
			ex.initCause(re);
			throw re;

		}
	
		return account;
	}


	public double getBalance(long accountId) throws BankAccountNotFoundException {
		String query = "SELECT account_balance FROM bankaccounts WHERE account_id=?";
		double balance = -1;
		try  {
				balance = jdbcTemplate.queryForObject(query,new Object[] {accountId},(rs,rowNum)->{
					double accountBalance= rs.getDouble(1);
					return accountBalance;
				});
		}catch(EmptyResultDataAccessException ex){
			BankAccountNotFoundException  re= new BankAccountNotFoundException("Bank Account not Found");
			ex.initCause(re);
			throw re;
		}
		return balance;
	}


	public void updateBalance(long accountId, double newBalance) throws BankAccountNotFoundException {
		String query = "UPDATE bankaccounts SET account_balance=? WHERE account_id="+accountId;
		// try  {
			 jdbcTemplate.update(query,newBalance);
	
		// }catch(EmptyResultDataAccessException ex){
		// 	BankAccountNotFoundException  re= new BankAccountNotFoundException("Bank Account not Found");
		// 	ex.initCause(re);
		// 	throw re;
		// }
	

	}



	

}
