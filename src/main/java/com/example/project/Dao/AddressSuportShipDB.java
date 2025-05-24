package com.example.project.Dao;

import com.example.project.Model.District;
import com.example.project.Model.Province;
import com.example.project.Model.Street;
import com.example.project.Model.Ward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressSuportShipDB {
    Dao dao = new Dao();
    Connection connection = dao.getConn();

    
    public void close(){
        dao.closeConnection(connection);
    }
    public List<Province> getAllProvinces() {
        List<Province> provinces = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT ProvinceID, ProvinceName FROM SupportShipProvinces");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                provinces.add(new Province(
                        rs.getInt("ProvinceID"),
                        rs.getString("ProvinceName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provinces;
    }
    public List<District> getDistrictsByProvinceId(int provinceId) {
        List<District> districts = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT DistrictID, DistrictName FROM SupportShipDistricts WHERE ProvinceID = ?");
            ps.setInt(1, provinceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                districts.add(new District(
                        rs.getInt("DistrictID"),
                        rs.getString("DistrictName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return districts;
    }
    public List<Ward> getWardsByDistrictId(int districtId) {
        List<Ward> wards = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT WardID, WardName FROM SupportShipWards WHERE DistrictID = ?");
            ps.setInt(1, districtId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                wards.add(new Ward(
                        rs.getInt("WardID"),
                        rs.getString("WardName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wards;
    }

    public List<Street> getStreetsByWardId(int wardId) {
        List<Street> streets = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT StreetID, StreetName FROM SupportShipStreets WHERE WardID = ?");
            ps.setInt(1, wardId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                streets.add(new Street(
                        rs.getInt("StreetID"),
                        rs.getString("StreetName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streets;
    }

    public String getNameProvinceByID(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT ProvinceName FROM SupportShipProvinces WHERE ProvinceID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("ProvinceName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getNameDistrictByID(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT DistrictName FROM SupportShipDistricts WHERE DistrictID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("DistrictName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getNameWardByID(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT WardName FROM SupportShipWards WHERE WardID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("WardName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getNameStreetByID(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT StreetName FROM SupportShipStreets WHERE StreetID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("StreetName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void main(String[] args) {
        AddressSuportShipDB addressSuportShipDB = new AddressSuportShipDB();
        List<Province> provinces = addressSuportShipDB.getAllProvinces();
        for (Province province : provinces) {
            System.out.println(province.getId() + " " + province.getName());
        }
        List<District> districts = addressSuportShipDB.getDistrictsByProvinceId(1);
        for (District district : districts) {
            System.out.println(district.getId() + " " + district.getName());
        }
        List<Ward> wards = addressSuportShipDB.getWardsByDistrictId(1);
        for (Ward ward : wards) {
            System.out.println(ward.getId() + " " + ward.getName());
        }
        List<Street> streets = addressSuportShipDB.getStreetsByWardId(1);
        for (Street street : streets) {
            System.out.println(street.getId() + " " + street.getName());
        }
        addressSuportShipDB.close();
    }
}
