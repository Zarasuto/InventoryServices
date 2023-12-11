package org.microservices.inventoryservices.Controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.microservices.inventoryservices.Dto.InventoryResponse;
import org.microservices.inventoryservices.Service.InventoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryService;


    //localhost:8082/api/inventory?sku-code=iphone_13$sku-code=iphone_14
    //RequestParam uses multiple same name parameters in a single list
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam(required = false,name="sku-code") List<String> skuCodeList){
        return inventoryService.isInStock(skuCodeList);
    }
}
