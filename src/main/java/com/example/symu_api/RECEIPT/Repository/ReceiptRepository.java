package com.example.symu_api.RECEIPT.Repository;

import com.example.symu_api.RECEIPT.Entity.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<ReceiptEntity,Long> {

    ReceiptEntity getAllByReceiptCode(int receiptCode);

    List<ReceiptEntity> getAllByReceiptStockCode(int stockCode);
}
