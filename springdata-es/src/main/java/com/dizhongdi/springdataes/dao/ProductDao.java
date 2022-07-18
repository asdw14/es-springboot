package com.dizhongdi.springdataes.dao;

import com.dizhongdi.springdataes.entiy.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName:ProductDao
 * Package:com.dizhongdi.springdataes.dao
 * Description:
 *
 * @Date: 2022/7/18 22:57
 * @Author:dizhongdi
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}