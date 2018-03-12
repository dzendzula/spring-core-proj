package net.servodata.app.domain.commons.dao.impl;

import java.util.Map;

import com.querydsl.core.types.dsl.BooleanExpression;



/**
 * @author <a href="mailto:pospisek@servodata.net">Michal Pospisek</a>
 */
public abstract class AbstractQueryRepository {

    protected BooleanExpression appendToResult(BooleanExpression result, BooleanExpression predicate) {
        if(result == null) {
            return predicate;
        } else {
            return result.and(predicate);
        }
    }

    protected String appendCondition(String query, String condition) {
        if(query.contains(" WHERE ")) {
            return query + " AND " + condition;
        } else {
            return query + " WHERE " + condition;
        }
    }

    protected String appendKeywords(String query, String condition, String keyword, Map<String, Object> parameters) {
        String[] keywords = keyword.toLowerCase().split(Constants.KEYWORDS_SPLIT_PATTERN);
        StringBuilder builder = new StringBuilder("to_tsquery(");
        boolean first = true;
        for (int i = 0; i < keywords.length; i++) {
            if (!first) {
                builder.append(" || ' & ' || ");
            } else {
                first = false;
            }
            builder.append("unaccent(:keyword");
            builder.append(i);
            builder.append(")");
            parameters.put("keyword" + i, keywords[i] + ":*");
        }
        builder.append(") query");
        query += ", " + builder.toString();
        return appendCondition(query, condition + " @@ query");
    }
}
