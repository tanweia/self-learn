package com.serius.learn.mongo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * create by serius on Jun 25, 2017
 * db操作全局接口定义
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID>{
	
	/**
	 * 根据条件查询document列表
	 * @param query
	 * @return
	 */
	List<T> queryList(Query query);
	
	/**
	 * 根据条件统计document总数
	 * @param query
	 * @return
	 */
	long count(Query query);
	
	/**
	 * 单document更新
	 * @param query
	 * @param update
	 * @return
	 */
	boolean updateFirst(Query query, Update update);
	
	
	/**
	 * 多document更新
	 * @param query
	 * @param update
	 * @return
	 */
	boolean updateMulti(Query query, Update update);
	
	/**
	 * 判断是否存在
	 * @param query
	 * @return
	 */
	boolean exists(Query query);
	
	/**
	 * 删除多个document
	 * @param query
	 * @return
	 */
	boolean delete(Query query);
	
}
