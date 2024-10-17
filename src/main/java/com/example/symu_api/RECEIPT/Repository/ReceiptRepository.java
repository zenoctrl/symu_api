package com.example.symu_api.RECEIPT.Repository;

import com.example.symu_api.RECEIPT.Entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<ReceiptEntity,Long> {
    @Query(value = "SELECT  MAX (receiptCode) FROM ReceiptEntity")
    Integer findMaxCode();

    ReceiptEntity getAllByReceiptCode(int receiptCode);

    List<ReceiptEntity> getAllByReceiptStockCodeAndReceiptStatus(int stockCode, String stockStatus);

    //List<ReceiptEntity> getAllByReceiptStockCode(int stockCode);

}
