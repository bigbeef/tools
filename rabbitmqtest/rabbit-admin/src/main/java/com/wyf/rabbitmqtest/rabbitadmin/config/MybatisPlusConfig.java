package com.wyf.rabbitmqtest.rabbitadmin.config;

import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * MybatisPlus配置
 *EnableTransactionManagement由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后
 * @author wangcw
 * @Date 2017/5/20 21:58
 */
@Configuration
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = {"com.wyf.rabbitmqtest.rabbitadmin.mapper"})
public class MybatisPlusConfig {
    
    
    /***
	 * plus 的性能能优化
	 * @return
     */
	@Bean
	@ConditionalOnProperty(prefix = "spring.profiles",value = "active",havingValue = "dev")
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor=new PerformanceInterceptor();
		/*<!-- SQL 执行性能分析，开发环境使用，线上不推荐 maxTime 指的 sql 大执行时? -->*/
		performanceInterceptor.setMaxTime(10000);
		/*<!--SQL是否格式�? 默认false-->*/
		performanceInterceptor.setFormat(true);
		return performanceInterceptor;
	}

	/**
	 *	 mybatis-plus分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");
		return page;
	}
    

	/**
	 *
	 * @return
	 */

	@Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
