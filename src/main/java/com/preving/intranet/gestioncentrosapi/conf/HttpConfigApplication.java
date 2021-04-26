package com.preving.intranet.gestioncentrosapi.conf;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.security.KeyStore;

@Configuration
public class HttpConfigApplication {
    @Value(value = "${api-comunicacion.keystore")
    private String keyStoreFileComm = "";

    @Value(value = "${api-comunicacion.keystore-password}")
    private String keyStorePasswordComm = "";

    @Bean(name = "restTemplateCommunication")
    public RestTemplate restTemplate() throws Exception{
        RestTemplate restTemplate = new RestTemplate();

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(this.getClass().getClassLoader().getResourceAsStream(keyStoreFileComm), keyStorePasswordComm.toCharArray());

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                new SSLContextBuilder()
                        .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                        .loadKeyMaterial(keyStore, keyStorePasswordComm.toCharArray())
                        .build(),
                NoopHostnameVerifier.INSTANCE);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(requestFactory);

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return restTemplate;
    }
}
