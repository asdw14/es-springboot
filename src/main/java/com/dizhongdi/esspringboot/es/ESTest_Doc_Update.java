package com.dizhongdi.esspringboot.es;


import com.dizhongdi.esspringboot.es.entiy.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
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
public class ESTest_Doc_Update {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 修改文档 - 请求对象
        UpdateRequest request = new UpdateRequest();
        // 配置修改参数
        request.index("user").id("1001");
        request.doc(XContentType.JSON,"sex","女");
        // 客户端发送请求，获取响应对象
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getId());
        System.out.println(response.getIndex());
        System.out.println(response.getResult());

        //关闭链接
        client.close();

    }
}
