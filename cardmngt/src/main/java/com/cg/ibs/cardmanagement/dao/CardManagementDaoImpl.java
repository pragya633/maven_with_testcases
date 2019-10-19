package com.cg.ibs.cardmanagement.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.cg.ibs.cardmanagement.bean.AccountBean;
import com.cg.ibs.cardmanagement.bean.CaseIdBean;
import com.cg.ibs.cardmanagement.bean.CreditCardBean;
import com.cg.ibs.cardmanagement.bean.CreditCardTransaction;
import com.cg.ibs.cardmanagement.bean.CustomerBean;
import com.cg.ibs.cardmanagement.bean.DebitCardBean;
import com.cg.ibs.cardmanagement.bean.DebitCardTransaction;

public class CardManagementDaoImpl implements CustomerDao, BankDao {

	CaseIdBean caseIdObj = new CaseIdBean();
	DebitCardBean bean = new DebitCardBean();
	CreditCardBean bean1 = new CreditCardBean();
	CustomerBean bean2 = new CustomerBean();
	AccountBean bean3=new AccountBean();
	static BigInteger accountNumber=new BigInteger("12345678910");
	static BigInteger UCI2=new BigInteger("7894561239632587");

	static BigInteger debitCardNum1=new BigInteger("5234567891012131");
	static BigInteger debitCardNum2=new BigInteger("5221562391012233");
	static BigInteger creditCardNum =new BigInteger("5189101213259898");

	private static BigInteger UCI;
	private static Map<String, DebitCardTransaction> debitCardTransactionDetails = new HashMap<String, DebitCardTransaction>();
	private static Map<String, CreditCardTransaction> creditCardTransactionDetails = new HashMap<String, CreditCardTransaction>();
	private static Map<String, CaseIdBean> queryDetails = new HashMap<String, CaseIdBean>();
	private static Map<BigInteger, DebitCardBean> debitCardDetails = new HashMap<BigInteger, DebitCardBean>();
	private static Map<BigInteger, CreditCardBean> creditCardDetails = new HashMap<BigInteger, CreditCardBean>();
	private static Map<BigInteger, CustomerBean> customerDetails = new HashMap<BigInteger, CustomerBean>();
	private static Map<BigInteger, AccountBean> accountDetails = new HashMap<BigInteger, AccountBean>();
	static {
		
		CaseIdBean caseId1=new CaseIdBean("ANCC9999",LocalDateTime.of(2023, Month.JUNE, 30, 12, 20),"Pending",accountNumber,UCI2,"in process",creditCardNum,"ANCC00005247330");
			queryDetails.put(caseId1.getCaseIdTotal(), caseId1);	
		
		AccountBean account1=new AccountBean(accountNumber,UCI2);
		accountDetails.put(account1.getAccountNumber(), account1);

		DebitCardBean debit1 = new DebitCardBean(accountNumber, debitCardNum1,
				"Active", "Mohit Pursnani", 067, 2131, LocalDate.of(2024, Month.JULY, 30),
				"Platinum");
		DebitCardBean debit2 = new DebitCardBean(accountNumber, debitCardNum2,
				"Active", "Mohit Pursnani", 057, 2233, LocalDate.of(2022, Month.AUGUST, 30),
				"Silver");

		debitCardDetails.put(debit1.getDebitCardNumber(), debit1);
		debitCardDetails.put(debit2.getDebitCardNumber(), debit2);

		CreditCardBean credit1 = new CreditCardBean(creditCardNum, "Active", "Mohit Pursnani", 623,
				9898, LocalDate.of(2023, Month.JUNE, 30), UCI2, "Gold", 200, new BigDecimal("100000.00"),
				690600.0);

		creditCardDetails.put(credit1.getCreditCardNumber(), credit1);

		CustomerBean cust1 = new CustomerBean(UCI2, "Mohit", "Pursnani",
				"mohit@gmail.com", "201965231351", "9774216545");

		customerDetails.put(cust1.getUCI(), cust1);

		DebitCardTransaction debittrans1 = new DebitCardTransaction("DEB101",UCI2,
				accountNumber, debitCardNum1,
				LocalDateTime.of(2019, Month.OCTOBER, 04, 12, 54, 6), new BigDecimal("1563"), "Petrol", "Debit");
		DebitCardTransaction debittrans2 = new DebitCardTransaction("DEB102", UCI2,
				accountNumber, debitCardNum1,
				LocalDateTime.of(2019, Month.AUGUST, 8, 18, 32, 8), new BigDecimal("20.45"), "Interest", "Credit");
		debitCardTransactionDetails.put(debittrans1.getTransactionId(), debittrans1);
		debitCardTransactionDetails.put(debittrans2.getTransactionId(), debittrans2);

		CreditCardTransaction credittrans1 = new CreditCardTransaction("CRED101", UCI2,
				creditCardNum, LocalDateTime.of(2019, Month.OCTOBER, 12, 11, 54, 6),
				new BigDecimal("5000"), "Shopping");
		CreditCardTransaction credittrans2 = new CreditCardTransaction("CRED102", UCI2,
				creditCardNum, LocalDateTime.of(2019, Month.SEPTEMBER, 25, 23, 21, 6),
				new BigDecimal("500"), "Movie");
		creditCardTransactionDetails.put(credittrans1.getTransactionid(), credittrans1);
		creditCardTransactionDetails.put(credittrans2.getTransactionid(), credittrans2);

	}

