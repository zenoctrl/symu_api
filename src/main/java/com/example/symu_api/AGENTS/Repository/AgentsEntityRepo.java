package com.example.symu_api.AGENTS.Repository;

import com.example.symu_api.AGENTS.Entity.AgentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentsEntityRepo extends JpaRepository<AgentsEntity, Long> {
    AgentsEntity getAgentsEntitiesByAgentCode(int agentCode);

    AgentsEntity getAgentsEntitiesByAgentNationalId(String agentNationalId);

    AgentsEntity getAgentsEntitiesByAgentPhoneNumber(String agentPhoneNumber);
}
