package com.example.symu_api.STOCK_STATUS.repository;


import com.example.symu_api.STOCK_STATUS.entity.ProcessFlowEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProcessFlowRepository extends CrudRepository<ProcessFlowEntity,Integer> {
    ProcessFlowEntity findDistinctByPflwShtDesc(String shtDesc);
}
