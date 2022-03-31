package com.me.travel_backend.specification;
import com.me.travel_backend.entity.LogActive;
import com.me.travel_backend.entity.Search.LogActiveSearch;
import com.me.travel_backend.entity.Search.TourSearch;
import com.me.travel_backend.entity.Tour;
import com.me.travel_backend.entity.filter.LogActiveFilter;
import com.me.travel_backend.entity.filter.TourFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Date;


public class LogActiveSpecificationBuilder {

	private LogActiveFilter filter;
	private LogActiveSearch search;

	public LogActiveSpecificationBuilder(LogActiveFilter filter, LogActiveSearch search) {
		this.filter = filter;
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<LogActive> build() {

		SearchCriteria searchLogActiveNameCriteria = new SearchCriteria("userName", "Like", search.getUserName());
		SearchCriteria minDateCriteria = new SearchCriteria("dateActive", "Date>=", filter.getMinDate());
		SearchCriteria maxDateCriteria = new SearchCriteria("dateActive", "Date<=", filter.getMaxDate());

		Specification<LogActive> where = null;

		// search log active by userName
		if (!StringUtils.isEmpty(search.getUserName())) {
			where = new LogActiveSpecification(searchLogActiveNameCriteria);
		}

		// min Date filter
		if (filter.getMinDate() instanceof Date) {
			if (where != null) {
				where = where.and(new LogActiveSpecification(minDateCriteria));
			} else {
				where = new LogActiveSpecification(minDateCriteria);
			}
		}

		// max Date filter
		if (filter.getMaxDate() instanceof Date) {
			if (where != null) {
				where = where.and(new LogActiveSpecification(maxDateCriteria));
			} else {
				where = new LogActiveSpecification(maxDateCriteria);
			}
		}

		return where;
	}
}
