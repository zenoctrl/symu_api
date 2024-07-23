package com.example.symu_api.STOCK_STATUS.Service;


import com.example.symu_api.STOCK_STATUS.repository.ProcessFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessFlowServiceImpl implements ProcessFlowService {
    @Autowired
    ProcessFlowRepository processFlowRepository;

    @Override
    public int getProcessFlowByShortDesc(String shortDesc) {
         return processFlowRepository.findDistinctByPflwShtDesc(shortDesc).getPflwCode();
    }
}
