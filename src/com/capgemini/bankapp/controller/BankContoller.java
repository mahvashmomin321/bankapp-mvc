package com.capgemini.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankapp.exceprtion.BankAccountNotFoundException;
import com.capgemini.bankapp.exceprtion.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;

@Controller
@RequestMapping("/bank")
public class BankContoller {

	@Autowired
	BankAccountService bankAccountService;

	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@RequestMapping("/home/new_acc")
	public String inputpage() {
		return "new_acc";
	}

	@RequestMapping("/new")
	public String addNewAccount(@RequestParam("customer_name") String accountHolderName,
			@RequestParam("account_type") String accountType, @RequestParam("account_balance") double accountBalance,
			Model model) {

		bankAccountService.addNewBankAccount(new BankAccount(accountHolderName, accountType, accountBalance));
		model.addAttribute("message", "Account Added Succesfullly...");
		return "success";

	}

	@RequestMapping("home/delete-acc")
	public String deletePage() {
		return "delete-acc";
	}

	@RequestMapping("/delete")
	public String deleteAccount(@RequestParam("account_id") long accountId, Model model) {

		try {
			boolean result = bankAccountService.deleteBankAccount(accountId);
			model.addAttribute("message", "Account deleted Successfully..");
		} catch (BankAccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		return "success";

	}

	@RequestMapping("home/update-acc")
	public String updateAcc() {
		return "update-account";
	}

	@RequestMapping("/update-acc")
	public String searchForUpdateAccount(@RequestParam("account_id") long accountId,Model model) {
		try {
			BankAccount account=bankAccountService.searchAccount(accountId);
			model.addAttribute("account", account);
		} catch (BankAccountNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		return "accountInfo";

	}

	@RequestMapping("/updateaccount")
	public String updateAccount(@RequestParam("account_id") long accountId,
			@RequestParam("customer_name") String accountHolderName, @RequestParam("account_type") String accountType, Model model) {

		try {
			bankAccountService.updateAccount(accountId, accountHolderName, accountType);
			model.addAttribute("message", "Account updated Successfully..");
		} catch (BankAccountNotFoundException e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		return "success";

	}

	@RequestMapping("home/withdraw")
	public String withdrawPage() {
		return "withdraw";
	}

	@RequestMapping("/withdrawp")
	public String withdraw(@RequestParam("account_id") long accountId, @RequestParam("account_balance") double amount,
			Model model) {
		try {
			double balance = bankAccountService.withdraw(accountId, amount);
			model.addAttribute("message", "successful..");
		} catch (LowBalanceException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		} catch (BankAccountNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		return "success";
	}

	@RequestMapping("home/deposit")
	public String depositPage() {
		return "deposit";
	}

	@RequestMapping("/depositp")
	public String deposit(@RequestParam("account_id") long accountId, @RequestParam("account_balance") double amount,
			Model model) {

		try {
			double balance = bankAccountService.deposit(accountId, amount);
			model.addAttribute("message", "successful...Your current Balance is:" + balance);
		} catch (BankAccountNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		return "success";
	}
	
	
	@RequestMapping("home/fund-transfer")
	public String fundTransferPage() {
		return "fund-transfer";
	}
	
	
	@RequestMapping("/fundtransferp")
	public String fundtransfer(@RequestParam("sender_account") long senderId,@RequestParam("recipent_account") long recipentId,
			@RequestParam("amount") double amount,Model model) {
		
		try {
			double balance=bankAccountService.fundTransfer(senderId, recipentId, amount);
			model.addAttribute("message", "successful...Your current Balance is:" + balance);
		} catch (LowBalanceException e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
		} catch (BankAccountNotFoundException e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		return "success";
		
	}

	@RequestMapping("home/check-balance")
	public String checkBalancePage() {
		return "check-balance";
	}

	@RequestMapping("/checkB")
	public String checkBalance(@RequestParam("account_id") long accountId, Model model) {
		try {
			double balance = bankAccountService.checkBalance(accountId);
			model.addAttribute("message", "Your current Balance is:" + balance);
		} catch (BankAccountNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		return "success";
	}

	
	@RequestMapping("home/search-account")
	public String searchPage() {
		return "search-account";
	}


	
	
	@RequestMapping("/accountDetails")
	public String searchAccount(@RequestParam("account_id") long accountId, Model model) {
		try {
			BankAccount account = bankAccountService.searchAccount(accountId);
			model.addAttribute("account", account);
		} catch (BankAccountNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}

		return "accountDetails";
	}

	
	@RequestMapping("home/show-details")
	public String showdetails(Model model) {
		
		List<BankAccount> accounts=bankAccountService.findAllBankAccounts();
		model.addAttribute("accounts", accounts);
		return "show-details";
		
	}
	
}
