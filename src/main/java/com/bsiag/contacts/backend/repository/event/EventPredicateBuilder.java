package com.bsiag.contacts.backend.repository.event;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.bsiag.contacts.backend.repository.CustomCriteria;
import com.mysema.query.types.expr.BooleanExpression;

public class EventPredicateBuilder {
	final static Logger logger = Logger.getLogger(EventPredicateBuilder.class);
	
    private List<CustomCriteria> params;
    
    public EventPredicateBuilder() {
        params = new ArrayList<CustomCriteria>();
    }
 
    public EventPredicateBuilder(String criteria) {
        params = new ArrayList<CustomCriteria>();

		logger.info("event predicate criteria='" + criteria + "'");
		
		if (criteria != null) {
			Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
			Matcher matcher = pattern.matcher(criteria + ",");

			while (matcher.find()) {
				String key = matcher.group(1);
				String op  = matcher.group(2);
				String val = matcher.group(3);

				logger.info("adding [key='" + key + " op='" + op + "' val='" + val + "']");

				with(key, op, val);
			}
		}
    }
 
    public EventPredicateBuilder with(String key, String operation, Object value) {
        params.add(new CustomCriteria(key, operation, value));
        return this;
    }
 
    public BooleanExpression predicates() {
        if (params.size() == 0) {
            return null;
        }
 
        List<BooleanExpression> predicates = new ArrayList<BooleanExpression>();
        EventPredicate predicate;
        
        for (CustomCriteria param : params) {
            predicate = new EventPredicate(param);
            BooleanExpression exp = predicate.getPredicate();
            if (exp != null) {
                predicates.add(exp);
            }
        }
 
        BooleanExpression result = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        
        return result;
    }
}
