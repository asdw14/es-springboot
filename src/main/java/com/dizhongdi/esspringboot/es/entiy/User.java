package com.dizhongdi.esspringboot.es.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ClassName:User
 * Package:com.dizhongdi.esspringboot.es.entiy
 * Description:
 *
 * @Date: 2022/7/17 21:51
 * @Author:dizhongdi
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class User {
    private String name;
    private Integer age;
    private String sex;
}
