package com.example.symu_api.CLUSTER.Entiry;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cluster")
@Data
public class ClusterEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "cluster_code")
    private Integer code;
    @Column(name = "cluster_COMP_CODE")
    private Integer companyCode;
    @Column(name = "cluster_brn_CODE")
    private Integer clusterBranchCode;
    @Column(name = "cluster_short_desc")
    private String clusterShortDesc;
    @Column(name = "cluster_name")
    private String clusterName;
    @Column(name = "cluster_desc")
    private String clusterDescription;
    @Column(name = "custer_status")
    private String clusterStatus;
    @Column(name = "cluster_country_code")
    private Integer clusterCountryCode;
    @Column(name = "cluster_region_code")
    private Integer clusterRegionCode;
}
