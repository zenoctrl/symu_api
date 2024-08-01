package com.example.symu_api.BRANCHES.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "branches")
@Data
public class BranchEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "BRN_CODE")
    private Integer code;
    @Column(name = "BRN_COMP_CODE")
    private Integer companyCode;
    @Column(name = "BRN_SHT_DESC")
    private String shortDesc;
    @Column(name = "BRN_NAME")
    private String name;
    @Column(name = "BRN_DESC")
    private String desc;
    @Column(name = "BRN_STATUS")
    private String status;

}
