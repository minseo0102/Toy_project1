package com.mstoy.restdemo.repository;

import com.mstoy.restdemo.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CloudVendorRepositoryTest {
    //테스트 작업을 할 때, 모든 초기화 작업을 수행하는 것이 모범
    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;

    //설정 메서드
    @BeforeEach
    void setUp(){
        //cloud vendor를 인스턴스화 한다. cloud vendor 객체를 생성
        cloudVendor = new CloudVendor("1","Amazon",
                "USA","xxxxx");
        cloudVendorRepository.save(cloudVendor);//메모리 데이터 베이스에 저장
    }

    //해제 메서드
    @AfterEach
    void tearDown(){
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }

    //TEST case SUCCESS
    @Test//테스트 케이스를 사용할 때마다 쓰는 어노테이션 = 이 표시가 있는 구간마다 개별 실행가능, 옆에 재생표시
    void testFindByVendorName_Found(){ //성공한 테스트 사례
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Amazon");
        //실제로 예상되는 값을 반환하는지 확인하는 단계
        assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
        assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }
    //TEST case FAILURE
    //데이터베이스에 존재하지 않는 공급업체 이름을 찾으려고 하는 실패 사례에 대한 테스트
    //현재는 내장 db에 위에서 생성해준 Amazon만 들어가 있는 상태
    @Test//이 어노테이션을 씀으로써 아래 메서드는 일반 메서드가 아니라, 테스트 케이스라는 것을 식별가능
    void testFindByVendorName_NotFound(){ //성공한 테스트 사례
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("GCP");
        //아무것도 반환되지 않아야 함.
        assertThat(cloudVendorList.isEmpty()).isTrue();//이 assertion이 통과하면 리스트가 비어있다는 것

    }
}
