package com.example.demo.src.user.handler;

import com.example.demo.src.user.model.Role;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Role.class)
public class RoleTypeHandler implements TypeHandler<Role> {
    @Override // 지정된 타입의 어떤 값을 db에 넣을 것인지
    public void setParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override // 칼럼이름 기반으로 값 조회 -> 실제 반환할 객체 구성
    public Role getResult(ResultSet rs, String columnName) throws SQLException {
        String roleKey = rs.getString(columnName);
        return getRole(roleKey);
    }

    @Override // 인덱스 기반
    public Role getResult(ResultSet rs, int columnIndex) throws SQLException {
        String roleKey = rs.getString(columnIndex);
        return getRole(roleKey);
    }

    @Override
    public Role getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String roleKey = cs.getString(columnIndex);
        return getRole(roleKey);
    }

    private Role getRole(String roleKey) {
        Role role = null;
        switch (roleKey) {
            case "ROLE_MANAGER":
                role = Role.MANAGER;
                break;
            default:
                role = Role.USER;
                break;
        }
        return role;
    }
}
