package com.plgrim.ncp.framework.filter.geoip;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;
import com.plgrim.ncp.framework.TestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@ActiveProfiles("local")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class GeoIpTest {
    @Autowired
    ApplicationContext ctx;

//    @Autowired
//    private CloudFileSystem cloudFileSystem;

    private static String bucketName = "dev-static-online-mall";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGeoIp() throws Exception {

        /**
         * GeoIP Database resource 가 없을 경우 재설정
         */
//        Resource resource = ctx.getResource("classpath:/GeoLite2-Country.mmdb");

        Resource resource = new ClassPathResource("GeoLite2-Country.mmdb");

        String isoCode = "EN";

        /**
         * Session 에 client PC의 ISO_CODE 가 없을 경우 IP를 사용하여 재설정
         */
        try (DatabaseReader reader = new DatabaseReader.Builder(resource.getInputStream()).build()) {
//            InetAddress ipAddress = InetAddress.getByName("206.190.36.45");   // 미국
//            InetAddress ipAddress = InetAddress.getByName("45.112.188.55");   // 중국
            InetAddress ipAddress = InetAddress.getByName("14.198.224.44");   // 홍콩
//            InetAddress ipAddress = InetAddress.getByName("43.224.248.66");     // 대만


            CountryResponse cityResponse = reader.country(ipAddress);
            isoCode = cityResponse.getCountry().getIsoCode();

            /**
             * 홍콩의 경우 ISO_CODE 및 언어코드 모두 중국으로 설정
             */
            if ("HK".equals(isoCode)) {
                isoCode = "CN";
            }

        } catch (Exception e) {
            log.error("{} getCountry Fail {}", this.getClass().getName(), e);
        }

        log.info("iso code : {}", isoCode);
        log.info("resource : {}", resource);


    }

}
