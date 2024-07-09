package com.example.symu_api.PROCESS_FLOW.repository;


import com.example.symu_api.PROCESS_FLOW.entity.ProcessFlowEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProcessFlowRepository extends CrudRepository<ProcessFlowEntity,Integer> {
    ProcessFlowEntity findDistinctByPflwShtDesc(String shtDesc);
}
