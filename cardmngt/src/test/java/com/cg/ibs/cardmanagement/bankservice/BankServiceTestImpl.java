package com.cg.ibs.cardmanagement.bankservice;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cg.ibs.cardmanagement.bean.CaseIdBean;
import com.cg.ibs.cardmanagement.dao.BankDao;
import com.cg.ibs.cardmanagement.dao.CardManagementDaoImpl;
import com.cg.ibs.cardmanagement.dao.CustomerDao;
import com.cg.ibs.cardmanagement.exceptionhandling.IBSException;
import com.cg.ibs.cardmanagement.service.BankService;
import com.cg.ibs.cardmanagement.service.BankServiceClassImpl;


public class BankServiceTestImpl {
BankDao bankDao = new CardManagementDaoImpl();
	BankService bankService = new BankServiceClassImpl();
	CaseIdBean caseIdObj = new CaseIdBean();


	
 @Test
	void viewQueriesWithInvalidInput() {
		List <CaseIdBean> queries = bankDao.viewAllQueries();
	
  		assertNotNull(queries);
    					
	}
 
	@Test
	void checkInvalidDebitCardNumber() {
		BigInteger debitCardNumber = new BigInteger("5234567891012198");
		Assertions.assertThrows(IBSException.class, () -> {
			bankService.verifyDebitCardNumber(debitCardNumber);
		});
	}

	@Test
	void checkValidDebitCardNumber() {
		BigInteger debitCardNumber = new BigInteger("5234567891012131");
		try {
			assertEquals(true, bankService.verifyDebitCardNumber(debitCardNumber));
		} catch (IBSException e) {
			fail(e.getMessage());
		}

	}
    @Test
	void checkInvalidCreditCardNumber() {
		BigInteger creditCardNumber = new BigInteger("5189101213259876");
		Assertions.assertThrows(IBSException.class, () -> {
			bankService.verifyCreditCardNumber(creditCardNumber);
		});
	}

	@Test
	void checkValidCreditCardNumber() {
		BigInteger creditCardNumber = new BigInteger("5189101213259898");
		try {
			assertEquals(true, bankService.verifyCreditCardNumber(creditCardNumber));
		} catch (IBSException e) {
			fail(e.getMessage());
		}

	}
	@Test
	void viewTransactionsForDebitCard() {
		int days = 400;
		BigInteger debitCardNumber = new BigInteger("5234567891012131");
		try {
			assertNotNull(bankService.getDebitTransactions(days, debitCardNumber));
		} catch (IBSException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void viewTransactionsForCreditCard() {
		int days = 600;
		BigInteger creditCardNumber = new BigInteger("5189101213259898");
		try {
			assertNotNull(bankService.getCreditTrans(days, creditCardNumber));
		} catch (IBSException e) {
			fail(e.getMessage());
		}
	}


	@Test
	void checkInvalidNoOfDays() {
		int days = 1000;
		Assertions.assertThrows(IBSException.class, () -> {
			bankService.checkDays(days);
		});

	
	}
	
	@Test
	void verifyQueryIdInvalidInput() {
		String queryId="ANDC00009632587";
		Assertions.assertThrows(IBSException.class, () -> {
			bankService.verifyQueryId(queryId);
		});

		
	}
	@Test
	void verifyQueryIdForValidInput() {
		
		String queryId="ANCC9999";
		try {
			assertEquals(true, bankService.verifyQueryId(queryId));
		} catch (IBSException e) {
			fail(e.getMessage());
		}
		
	}
	@Test
	void getNewQueryStatusForValidInput() {
		int newQueryStatus = 2;
		try {
			assertEquals("In Process", bankService.getNewQueryStatus(newQueryStatus));
		} catch (IBSException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void setQueryStatusForValidInput() {
		String queryId="ANCC9999";
		String newStatus="Approved";
		bankService.setQueryStatus(queryId, newStatus);
		assertEquals("Approved", bankDao.getQueryStatus(queryId));
		
	}
	@Test
	void upgradeCC() {}
}