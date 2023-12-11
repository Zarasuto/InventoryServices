package org.microservices.inventoryservices.Service;

import lombok.RequiredArgsConstructor;
import org.microservices.inventoryservices.Dto.InventoryResponse;
import org.microservices.inventoryservices.Model.Inventory;
import org.microservices.inventoryservices.Repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(Inventory -> mapToInventoryResponseDto(Inventory) ).toList();
    }

    private InventoryResponse mapToInventoryResponseDto(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity()>0)
                .build();
    }
}
