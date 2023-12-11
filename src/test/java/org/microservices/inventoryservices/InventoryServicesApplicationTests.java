package org.microservices.inventoryservices;

import org.junit.jupiter.api.Test;
import org.microservices.inventoryservices.Model.Inventory;
import org.microservices.inventoryservices.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class InventoryServicesApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username",mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password",mySQLContainer::getPassword);
    }
    @Autowired
    private InventoryRepository inventoryRepository;



    @Test
    void shouldSetSkuCodeAndReturnTrue() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setSkuCode("iphone_11");
        inventory.setQuantity(100);
        inventoryRepository.save(inventory);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory?sku-code=iphone_11"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"skuCode\":\"iphone_13\",\"inStock\":true}]"));

    }

}
