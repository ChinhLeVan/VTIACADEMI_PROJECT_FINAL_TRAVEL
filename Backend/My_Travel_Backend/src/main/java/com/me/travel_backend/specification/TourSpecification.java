package com.me.travel_backend.specification;

import javax.persistence.criteria.*;

import com.me.travel_backend.entity.Province;
import com.me.travel_backend.entity.Tour;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class TourSpecification implements Specification<Tour> {

	private static final long serialVersionUID = 1L;
	private SearchCriteria criteria;

	public TourSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Tour> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (criteria.getOperator().equalsIgnoreCase("Like")) {
			return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue().toString() + "%");
		}

		if (criteria.getOperator().equalsIgnoreCase("Likes") && criteria.getKey().equalsIgnoreCase("province")){
			Join<Tour, Province> join1 = root.join("province", JoinType.LEFT);
			return criteriaBuilder.like(join1.get("nameProvince"), "%" + criteria.getValue().toString() + "%");
		}

		if (criteria.getOperator().equalsIgnoreCase("Likes") && criteria.getKey().equalsIgnoreCase("location")){
			Join<Tour, Province> join1 = root.join("location", JoinType.LEFT);
			return criteriaBuilder.like(join1.get("location"), "%" + criteria.getValue().toString() + "%");
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
