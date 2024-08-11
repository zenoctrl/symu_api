package com.example.symu_api.DEALERSHIP.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.DEALERSHIP.Entity.DealershipEntity;

import java.util.List;

public interface DealershipService {
    SymuResponse createOrUpdateDealership(DealershipEntity dealership);
    SymuResponse getDealershipEntitiesByDealerCode(int dealerCode);
    SymuResponse getDealershipEntitiesByCountryCode(int countryCode,int companyCode);
    SymuResponse getAllByCompanyCode(int companyCode);
}
