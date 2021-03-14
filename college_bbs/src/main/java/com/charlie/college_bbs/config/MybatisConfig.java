package com.charlie.college_bbs.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.charlie.college_bbs.dao")
public class MybatisConfig {

}
