package com.dizhongdi.esspringboot.es;


import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * ClassName:Elasticsearch_Client
 * Package:com.dizhongdi.esspringboot.es
 * Description:
 *
 * @Date: 2022/7/17 20:11
 * @Author:dizhongdi
 */
public class ESTest_Doc_Detete_batch {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("1002"));
        request.add(new DeleteRequest().index("user").id("1003"));
        // 客户端发送请求，获取响应对象
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());

        //关闭链接
        client.close();

    }
}