	public CardManagementDaoImpl() {
		super();
	}

	public CardManagementDaoImpl(CaseIdBean caseIdObj) {
		super();

		this.caseIdObj = caseIdObj;

	}

	public boolean verifyAccountNumber(BigInteger accountNumber) {

		return accountDetails.containsKey(accountNumber);

		
	}

	public boolean verifyDebitCardNumber(BigInteger debitCardNumber) {

		return debitCardDetails.containsKey(debitCardNumber);
		
	}

	public boolean verifyCreditCardNumber(BigInteger creditCardNumber) {

		return creditCardDetails.containsKey(creditCardNumber);
		
	}

	public BigInteger getAccountNumber(BigInteger debitCardNumber) {
		return debitCardDetails.get(debitCardNumber).getAccountNumber();
		
	}
	
	public BigInteger getNDCUci(BigInteger accountNumber) {
		bean3 = accountDetails.get(accountNumber);
			return bean3.getUCI();
		
	}
	
	public BigInteger getDebitUci(BigInteger debitCardNumber) {
		BigInteger accountNumber = debitCardDetails.get(debitCardNumber).getAccountNumber();
		return getNDCUci(accountNumber);
	}
	
	public BigInteger getCreditUci(BigInteger creditCardNumber) {
		return creditCardDetails.get(creditCardNumber).getUCI();
	
	}
	
	public String getQueryStatus(String caseIdTotal) {
		return queryDetails.get(caseIdTotal).getStatusOfQuery();}
	
	

