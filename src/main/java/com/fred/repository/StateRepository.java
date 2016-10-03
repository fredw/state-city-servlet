package com.fred.repository;

import com.fred.database.ConnectionManager;
import com.fred.domain.StateDomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StateRepository {

    public List<StateDomain> findAll() throws SQLException {
        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "select * from state";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();
                List<StateDomain> result = new ArrayList<>();
                while (rs.next()) {
                    StateDomain state = new StateDomain();
                    this.setDomainFields(state, rs);
                    result.add(state);
                }
                return result;
            }
        }
    }

    public StateDomain get(int id) throws SQLException {
        StateDomain state = null;
        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "select * from state where id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    state = new StateDomain();
                    this.setDomainFields(state, rs);
                }
            }
        }
        return state;
    }

    private void setDomainFields(StateDomain state, ResultSet rs) throws SQLException {
        state.setId(rs.getInt("id"));
        state.setName(rs.getString("name"));
    }

    public void save(StateDomain state) throws SQLException {
        if (state.getId() == null) {
            this.insert(state);
        } else {
            this.update(state);
        }
    }

    private void insert(StateDomain state) throws SQLException {
        try(Connection con = ConnectionManager.getConnection()) {
            String sql = "insert into state (name) values (?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, state.getName());
                pst.executeUpdate();
            }
        }
    }

    private void update(StateDomain state) throws SQLException {
        try(Connection con = ConnectionManager.getConnection()) {
            String sql = "update state set name = ? where id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, state.getName());
                pst.setInt(2, state.getId());
                pst.executeUpdate();
            }
        }
    }

    public void delete(StateDomain state) throws SQLException {
        try(Connection con = ConnectionManager.getConnection()) {
            String sql = "delete from state where id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, state.getId());
                pst.executeUpdate();
            }
        }
    }
}
