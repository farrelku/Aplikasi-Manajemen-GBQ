/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class AbsensiMahasiswaModel {

    private int absensiId;
    private int mahasiswaId;
    private String nama;
    private String nim;
    private String tanggal;
    private String status;

    public int getAbsensiId() {
        return absensiId;
    }

    public void setAbsensiId(int absensiId) {
        this.absensiId = absensiId;
    }

   public int getMahasiswaId() {
    return mahasiswaId;
}

public void setMahasiswaId(int mahasiswaId) {
    this.mahasiswaId = mahasiswaId;
}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
