package ar.fit_zone_spboot;

import ar.fit_zone_spboot.model.Client;
import ar.fit_zone_spboot.service.IClientService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class FitZoneApplication implements CommandLineRunner {

	@Autowired
	private IClientService clientService;

	private static final Logger logger = LoggerFactory.getLogger(FitZoneApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FitZoneApplication.class, args);
	}

	String nl = System.lineSeparator();
	@Override
	public void run(String... args) throws Exception {
		fitZoneApp();
	}

	private void fitZoneApp(){
		logger.info(this.nl + "*** Fit Zone (GYM) ***");

		boolean exit = false;
		Scanner console = new Scanner(System.in);
		while(!exit){
			int option = showMenu(console);
			exit = executeOptions(console, option);
			logger.info(this.nl);
		}
	}

	private int showMenu(Scanner console){
		logger.info(this.nl);
		logger.info(this.nl + """
				1. List Clients
				2. Find Client
				3. Add Client
				4. Remove Client
				5. Update Client
				6. Exit
				Choose an option:\s""");
		return Integer.parseInt(console.nextLine());
	}

	private boolean executeOptions(Scanner console, int option){
		boolean exit = false;
		switch (option){
			case 1 -> {
				logger.info(this.nl + "--- Clients list ---" + this.nl);
				List<Client> clients = clientService.listClients();
				clients.forEach(client -> logger.info(client.toString()+ this.nl));

			}
			case 2 -> {
				logger.info("Client id: ");
				int clientId = Integer.parseInt(console.nextLine());
				var client = clientService.findClientById(clientId);
				logger.info(client.toString() + this.nl);
			}
			case 3 -> {
				logger.info("Name: " + this.nl);
				String name = console.nextLine();
				logger.info("Lastname: " + this.nl);
				String lastName = console.nextLine();
				logger.info("Membership: " + this.nl);
				int membership = Integer.parseInt(console.nextLine());
				var client = new Client();
				client.setName(name);
				client.setLastName(lastName);
				client.setMembership(membership);
				clientService.saveClient(client);
			}
			case 4 -> {
				logger.info("Id: " + this.nl);
				int idClient = Integer.parseInt(console.nextLine());
				clientService.deleteClient(idClient);
			}
			case 5 -> {
				{
					logger.info("Id: " + this.nl);
					int idClient = Integer.parseInt(console.nextLine());
					logger.info("Name: " + this.nl);
					String name = console.nextLine();
					logger.info("Lastname: " + this.nl);
					String lastName = console.nextLine();
					logger.info("Membership: " + this.nl);
					int membership = Integer.parseInt(console.nextLine());
					var client = new Client();
					client.setId(idClient);
					client.setName(name);
					client.setLastName(lastName);
					client.setMembership(membership);
					clientService.saveClient(client);
				}
			}
			case 6 -> {
				exit = true;
				logger.info("Goodbye!");
			}
			default -> logger.info("Invalid option" + option + this.nl);
		}
		return exit;
	}

}
