package com.example.symu_api.DEALERSHIP.Service;

import com.example.symu_api.COMMON.Model.SymuResponse;
import com.example.symu_api.DEALERSHIP.Entity.DealershipEntity;
import com.example.symu_api.DEALERSHIP.Repository.DealershipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealershipServiceImpl implements DealershipService {
    @Autowired
    private DealershipRepo dealershipRepo;

    @Override
    public SymuResponse createOrUpdateDealership(DealershipEntity dealership) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            DealershipEntity dealershipEntity = dealershipRepo.save(dealership);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(dealershipEntity);
        }catch (Exception e){
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getDealershipEntitiesByDealerCode(int dealerCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try{
            DealershipEntity dealershipEntity=dealershipRepo.getDealershipEntitiesByDealerCode(dealerCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(dealershipEntity);
        }catch (Exception e){
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getDealershipEntitiesByCountryCode(int countryCode,int companyCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try {
            List<DealershipEntity> dealershipEntityList=dealershipRepo.getDealershipEntitiesByDealerCountryCodeAndDealerCompanyCode(
                    countryCode,companyCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(dealershipEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }

    @Override
    public SymuResponse getAllByCompanyCode(int companyCode) {
        SymuResponse symuResponse=new SymuResponse<>();
        try {
            List<DealershipEntity> dealershipEntityList=dealershipRepo.getAllByDealerCompanyCode(companyCode);
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("Success");
            symuResponse.setData(dealershipEntityList);
        }catch (Exception e){
            symuResponse.setStatusCode("0");
            symuResponse.setMessage("failed");
            symuResponse.setData(e.getMessage());
        }
        return symuResponse;
    }
}
