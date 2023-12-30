package com.mstoy.restdemo.repository;

import com.mstoy.restdemo.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, String> {//how to define repository is model name and id's datatype
    List<CloudVendor> findByVendorName(String vendorName);
}
