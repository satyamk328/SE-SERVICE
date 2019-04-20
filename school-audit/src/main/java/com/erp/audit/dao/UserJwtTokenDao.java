package com.erp.audit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.erp.audit.model.JwtModel;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserJwtTokenDao {

	 /** The jdbc template object. */
    private NamedParameterJdbcTemplate jdbcTemplateObject;

    @Value("${user_jwt_token.insert.sql}")
    private String selectByIdSql;

    @Value("${user_jwt_token.delete.sql}")
    private String removeJwtSql;

    @Value("${user_jwt_token.select.sql}")
    private String checkJwtSql;

    @Value("${user_jwt_token.udpate.IsValid}")
    private String setValid;

    @Value("${user_jwt_token.select.isValid.sql}")
    private String checkValid;

    public int insertJwt(final JwtModel jwt) {
    	log.info("Query run for insertJwt {}", selectByIdSql);
        final BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(jwt);
        return jdbcTemplateObject.update(selectByIdSql, params);
    }

    public int removeJwt(final JwtModel jwt) {
    	log.info("Query run for removeJwt {}", removeJwtSql);
        final MapSqlParameterSource mapParam = new MapSqlParameterSource();
        mapParam.addValue("Token", jwt.getToken());
        return jdbcTemplateObject.update(removeJwtSql, mapParam);
    }

    public boolean checkJwt(final String jwt) {
    	log.info("Query run for checkJwt {}", checkValid);
        final MapSqlParameterSource mapParam = new MapSqlParameterSource();
        mapParam.addValue("Token", jwt);
        final List<Integer> isValid = jdbcTemplateObject.queryForList(checkValid, mapParam, Integer.class);
        if (isValid.size() == 0) {
            return false;
        }
        return isValid.get(0) == 1 ? true : false;
    }

    public int updateJwtIsValid(final JwtModel jwt) {
        return 0;
    }
}
