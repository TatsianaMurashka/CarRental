package com.htp.dao.jdbctemplate;

import com.htp.dao.RoleDao;
import com.htp.domain.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository implements RoleDao {

    public static final String ROLE_ID = "id";
    public static final String ROLE_NAME = "role_name";
    public static final String USER_ID = "user_id";

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RoleRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Role> findAll() {
        final String findAllQuery = "select * from m_roles order by id desc";
        return jdbcTemplate.query(findAllQuery, this::roleRowMapper);
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        final String searchQuery = "select * from m_roles where user_id = ? order by role_name";
        return jdbcTemplate.query(searchQuery, this::roleRowMapper, userId);
    }


    @Override
    public Optional<Role> findByLogin(String username) {
        return Optional.empty();
    }

    @Override
    public Role findOne(Long id) {
        final String findById = "select * from m_roles where id = ?";
        return jdbcTemplate.queryForObject(findById, this::roleRowMapper, id);
    }

    @Override
    public List<Role> search(String searchParam) {
        return null;
    }

    @Override
    public Role save(Role role) {
        final String insertQuery = "insert into m_roles (role_name, user_id)\n" +
                " values (:role_name, :user_id)";
        final String findLastIdQuery = "SELECT currval('m_roles_id_seq') as last_insert_id";

        namedParameterJdbcTemplate.update(insertQuery, new MapSqlParameterSource("role_name", role.getRoleName())
                .addValue("user_id", role.getUserId()));

        long lastId = jdbcTemplate.queryForObject(findLastIdQuery, Long.class);

        return findOne(lastId);
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    private Role roleRowMapper(ResultSet resultSet, int i) throws SQLException {
        return Role.builder()
                .id(resultSet.getLong(ROLE_ID))
                .userId(resultSet.getLong(USER_ID))
                .roleName(resultSet.getString(ROLE_NAME))
                .build();
    }
}
