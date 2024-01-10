package com.mstoy.restdemo.service.impl;

import com.mstoy.restdemo.exception.CloudVendorNotFoundException;
import com.mstoy.restdemo.model.CloudVendor;
import com.mstoy.restdemo.repository.CloudVendorRepository;
import com.mstoy.restdemo.service.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorServiceImpl implements CloudVendorService{
    CloudVendorRepository cloudVendorRepository;

    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        //more Business logic
        cloudVendorRepository.save(cloudVendor);
        return "Success";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        //more Business logic
        cloudVendorRepository.save(cloudVendor);//jpa의 save 메서드의 경우, 이미 있는 값에 대해서는 변경된 내용만 추적해서 수정합니다
        return "Success";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        //more Business logic
        cloudVendorRepository.deleteById(cloudVendorId);
        return "Success";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        //more Business logic
        if(cloudVendorRepository.findById(cloudVendorId).isEmpty())
            throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
        return cloudVendorRepository.findById(cloudVendorId).get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        //more Business logic
        return cloudVendorRepository.findAll();
    }//이 안에 있는 모든 메서드를 implements 하기 전에는 빨간줄이 떠있음

    @Override
    public List<CloudVendor> getByVendorName(String vendorName){
        return cloudVendorRepository.findByVendorName(vendorName);
    }
}