	@Override
	public void newCreditCard(CaseIdBean caseIdObj) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	@Override
	public void newDebitCard(CaseIdBean caseIdObj, BigInteger accountNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	@Override
	public List<CaseIdBean> viewAllQueries() {
		List<CaseIdBean> query = new ArrayList();

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {
			if(entry.getValue().getStatusOfQuery().equalsIgnoreCase("pending") || entry.getValue().getStatusOfQuery().equalsIgnoreCase("In process"))
			query.add(entry.getValue());
		}

		return query;

	}

	@Override
	public List<DebitCardBean> viewAllDebitCards() {
		List<DebitCardBean> debitCards = new ArrayList();

		for (Entry<BigInteger, DebitCardBean> entry : debitCardDetails.entrySet()) {
			debitCards.add(entry.getValue());
		}

		return debitCards;

	}

	public List<CreditCardBean> viewAllCreditCards() {
		List<CreditCardBean> creditCards = new ArrayList<>();

		for (Entry<BigInteger, CreditCardBean> entry : creditCardDetails.entrySet()) {
			creditCards.add(entry.getValue());
		}
		return creditCards;

	}

	public void requestDebitCardLost(CaseIdBean caseIdObj, BigInteger debitCardNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	public void requestCreditCardLost(CaseIdBean caseIdObj, BigInteger creditCardNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	public String getdebitCardType(BigInteger debitCardNumber) {
		return debitCardDetails.get(debitCardNumber).getDebitCardType();

	}

	public String requestDebitCardUpgrade(CaseIdBean caseIdObj, BigInteger debitCardNumber) {
		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
		return ("TICKET RAISED SUCCESSFULLY");

	}

	public int getDebitCardPin(BigInteger debitCardNumber) {
		return debitCardDetails.get(debitCardNumber).getDebitCurrentPin();
		

	}

	public void setNewDebitPin(BigInteger debitCardNumber, int newPin) {
		bean = debitCardDetails.get(debitCardNumber);
		bean.setDebitCurrentPin(newPin);
		debitCardDetails.replace(debitCardNumber, bean);
	}

	public int getCreditCardPin(BigInteger creditCardNumber) {
		return creditCardDetails.get(creditCardNumber).getCreditCurrentPin();
		

	}

	public void setNewCreditPin(BigInteger creditCardNumber, int newPin) {
		bean1 = creditCardDetails.get(creditCardNumber);
		bean1.setCreditCurrentPin(newPin);
		creditCardDetails.replace(creditCardNumber, bean1);
	}

	public void requestCreditCardUpgrade(CaseIdBean caseIdObj, BigInteger creditCardNumber) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);
	

	}

	public String getcreditCardType(BigInteger creditCardNumber) {

		return creditCardDetails.get(creditCardNumber).getCreditCardType();
		
	}

	@Override
	public boolean verifyDebitTransactionId(String transactionId) {

		return debitCardTransactionDetails.containsKey(transactionId);
		
	}

	@Override
	public void raiseDebitMismatchTicket(CaseIdBean caseIdObj, String transactionId) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	@Override
	public void raiseCreditMismatchTicket(CaseIdBean caseIdObj, String transactionId) {

		queryDetails.put(caseIdObj.getCaseIdTotal(), caseIdObj);

	}

	public List<DebitCardTransaction> getDebitTrans(int dys, BigInteger debitCardNumber) {

		List<DebitCardTransaction> debitCardsList = new ArrayList();
		LocalDateTime dLocalDateTime = LocalDateTime.now().minusDays(dys);
		for (Entry<String, DebitCardTransaction> entry : debitCardTransactionDetails.entrySet()) {
			if (entry.getValue().getDate().isAfter(dLocalDateTime))
				debitCardsList.add(entry.getValue());
		}

		return debitCardsList;
	}

	@Override
	public List<CreditCardTransaction> getCreditTrans(int days, BigInteger creditCardNumber) {

		List<CreditCardTransaction> creditCardsList = new ArrayList();
		LocalDateTime dLocalDateTime = LocalDateTime.now().minusDays(days);
		for (Entry<String, CreditCardTransaction> entry : creditCardTransactionDetails.entrySet()) {
			if (entry.getValue().getDate().isAfter(dLocalDateTime))
				creditCardsList.add(entry.getValue());
		}

		return creditCardsList;
	}

	@Override
	public boolean verifyQueryId(String queryId) {

		return queryDetails.containsKey(queryId);
		

	}

	@Override
	public void setQueryStatus(String queryId, String newStatus) {

		caseIdObj = queryDetails.get(queryId);
		caseIdObj.setStatusOfQuery(newStatus);
		queryDetails.replace(newStatus, caseIdObj);

	}

	@Override
	public boolean verifyCreditTransactionId(String transactionId) {
		return creditCardTransactionDetails.containsKey(transactionId);
		
	}

	@Override
	public String getCustomerReferenceId(CaseIdBean caseIdObjService, String customerReferenceId) {

		String BankId = null;

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {
			if (customerReferenceId.equals(entry.getValue().getCustomerReferenceId()))
				BankId = entry.getValue().getCaseIdTotal();
		}

		return queryDetails.get(BankId).getStatusOfQuery();

		
	}

	@Override
	public void actionANDC(BigInteger debitCardNumber, Integer cvv, Integer pin, String queryId, String status) {

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {
			BigInteger accountNumber = entry.getValue().getAccountNumber();
			UCI = entry.getValue().getUCI();
			String type = entry.getValue().getDefineQuery();
			CustomerBean bean = new CustomerBean();
			bean = customerDetails.get(UCI);
			String firstName = bean.getFirstName();
			String lastName = bean.getLastName();
			DebitCardBean debit3 = new DebitCardBean(accountNumber, debitCardNumber, status,
					(firstName + " " + lastName), cvv, pin, LocalDate.now().plusYears(5), type);
			debitCardDetails.put(debitCardNumber, debit3);
		
		}

	}

	@Override
	public void actionANCC(BigInteger creditCardNumber, int cvv, int pin, String queryId, int score, double income,
			String status) {
		BigInteger UCI;

		for (Entry<String, CaseIdBean> entry : queryDetails.entrySet()) {

			BigDecimal limit = new BigDecimal("0");
			UCI = entry.getValue().getUCI();
			String type = entry.getValue().getDefineQuery();
			if (type == "Silver") {
				limit = new BigDecimal(50000);
			} else if (type == "Gold") {
				limit = new BigDecimal(100000);
			} else if (type == "Platinium") {
				limit = new BigDecimal(500000);
			}
			UCI = entry.getValue().getUCI();
			CustomerBean bean = new CustomerBean();
			CreditCardBean credit3 = new CreditCardBean(creditCardNumber, status, "Mohit Pursnani", cvv, pin,
					LocalDate.now().plusYears(5), UCI, type, score, limit, income);
			creditCardDetails.put(creditCardNumber, credit3);
		
		}
	}

	public void actionBlockDC(String queryId, String status) {

		caseIdObj = queryDetails.get(queryId);
		BigInteger debitCardNumber = caseIdObj.getCardNumber();
		bean = debitCardDetails.get(debitCardNumber);
		bean.setDebitCardStatus(status);
		debitCardDetails.replace(debitCardNumber, bean);
		queryDetails.remove(queryId);
	}

	public void actionBlockCC(String queryId, String status) {

		caseIdObj = queryDetails.get(queryId);
		BigInteger creditCardNumber = caseIdObj.getCardNumber();
		bean1 = creditCardDetails.get(creditCardNumber);
		bean1.setCreditCardStatus(status);
		debitCardDetails.replace(creditCardNumber, bean);
		queryDetails.remove(queryId);

	}

	public void actionUpgradeDC(String queryId) {

		caseIdObj = queryDetails.get(queryId);
		BigInteger debitCardNumber = caseIdObj.getCardNumber();
		String type = caseIdObj.getDefineQuery();
		bean = debitCardDetails.get(debitCardNumber);
		bean.setDebitCardType(type);
		debitCardDetails.replace(debitCardNumber, bean);
		queryDetails.remove(queryId);
	}

	public String getDebitCardStatus(BigInteger debitCardNumber) {
		bean = debitCardDetails.get(debitCardNumber);
		return bean.getDebitCardStatus();
	
	}
	public String getCreditCardStatus(BigInteger creditCardNumber) {
		bean1 = creditCardDetails.get(creditCardNumber);
		return bean1.getCreditCardStatus();
		
	}
	

	@Override
	public BigInteger getDebitCardNumber(String transactionId) {
	return debitCardTransactionDetails.get(transactionId).getDebitCardNumber();
	}

	@Override
	public BigInteger getDMUci(String transactionId) {
     BigInteger dbCard=getDebitCardNumber(transactionId);
     return getDebitUci( dbCard);
		
	}

	@Override
	public BigInteger getDMAccountNumber(String transactionId) {
		BigInteger dbCard=getDebitCardNumber(transactionId);
		return getAccountNumber(dbCard);
	}

	@Override
	public BigInteger getCMUci(String transactionId) {
		BigInteger creditCardNumber=creditCardDetails.get(transactionId).getCreditCardNumber();
		return getCreditUci(creditCardNumber);
	}

	@Override
	public BigInteger getUci() {
		return bean2.getUCI();
	
	}

	@Override
	public void actionUpgradeCC(String queryId) {
	caseIdObj = queryDetails.get(queryId);
	BigInteger creditCardNumber = caseIdObj.getCardNumber();
	String type = caseIdObj.getDefineQuery();
	bean1 = creditCardDetails.get(creditCardNumber);
	bean1.setCreditCardType(type);
	creditCardDetails.replace(creditCardNumber, bean1);
	queryDetails.remove(queryId);
}
}
