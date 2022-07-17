package com.dizhongdi.esspringboot.es;


import com.dizhongdi.esspringboot.es.entiy.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
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
public class ESTest_Doc_Insert_Beatch {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 新增文档 - 请求对象
        BulkRequest request = new BulkRequest();

        // 设置索引及唯一性标识
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON,"name","zhangsan"));
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON,"name","lisi"));
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON,"name","wangwu"));

        // 客户端发送请求，获取响应对象
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getItems());
        System.out.println(response.getTook());

        //关闭链接
        client.close();

    }
}
