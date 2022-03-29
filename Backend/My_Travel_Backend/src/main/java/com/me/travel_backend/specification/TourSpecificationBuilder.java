package com.me.travel_backend.specification;

import com.me.travel_backend.entity.Search.TourSearch;
import com.me.travel_backend.entity.Tour;
import com.me.travel_backend.entity.filter.TourFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Date;


public class TourSpecificationBuilder {

	private TourFilter filter;
	private TourSearch search;

	public TourSpecificationBuilder(TourFilter filter, TourSearch search) {
		this.filter = filter;
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Tour> build() {

		SearchCriteria searchTourNameCriteria = new SearchCriteria("tourName", "Like", search.getTourName());
		SearchCriteria minDateCriteria = new SearchCriteria("dayStart", "Date>=", filter.getMinDate());
		SearchCriteria maxDateCriteria = new SearchCriteria("dayEnd", "Date<=", filter.getMaxDate());

		Specification<Tour> where = null;

		// search Tour Name
		if (!StringUtils.isEmpty(search.getTourName())) {
			where = new TourSpecification(searchTourNameCriteria);
		}

		// min Date filter
		if (filter.getMinDate() instanceof Date) {
			if (where != null) {
				where = where.and(new TourSpecification(minDateCriteria));
			} else {
				where = new TourSpecification(minDateCriteria);
			}
		}

		// max Date filter
		if (filter.getMaxDate() instanceof Date) {
			if (where != null) {
				where = where.and(new TourSpecification(maxDateCriteria));
			} else {
				where = new TourSpecification(maxDateCriteria);
			}
		}

		return where;
	}
}
