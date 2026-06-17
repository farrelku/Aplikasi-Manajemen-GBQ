/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

/**
 *
 * @author LENOVO
 */


import java.sql.Connection;
import java.sql.DriverManager;

public class koneksi {

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gbq",
                "root",
                "admin123"
            );

        } catch(Exception e) {

            return null;

        }

    }

}