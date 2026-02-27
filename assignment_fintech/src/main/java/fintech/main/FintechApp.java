package fintech.main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import fintech.dao.BankAccountDAO;
import fintech.dao.CardDAO;
import fintech.dao.CustomerDAO;
import fintech.dao.TransactionDAO;
import fintech.entity.BankAccount;
import fintech.entity.Card;
import fintech.entity.Customer;
import fintech.entity.Transaction;
import fintech.util.JPAUtil;

public class FintechApp {

	static Scanner sc = new Scanner(System.in);
	static CustomerDAO customerDAO = new CustomerDAO();
	static BankAccountDAO accountDAO = new BankAccountDAO();
	static TransactionDAO txnDAO = new TransactionDAO();
	static CardDAO cardDAO = new CardDAO();

	public static void main(String[] args) {
		boolean running = true;

		while (running) {
			printMainMenu();
			String choice = sc.nextLine().trim();

			switch (choice) {
			case "1":
				addCustomer();
				break;
			case "2":
				viewAllCustomers();
				break;
			case "3":
				createBankAccount();
				break;
			case "4":
				viewAllAccounts();
				break;
			case "5":
				linkAccountToCustomer();
				break;
			case "6":
				updateBalance();
				break;
			case "7":
				recordTransaction();
				break;
			case "8":
				viewTransactions();
				break;
			case "9":
				viewTransactionsByType();
				break;
			case "10":
				issueCard();
				break;
			case "11":
				assignCardToCustomer();
				break;
			case "12":
				viewAllCards();
				break;
			case "13":
				deactivateCard();
				break;
			case "0":
				JPAUtil.close();
				System.out.println("Goodbye!");
				running = false;
				break;
			default:
				System.out.println("Invalid option. Try again.");
			}
		}

		sc.close();
	}

	//
	static void printMainMenu() {
		System.out.println("\n========== FINTECH BANKING SYSTEM ==========");
		System.out.println("--- Customer ---");
		System.out.println("1.  Add Customer");
		System.out.println("2.  View All Customers");
		System.out.println("--- Bank Account ---");
		System.out.println("3.  Create Bank Account");
		System.out.println("4.  View All Accounts");
		System.out.println("5.  Link Account to Customer");
		System.out.println("6.  Update Balance");
		System.out.println("--- Transactions ---");
		System.out.println("7.  Record Transaction");
		System.out.println("8.  View Transactions by Account");
		System.out.println("9.  View Transactions by Type");
		System.out.println("--- Cards ---");
		System.out.println("10. Issue Card");
		System.out.println("11. Assign Card to Customer");
		System.out.println("12. View All Cards");
		System.out.println("13. Deactivate Card");
		System.out.println("0.  Exit");
		System.out.print("Choose option: ");
	}


	// CUSTOMER METHODS


	static void addCustomer() {
		System.out.print("Enter full name: ");
		String name = sc.nextLine();
		System.out.print("Enter email: ");
		String email = sc.nextLine();
		System.out.print("Enter phone: ");
		String phone = sc.nextLine();

		Customer customer = new Customer(name, email, phone);
		customerDAO.save(customer);
	}

	static void viewAllCustomers() {
		List<Customer> customers = customerDAO.findAll();
		if (customers.isEmpty()) {
			System.out.println("No customers found.");
			return;
		}
		System.out.println("\n--- All Customers ---");
		for (Customer c : customers) {
			System.out.println("ID: " + c.getCustomerId() + " | Name: " + c.getFullName() + " | Email: " + c.getEmail()
					+ " | Phone: " + c.getPhone());
		}
	}


	// BANK ACCOUNT METHODS


	static void createBankAccount() {
		System.out.print("Enter account number: ");
		String accNum = sc.nextLine();
		System.out.print("Enter account type (SAVINGS/CURRENT): ");
		String accType = sc.nextLine();
		System.out.print("Enter initial balance: ");
		BigDecimal balance = new BigDecimal(sc.nextLine());

		BankAccount account = new BankAccount(accNum, accType, balance);
		accountDAO.save(account);
	}

	static void viewAllAccounts() {
		List<BankAccount> accounts = accountDAO.findAll();
		if (accounts.isEmpty()) {
			System.out.println("No accounts found.");
			return;
		}
		System.out.println("\n--- All Bank Accounts ---");
		for (BankAccount a : accounts) {
			System.out.println("ID: " + a.getAccountId() + " | Number: " + a.getAccountNumber() + " | Type: "
					+ a.getAccountType() + " | Balance: " + a.getBalance());
		}
	}

