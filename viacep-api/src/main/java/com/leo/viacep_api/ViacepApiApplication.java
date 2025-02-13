package com.leo.viacep_api;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.leo.viacep_api.model.CepResponse;
import com.leo.viacep_api.service.CepService;

@SpringBootApplication
public class ViacepApiApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ViacepApiApplication.class, args);
        
        // Get the CepService bean from the context
        CepService cepService = context.getBean(CepService.class);

        // Scanner to receive user input
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("Enter a CEP (or type 'exit' to quit): ");
            String cep = scanner.nextLine();

            if (cep.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            } // equalsIgnoreCase is used to compare strings in a case-insensitive manner

            // Validate input (CEP should have exactly 8 digits)
            if (!cep.matches("\\d{8}")) {
                System.out.println("Invalid CEP format! Please enter 8 digits.");
                continue;
            }

            // Call the service to get CEP information
            CepResponse response = cepService.searchCep(cep);

            // Check if the response is valid
            if (response != null && response.getCep() != null) {
                System.out.println("CEP found: " + response);
            } else {
                System.out.println("CEP not found!");
            }
        }

        // Close the scanner to prevent memory leaks
        scanner.close();
    }
}
