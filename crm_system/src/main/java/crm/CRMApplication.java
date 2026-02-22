package crm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import crm.entity.Address;
import crm.service.CustomerService;
import crm.service.LeadService;
import crm.service.OrderService;
import crm.service.ProductService;
import crm.service.ReportService;
import crm.service.TicketService;

public class CRMApplication {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		Scanner scanner = new Scanner(System.in);

		CustomerService customerService = new CustomerService(em);
		LeadService leadService = new LeadService(em);
		ProductService productService = new ProductService(em);
		OrderService orderService = new OrderService(em);
		TicketService ticketService = new TicketService(em);
		ReportService reportService = new ReportService(em);

		boolean running = true;

		while (running) {
			System.out.println("\n========== CRM SYSTEM ==========");
			System.out.println("1.  Register Customer");
			System.out.println("2.  Add Address to Customer");
			System.out.println("3.  Create Lead");
			System.out.println("4.  Create Employee");
			System.out.println("5.  Assign Lead to Employee");
			System.out.println("6.  Convert Lead to Customer");
			System.out.println("7.  Add Product");
			System.out.println("8.  Place Order");
			System.out.println("9.  Raise Support Ticket");
			System.out.println("10. View Employee Performance");
			System.out.println("0.  Exit");
			System.out.print("Choose option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // consume newline

			switch (choice) {

			case 1:
				System.out.print("Enter name: ");
				String name = scanner.nextLine();
				System.out.print("Enter email: ");
				String email = scanner.nextLine();
				System.out.print("Enter phone: ");
				String phone = scanner.nextLine();
				customerService.registerCustomer(name, email, phone);
				break;

			case 2:
				System.out.print("Enter customer ID: ");
				Long customerId = scanner.nextLong();
				scanner.nextLine();
				System.out.print("Enter street: ");
				String street = scanner.nextLine();
				System.out.print("Enter city: ");
				String city = scanner.nextLine();
				System.out.print("Enter state: ");
				String state = scanner.nextLine();
				System.out.print("Enter zip code: ");
				String zip = scanner.nextLine();

				Address address = new Address();
				address.setStreet(street);
				address.setCity(city);
				address.setState(state);
				address.setZipCode(zip);

				customerService.addAddressToCustomer(customerId, address);
				break;

			case 3:
				System.out.print("Enter lead name: ");
				String leadName = scanner.nextLine();
				System.out.print("Enter source (e.g. Website, Referral): ");
				String source = scanner.nextLine();
				System.out.print("Enter contact info: ");
				String contactInfo = scanner.nextLine();
				leadService.createLead(leadName, source, contactInfo);
				break;

			case 4:
				System.out.print("Enter employee name: ");
				String empName = scanner.nextLine();
				System.out.print("Enter department: ");
				String dept = scanner.nextLine();
				leadService.createEmployee(empName, dept);
				break;

			case 5:
				System.out.print("Enter lead ID: ");
				Long leadId = scanner.nextLong();
				System.out.print("Enter employee ID: ");
				Long empId = scanner.nextLong();
				scanner.nextLine();
				leadService.assignLeadToEmployee(leadId, empId);
				break;

			case 6:
				System.out.print("Enter lead ID to convert: ");
				Long convertId = scanner.nextLong();
				scanner.nextLine();
				leadService.convertLeadToCustomer(convertId);
				break;

			case 7:
				System.out.print("Enter product name: ");
				String productName = scanner.nextLine();
				System.out.print("Enter price: ");
				double price = scanner.nextDouble();
				scanner.nextLine();
				productService.addProduct(productName, price);
				break;

			case 8:
				System.out.print("Enter customer ID: ");
				Long orderCustomerId = scanner.nextLong();
				scanner.nextLine();
				System.out.print("How many products? ");
				int count = scanner.nextInt();
				scanner.nextLine();

				List<Long> productIds = new ArrayList<>();
				for (int i = 0; i < count; i++) {
					System.out.print("Enter product ID " + (i + 1) + ": ");
					productIds.add(scanner.nextLong());
					scanner.nextLine();
				}
				orderService.placeOrder(orderCustomerId, productIds);
				break;

			case 9:
				System.out.print("Enter order ID: ");
				Long orderId = scanner.nextLong();
				scanner.nextLine();
				System.out.print("Enter issue description: ");
				String issue = scanner.nextLine();
				ticketService.raiseTicket(orderId, issue);
				break;

			case 10:
				System.out.print("Enter employee ID: ");
				Long reportEmpId = scanner.nextLong();
				scanner.nextLine();
				reportService.getEmployeePerformance(reportEmpId);
				break;

			case 0:
				running = false;
				System.out.println("Exiting CRM system. Goodbye!");
				break;

			default:
				System.out.println("Invalid option. Please try again.");
			}
		}

		scanner.close();
		em.close();
		emf.close();
	}
}