package com.plgrim.ncp.interfaces.connection;

import com.plgrim.ncp.framework.systems.Stage;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class NCPConnectionConfig {
    @Autowired
    Environment env;

    @Bean(name = "sampleConnection")
    public NCPURLConnection createSampleConnection() throws Exception {
        NCPConnectionFactory factory = new NCPConnectionFactory();

        String url = env.getProperty("ncp_if_internal_server.sample.server.url");
        String connectTimeout = env.getProperty("ncp_if_internal_server.sample.connection.timeout");
        String readTimeout = env.getProperty("ncp_if_internal_server.sample.read.timeout");

        factory.setUrl(url);
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);

        return factory.getObject();
    }

    @Bean(name = "erpConnection")
    public NCPURLConnection createErpConnection() throws Exception {
        NCPConnectionFactory factory = new NCPConnectionFactory();

        String url = env.getProperty("ncp_if_internal_server.erp.server.url");
        String connectTimeout = env.getProperty("ncp_base.interface.http.connection.timeout");
        String readTimeout = env.getProperty("ncp_base.interface.http.read.timeout");
        String host = env.getProperty("ncp_if_internal_server.erp.host");
        String apiKey = env.getProperty("ncp_if_internal_server.erp.x_api_key");

        factory.setUrl(url);
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);

        Stage stage = Stage.decideStage(env);
        List<ImmutablePair<String, String>> pairList = new ArrayList<>();
        if (Stage.LOCAL == stage) {
            pairList.add(new ImmutablePair<>("x-api-key", apiKey));
        } else {
            pairList.add(new ImmutablePair<>("Host", host));
        }

        factory.setKeyPiar(pairList);

        return factory.getObject();
    }

}
