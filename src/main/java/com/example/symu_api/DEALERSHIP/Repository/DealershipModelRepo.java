package com.example.symu_api.DEALERSHIP.Repository;

import com.example.symu_api.DEALERSHIP.Entity.DealershipEntity;
import com.example.symu_api.DEALERSHIP.Entity.DealershipModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealershipModelRepo extends JpaRepository<DealershipModel,Long> {
    List<DealershipModel> getAllByDealerCompanyCode(int companyCode);
}
