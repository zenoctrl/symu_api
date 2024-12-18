package com.example.symu_api.CLUSTER.Controller;

import com.example.symu_api.CLUSTER.Entiry.ClusterEntity;
import com.example.symu_api.CLUSTER.Service.ClusterService;
import com.example.symu_api.COMMON.Model.SymuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/symu/cluster")
public class ClusterController {
    @Autowired
    private ClusterService clusterService;

    @PostMapping("/createOrUpdateCluster")
    public SymuResponse createOrUpdateCluster(@RequestBody ClusterEntity clusterEntity) {
        return clusterService.createOrUpdateCluster(clusterEntity);
    }
    @GetMapping(path = "/getAllClusters",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllClusters(
            @RequestParam("countryCode")int countryCode
    ) {
        final SymuResponse cluster =clusterService.getAllClusters(countryCode);
        return ResponseEntity.ok(cluster);
    }
    @GetMapping(path = "/getClusterByCode",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getClusterByCode(
            @RequestParam("clusterCode")int clusterCode
    ) {
        final SymuResponse cluster =clusterService.getClusterByCode(clusterCode);
        return ResponseEntity.ok(cluster);
    }
    @GetMapping(path = "/getAllClustersByBranch",
            produces = {"application/json; charset=UTF-8"})
    public ResponseEntity<SymuResponse> getAllClustersByBranch(
            @RequestParam("clusterBranchCode")int clusterBranchCode
    ) {
        final SymuResponse cluster =clusterService.getAllClustersByBranch(clusterBranchCode);
        return ResponseEntity.ok(cluster);
    }
}