	static void linkAccountToCustomer() {
		System.out.print("Enter account ID: ");
		Long accountId = Long.parseLong(sc.nextLine());
		System.out.print("Enter customer ID: ");
		Long customerId = Long.parseLong(sc.nextLine());
		accountDAO.linkToCustomer(accountId, customerId);
	}

	static void updateBalance() {
		System.out.print("Enter account ID: ");
		Long accountId = Long.parseLong(sc.nextLine());
		System.out.print("Enter new balance: ");
		BigDecimal newBalance = new BigDecimal(sc.nextLine());
		accountDAO.updateBalance(accountId, newBalance);
	}


	// TRANSACTION METHODS


	static void recordTransaction() {
		System.out.print("Enter account ID: ");
		Long accountId = Long.parseLong(sc.nextLine());
		System.out.print("Enter type (CREDIT/DEBIT): ");
		String type = sc.nextLine().toUpperCase();
		System.out.print("Enter amount: ");
		BigDecimal amount = new BigDecimal(sc.nextLine());
		System.out.print("Enter description: ");
		String desc = sc.nextLine();
		System.out.print("Enter reference number: ");
		String ref = sc.nextLine();

		Transaction txn = new Transaction(type, amount, desc, ref);
		txnDAO.save(txn, accountId);
	}

	static void viewTransactions() {
		System.out.print("Enter account ID: ");
		Long accountId = Long.parseLong(sc.nextLine());

		List<Transaction> txns = txnDAO.findByAccount(accountId);
		if (txns.isEmpty()) {
			System.out.println("No transactions found.");
			return;
		}
		System.out.println("\n--- Transactions for Account " + accountId + " ---");
		for (Transaction t : txns) {
			System.out.println("ID: " + t.getTransactionId() + " | Type: " + t.getTxnType() + " | Amount: "
					+ t.getAmount() + " | Date: " + t.getTxnDate() + " | Desc: " + t.getDescription());
		}
	}

	static void viewTransactionsByType() {
		System.out.print("Enter account ID: ");
		Long accountId = Long.parseLong(sc.nextLine());
		System.out.print("Enter type (CREDIT/DEBIT): ");
		String type = sc.nextLine().toUpperCase();

		List<Transaction> txns = txnDAO.findByType(accountId, type);
		if (txns.isEmpty()) {
			System.out.println("No " + type + " transactions found.");
			return;
		}
		System.out.println("\n--- " + type + " Transactions for Account " + accountId + " ---");
		for (Transaction t : txns) {
			System.out.println("ID: " + t.getTransactionId() + " | Amount: " + t.getAmount() + " | Date: "
					+ t.getTxnDate() + " | Desc: " + t.getDescription());
		}
	}


	// CARD METHODS


	static void issueCard() {
		System.out.print("Enter card number: ");
		String cardNum = sc.nextLine();
		System.out.print("Enter card type (DEBIT/CREDIT/PREPAID): ");
		String cardType = sc.nextLine().toUpperCase();
		System.out.print("Enter card network (VISA/MASTERCARD/RUPAY): ");
		String network = sc.nextLine().toUpperCase();
		System.out.print("Enter expiry date (YYYY-MM-DD): ");
		LocalDate expiry = LocalDate.parse(sc.nextLine());
		System.out.print("Enter account ID to link: ");
		Long accountId = Long.parseLong(sc.nextLine());

		Card card = new Card(cardNum, cardType, network, expiry);
		cardDAO.issueCard(card, accountId);
	}

	static void assignCardToCustomer() {
		System.out.print("Enter card ID: ");
		Long cardId = Long.parseLong(sc.nextLine());
		System.out.print("Enter customer ID: ");
		Long customerId = Long.parseLong(sc.nextLine());
		cardDAO.assignCardToCustomer(cardId, customerId);
	}

	static void viewAllCards() {
        List<Card> cards = cardDAO.findAll();
        if (cards.isEmpty()) {
            System.out.println("No cards found.");
            return;
        }
        System.out.println("\n--- All Cards ---");
        for (Card c : cards) {
            System.out.println("ID: " + c.getCardId() +
                               " | Number: " + c.getCardNumber() +
                               " | Type: " + c.getCardType() +
                               " | Network: " + c.getCardNetwork() +
                               " | Expiry: " + c.getExpiryDate() +
                               " | Active: " + c.getIsActive());
        }
    }

	 static void deactivateCard() {
	        System.out.print("Enter card ID to deactivate: ");
	        Long cardId = Long.parseLong(sc.nextLine());
	        cardDAO.deactivateCard(cardId);
	    }
	}