package com.dizhongdi.esspringboot.es;


import com.dizhongdi.esspringboot.es.entiy.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
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
public class ESTest_Doc_Insert {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 新增文档 - 请求对象
        IndexRequest request = new IndexRequest();

        // 设置索引及唯一性标识
        request.index("user").id("1001");
        // 创建数据对象
        User user = new User("zhangsan", 30, "男");
        String valueAsString = new ObjectMapper().writeValueAsString(user);
        // 添加文档数据，数据格式为 JSON 格式
        request.source(valueAsString, XContentType.JSON);
        // 客户端发送请求，获取响应对象
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getId());
        System.out.println(response.getIndex());
        System.out.println(response.getResult());

        //关闭链接
        client.close();

    }
}
