package com.dizhongdi.esspringboot.es;


import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * ClassName:Elasticsearch_Client
 * Package:com.dizhongdi.esspringboot.es
 * Description:
 *
 * @Date: 2022/7/17 20:11
 * @Author:dizhongdi
 */
public class ESTest_Doc_Get {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        GetRequest request = new GetRequest().index("user").id("1001");
        // 客户端发送请求，获取响应对象
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getId());
        System.out.println(response.getIndex());
        System.out.println(response.getType());
        System.out.println(response.getSourceAsString());
        //关闭链接
        client.close();

    }
}
