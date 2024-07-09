package com.example.symu_api.COMPANY.Repository;

import com.example.symu_api.COMPANY.Entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Integer> {
    CompanyEntity getAllByCode(int compCode);
}
