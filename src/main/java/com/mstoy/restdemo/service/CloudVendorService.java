package com.mstoy.restdemo.service;

import com.mstoy.restdemo.model.CloudVendor;

import java.util.List;

public interface CloudVendorService {//인터페이스로 정의해서 서비스 구현체에 직접 의존하지 않고 인터페이스에 의존하게 하여 결합도를 낮춤
    public String createCloudVendor(CloudVendor cloudVendor);
    public String updateCloudVendor(CloudVendor cloudVendor);
    public String deleteCloudVendor(String cloudVendorId);
    public CloudVendor getCloudVendor(String cloudVendorId);
    public List<CloudVendor> getAllCloudVendors();
    public List<CloudVendor> getByVendorName(String vendorName);
}
