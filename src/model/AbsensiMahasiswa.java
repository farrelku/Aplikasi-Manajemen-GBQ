/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class AbsensiMahasiswa {
    private String nama;
    private String nim;
    private String tanggal;
    private String status;

    public AbsensiMahasiswa(
        String nama,
        String nim,
        String tanggal,
        String status
    ){
        this.nama = nama;
        this.nim = nim;
        this.tanggal = tanggal;
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getStatus() {
        return status;
    }
}
