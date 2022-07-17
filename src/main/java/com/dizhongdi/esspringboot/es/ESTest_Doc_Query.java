package com.dizhongdi.esspringboot.es;


import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * ClassName:Elasticsearch_Client
 * Package:com.dizhongdi.esspringboot.es
 * Description:
 *
 * @Date: 2022/7/17 20:11
 * @Author:dizhongdi
 */
public class ESTest_Doc_Query {
    public static void main(String[] args) throws IOException {
        // 创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 构建查询的请求体
//        SearchRequest request = new SearchRequest();
//        request.indices("student");
//        // 查询所有数据
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        request.source(searchSourceBuilder);

// 创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices("student");
//// 构建查询的请求体
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.query(QueryBuilders.termQuery("name", "asdw14"));
//        request.source(sourceBuilder);
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);




        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // 分页查询
//        // 当前页其实索引(第一条数据的顺序号)，from
//        builder.from(0);
//        builder.size(2);

        // 排序
//        builder.sort("age", SortOrder.ASC);
//        request.source(builder);


        //查询字段过滤
//        String[] excludes = {};
//        String[] includes = {"name", "age"};
//        builder.fetchSource(includes, excludes);

        //bool查询
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.matchQuery("age",30));
//        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name","zhangsan"));
//        boolQueryBuilder.should(QueryBuilders.matchQuery("sex","男"));
//        builder.query(boolQueryBuilder);


//        范围查询
//        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        // 大于等于
//        rangeQuery.gte(31).lte(40);
//        builder.query(rangeQuery);
//

        //8.模糊查询
//        builder.query(QueryBuilders.fuzzyQuery("name","lisi").fuzziness(Fuzziness.TWO));

        // 9.高亮查询
        //构建查询方式：高亮查询
//        builder.query(QueryBuilders.termQuery("name","lisi"));
//        //构建高亮字段
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("name");//设置高亮字段
//        highlightBuilder.preTags("<font color='red'>"); //设置标签前缀
//        highlightBuilder.postTags("</font>");
//        //设置高亮构建对象
//        builder.highlighter(highlightBuilder);

        //10.聚合查询
        builder.aggregation(AggregationBuilders.max("maxAge").field("age"));

        //11. 分组统计
        builder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));

        request.source(builder);


        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
//输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
        System.out.println(response);
        //关闭链接
        client.close();

    }
}
