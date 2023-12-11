package org.microservices.inventoryservices;

import org.microservices.inventoryservices.Model.Inventory;
import org.microservices.inventoryservices.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


//EnabledEurekaClient
@SpringBootApplication
public class InventoryServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServicesApplication.class, args);
    }
}
