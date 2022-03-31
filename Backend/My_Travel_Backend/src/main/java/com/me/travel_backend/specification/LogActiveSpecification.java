package com.me.travel_backend.specification;

import com.me.travel_backend.entity.LogActive;
import com.me.travel_backend.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

public class LogActiveSpecification implements Specification<LogActive> {

	private static final long serialVersionUID = 1L;
	private SearchCriteria criteria;

	public LogActiveSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<LogActive> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (criteria.getOperator().equalsIgnoreCase("Like") && criteria.getKey().equalsIgnoreCase("userName")){
			Join<LogActive, User> join1 = root.join("user", JoinType.LEFT);
			return criteriaBuilder.like(join1.get("userName"), "%" + criteria.getValue().toString() + "%");
		}

		if (criteria.getOperator().equalsIgnoreCase("Date>=")) {
			return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Date) criteria.getValue());
		}

		if (criteria.getOperator().equalsIgnoreCase("Date<=")) {
			return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), (Date) criteria.getValue());
		}
		return null;
	}

}
