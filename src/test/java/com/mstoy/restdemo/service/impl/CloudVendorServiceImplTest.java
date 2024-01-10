package com.mstoy.restdemo.service.impl;

import com.mstoy.restdemo.model.CloudVendor;
import com.mstoy.restdemo.repository.CloudVendorRepository;
import com.mstoy.restdemo.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class CloudVendorServiceImplTest {
//서비스 계층이 데이터베이스와 직접적으로 통신해서는 안되기 때문에, 데이터베이스 응답을
    //얻으려면 repository layer를 mocking해야 함.=> mock object 모의 객체 사용
    @Mock
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorService cloudVendorService;
    AutoCloseable autoCloseable;//자동 종료가능 인스턴스
    CloudVendor cloudVendor;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        //전체 클래스에 대한 모의가 여기에서 열림
        //이 클래스의 실행이 완료되는 순간 모든 리소스를 닫는다=모든 리소스를 즉시 해제
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor("1", "Amazon",
                "USA", "xxxxx");
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);//저장이 성공한 것처럼 보이도록 설정
        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Success");
        //앞서 설정한 cloudVendorRepository.save 메서드 호출이 성공하도록 설정되어 있기 때문에,
        // createCloudVendor 메서드가 "Success"를 반환하면 테스트가 통과
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);//저장이 성공한 것처럼 보이도록 설정
        assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("Success");
    }

    @Test
    void testDeleteCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository).deleteById(any());
        //doAnswer는 특정 메서드에 대한 호출을 가로채고, 지정된 동작을 수행하도록 하는 Mockito의 메서드
        assertThat(cloudVendorService.deleteCloudVendor("1")).isEqualTo("Success");
    }

    @Test
    void testGetCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        //find 결과가 있을수도 있고, 없을 수도 있으므로 옵션으로 nullable을 설정한다.
        assertThat(cloudVendorService.getCloudVendor("1").getVendorName())
                .isEqualTo(cloudVendor.getVendorName());
    }

    //testGetByVendorName 나중에 추가할 것, 실제 main의 service에도 메서드를 추가하고 test를 수행할것 =>OK!!!
    @Test
    void testGetByVendorName(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findByVendorName("Amazon")).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor))
        );

        assertThat(cloudVendorService.getByVendorName("Amazon").get(0).getVendorId())
                .isEqualTo(cloudVendor.getVendorId());
    }
    @Test
    void testGetAllCloudVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findAll()).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor))
        );

        assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorPhoneNumber())
                .isEqualTo(cloudVendor.getVendorPhoneNumber());
    }
}