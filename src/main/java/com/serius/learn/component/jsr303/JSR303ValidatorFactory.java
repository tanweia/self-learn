package com.serius.learn.component.jsr303;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * Created by serius on Mar 9, 2018
 * 获取Validator，保证单例
 */
public enum JSR303ValidatorFactory {

	INSTANCE {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

		@Override
		public Validator getValidator() {
			return factory.getValidator();
		}
	};

	public abstract Validator getValidator();


}
