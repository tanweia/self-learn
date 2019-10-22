package com.serius.learn.mongo.repository.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import com.mongodb.WriteResult;
import com.serius.learn.mongo.repository.BaseRepository;

/**
 * create by serius on Jun 25, 2017
 * db操作全局实现
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements BaseRepository<T, ID> {

	private final MongoOperations mongoOperations;
	protected final MongoEntityInformation<T, ID> entityInformation;
	private Class<T> entityClass;

	public BaseRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
		this.mongoOperations = mongoOperations;
		this.entityInformation = metadata;
		this.entityClass = metadata.getJavaType();
	}

	@Override
	public boolean updateFirst(Query query, Update update) {
		WriteResult writeResult = mongoOperations.updateFirst(query, update, entityClass);
		if (writeResult.wasAcknowledged() && writeResult.getN() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateMulti(Query query, Update update) {
		WriteResult writeResult = mongoOperations.updateMulti(query, update, entityClass);
		if (writeResult.wasAcknowledged() && writeResult.getN() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<T> queryList(Query query) {
		List<T> list = mongoOperations.find(query, entityClass);
		return list;
	}

	@Override
	public long count(Query query) {
		long total = mongoOperations.count(query, entityClass);
		return total;
	}

	@Override
	public boolean exists(Query query) {
		return mongoOperations.exists(query, entityClass);
	}

	@Override
	public boolean delete(Query query) {
		WriteResult writeResult = mongoOperations.remove(query, entityClass);
		if (writeResult.wasAcknowledged() && writeResult.getN() >= 1) {
			return true;
		}
		return false;
	}

}
