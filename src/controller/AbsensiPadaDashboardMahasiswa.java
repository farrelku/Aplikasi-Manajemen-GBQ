/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.AbsensiMahasiswa;

/**
 *
 * @author LENOVO
 */
public class AbsensiPadaDashboardMahasiswa {
    Connection con;

    public AbsensiPadaDashboardMahasiswa() {

        try {

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gbq",
                "root",
                "admin123"
            );

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public ArrayList<AbsensiMahasiswa> getDataAbsensi(
            String nim,
        String statusFilter
){

    ArrayList<AbsensiMahasiswa> list =
    new ArrayList<>();

    try {

        String sql =
        "SELECT m.nama, m.nim, a.tanggal, a.status " +
        "FROM absensi a " +
        "JOIN mahasiswa m ON a.mahasiswa_id = m.id " +
        "WHERE m.nim=?";

        if(!statusFilter.equals("Semua")){
            sql += " AND a.status=?";
        }

        PreparedStatement pst =
        con.prepareStatement(sql);

        pst.setString(1, nim);

        if(!statusFilter.equals("Semua")){
            pst.setString(2, statusFilter);
        }

        ResultSet rs = pst.executeQuery();

        while(rs.next()){

            list.add(
                new AbsensiMahasiswa(
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("tanggal"),
                    rs.getString("status")
                )
            );

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return list;
           }}
