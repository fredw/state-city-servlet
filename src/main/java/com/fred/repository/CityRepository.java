package com.fred.repository;

import com.fred.database.ConnectionManager;
import com.fred.domain.CityDomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {

    public List<CityDomain> findAll() throws SQLException {
        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "select * from city";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();
                List<CityDomain> result = new ArrayList<>();
                while (rs.next()) {
                    CityDomain city = new CityDomain();
                    this.setDomainFields(city, rs);
                    result.add(city);
                }
                return result;
            }
        }
    }

    public CityDomain get(int id) throws SQLException {
        CityDomain city = null;
        try (Connection con = ConnectionManager.getConnection()) {
            String sql = "select * from city where id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    city = new CityDomain();
                    this.setDomainFields(city, rs);
                }
            }
        }
        return city;
    }

    private void setDomainFields(CityDomain city, ResultSet rs) throws SQLException {
        StateRepository stateRepository = new StateRepository();
        city.setId(rs.getInt("id"));
        city.setName(rs.getString("name"));
        city.setState(stateRepository.get(rs.getInt("state")));
    }

    public void save(CityDomain city) throws SQLException {
        if (city.getId() == null) {
            this.insert(city);
        } else {
            this.update(city);
        }
    }

    private void insert(CityDomain city) throws SQLException {
        try(Connection con = ConnectionManager.getConnection()) {
            String sql = "insert into city (name, state) values (?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, city.getName());
                pst.setInt(2, city.getState().getId());
                pst.executeUpdate();
            }
        }
    }

    private void update(CityDomain city) throws SQLException {
        try(Connection con = ConnectionManager.getConnection()) {
            String sql = "update city set name = ?, state = ? where id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, city.getName());
                pst.setInt(2, city.getState().getId());
                pst.setInt(3, city.getId());
                pst.executeUpdate();
            }
        }
    }

    public void delete(CityDomain city) throws SQLException {
        try(Connection con = ConnectionManager.getConnection()) {
            String sql = "delete from city where id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, city.getId());
                pst.executeUpdate();
            }
        }
    }
}
