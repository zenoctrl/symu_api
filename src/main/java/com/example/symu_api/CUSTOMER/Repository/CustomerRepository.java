package com.example.symu_api.CUSTOMER.Repository;

import com.example.symu_api.CUSTOMER.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
    CustomerEntity getCustomerEntitiesByCustomerCode(int customerCode);

    CustomerEntity getCustomerEntitiesByCustomerNationalId(String customerNationalId);

    CustomerEntity getCustomerEntitiesByCustomerPhoneNumber(String customerPhoneNumber);

    CustomerEntity getCustomerEntitiesByCustomerBranchCode(int customerBranchCode);
}
