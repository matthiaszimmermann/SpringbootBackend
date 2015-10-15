package com.bsiag.contacts.backend.repository.event;

import com.bsiag.contacts.backend.repository.CustomCriteria;
import com.bsiag.contacts.model.Event;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventPredicate {
	private CustomCriteria criteria;

	public BooleanExpression getPredicate() {
		PathBuilder<Event> entityPath = new PathBuilder<Event>(Event.class, "event");

		if (isNumeric(criteria.getValue().toString())) {
			NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
			int value = Integer.parseInt(criteria.getValue().toString());

			if (criteria.getOperation().equalsIgnoreCase(":")) {
				return path.eq(value);
			} 
			else if (criteria.getOperation().equalsIgnoreCase(">")) {
				return path.goe(value);
			} 
			else if (criteria.getOperation().equalsIgnoreCase("<")) {
				return path.loe(value);
			}
		} 
		else {
			StringPath path = entityPath.getString(criteria.getKey());

			if (criteria.getOperation().equalsIgnoreCase(":")) {
				return path.containsIgnoreCase(criteria.getValue().toString());
			}
		}

		return null;
	}

	public static boolean isNumeric(final String str) {
		try {
			Integer.parseInt(str);
		} catch (final NumberFormatException e) {
			return false;
		}
		return true;
	}    
}
