package com.dizhongdi.es8;

import co.elastic.clients.elasticsearch.*;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.*;
import org.apache.http.client.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.*;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.*;
import org.elasticsearch.client.*;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.file.*;
import java.security.KeyStore;
import java.security.cert.*;

/**
 * ClassName:ESClient
 * Package:com.dizhongdi.es8
 * Description:
 *
 * @Date: 2022/7/29 22:27
 * @Author:dizhongdi
 */
public class ESClient {
    public static ElasticsearchTransport transport;
//    public static ElasticsearchAsyncClient asyncClient;
    public static ElasticsearchClient client;

    public static void main(String[] args) throws Exception {
        //初始化链接
        initESConnection();
        //操作索引
        operationIndex();
    }

    public static void operationIndex() throws Exception {
        //获取索引客户端对象
        final ElasticsearchIndicesClient indices = client.indices();
        ExistsRequest existsRequest = new ExistsRequest.Builder().index("dizhongdi").build();

        boolean flag = indices.exists(existsRequest).value();
        if (flag){
            System.out.println("索引已经存在");
        }else {
            CreateIndexRequest request = new CreateIndexRequest.Builder().index("dizhongdi").build();
            CreateIndexResponse response = indices.create(request);
            System.out.println("创建索引成功: "+response);
        }

        //查询索引
        GetIndexRequest getIndexRequest = new GetIndexRequest.Builder().index("dizhongdi").build();
        GetIndexResponse getIndexResponse = indices.get(getIndexRequest);
        System.out.println("索引查询成功：" +getIndexResponse.result());

        // 删除索引
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest.Builder().index("dizhongdi").build();
        DeleteIndexResponse deleteIndexResponse = indices.delete(deleteIndexRequest);
        System.out.println("删除索引成功：" + deleteIndexResponse.acknowledged());

    }


    public static void initESConnection() throws Exception {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "gkgdHELd2*Zb-85lEgcw"));
        Path caCertificatePath = Paths.get("es-8/target/classes/certs/java-ca.crt");
        CertificateFactory factory =
                CertificateFactory.getInstance("X.509");
        Certificate trustedCa;
        try (InputStream is = Files.newInputStream(caCertificatePath)) {
            trustedCa = factory.generateCertificate(is);
        }
        KeyStore trustStore = KeyStore.getInstance("pkcs12");
        trustStore.load(null, null);
        trustStore.setCertificateEntry("ca", trustedCa);
        SSLContextBuilder sslContextBuilder = SSLContexts.custom()
                .loadTrustMaterial(trustStore, null);
        final SSLContext sslContext = sslContextBuilder.build();
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("120.76.118.137", 9200, "https"))
                .setHttpClientConfigCallback(new
                                                     RestClientBuilder.HttpClientConfigCallback() {
                                                         @Override
                                                         public HttpAsyncClientBuilder customizeHttpClient(
                                                                 HttpAsyncClientBuilder httpClientBuilder) {
                                                             return httpClientBuilder.setSSLContext(sslContext)
                                                                     .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                                                     .setDefaultCredentialsProvider(credentialsProvider);
                                                         }
                                                     });
        RestClient restClient = builder.build();
        transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        //同步
        client = new ElasticsearchClient(transport);
        //异步
//        asyncClient = new ElasticsearchAsyncClient(transport);
//        transport.close();
    }

}
