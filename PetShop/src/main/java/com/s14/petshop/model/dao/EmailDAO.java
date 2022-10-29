package com.s14.petshop.model.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<String> getSubscribedEmails () {
        List<String> result = new ArrayList<>();

        String sql = "SELECT u.email FROM users u WHERE u.is_subscribed = 1";
        jdbcTemplate.query(sql,resultSet -> {
            result.add(resultSet.getString("email"));
            while (resultSet.next()) {
                result.add(resultSet.getString("email"));
            }
        });
        return result;
    }
}