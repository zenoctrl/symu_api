package com.example.symu_api.DEALERSHIP.Repository;

import com.example.symu_api.DEALERSHIP.Entity.DealershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealershipRepo extends JpaRepository<DealershipEntity,Long> {
    DealershipEntity getDealershipEntitiesByDealerCode(int dealerCode);
    List<DealershipEntity> getDealershipEntitiesByDealerCountryCodeAndDealerCompanyCode(int dealerCountryCode, int dealerCompanyCode);
}
