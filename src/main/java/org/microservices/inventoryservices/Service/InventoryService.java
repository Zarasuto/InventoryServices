package org.microservices.inventoryservices.Service;

import org.microservices.inventoryservices.Dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<String> skuCode);
}
