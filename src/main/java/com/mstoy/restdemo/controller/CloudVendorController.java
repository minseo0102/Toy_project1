package com.mstoy.restdemo.controller;

import com.mstoy.restdemo.model.CloudVendor;
import com.mstoy.restdemo.service.CloudVendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {
    CloudVendorService cloudVendorService;

    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    //Read Specific Cloud Vendor Details
    @GetMapping("{vendorId}")
    public CloudVendor getCloudVendorDetails(@PathVariable("vendorId") String vendorId)//위 url 경로로부터 값을 받아와서 사용하므로 어노테이션 추가
    {
        return cloudVendorService.getCloudVendor(vendorId);
    }

    //Read All Cloud Vendor Details
    @GetMapping
    public List<CloudVendor> getCloudVendorDetails()//위 url 경로로부터 값을 받아와서 사용하므로 어노테이션 추가
    {
        return cloudVendorService.getAllCloudVendors();
    }

    //Create Cloud Vendor
    @PostMapping
    public String postCloudVendorDetails(@RequestBody CloudVendor cloudVendor)
    {
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor Created Successfully";
    }

    //Update Cloud Vendor
    @PutMapping//수정이라 하더라도 특정 id 안받아도됨-> jpa의 save 메서드가 이미 있는 값에 대해서는 변경을 하고, 없으면 삽입을 하는 두가지 역할을 수행하기 때문
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor)
    {
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "Cloud Vendor Updated Successfully";
    }

    @DeleteMapping("{VendorId}")
    public String deleteCloudVendorDetails(@PathVariable("VendorId") String vendorId)
    {
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Cloud Vendor Deleted Successfully";
    }
}
