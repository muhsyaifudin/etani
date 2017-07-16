package com.etanixyz.etani;

/**
 * Created by Horizon on 7/13/2017.
 */

public class Product {
    private String kd_produk;
    private String pupuk;
    private String nama_produk;
    private String harga_produk;
    private String min;
    private String nama_petani;
    private String telepon;
    private String alamat;
    private String foto_produk;





    public Product(String kd_produk,String pupuk,String nama_produk,String harga_produk,String min,String nama_petani,String telepon,String alamat, String foto){
        this.setKd_produk(kd_produk);
        this.setPupuk(pupuk);
        this.setNama_produk(nama_produk);
        this.setHarga_produk(harga_produk);
        this.setMin(min);
        this.setNama_petani(nama_petani);
        this.setAlamat(alamat);
        this.setTelepon(telepon);
        this.setFoto_produk(foto);
    }
    public String getFoto_produk() {
        return foto_produk;
    }

    public void setFoto_produk(String foto_produk) {
        this.foto_produk = foto_produk;
    }
    public String getKd_produk() {
        return kd_produk;
    }

    public void setKd_produk(String kd_produk) {
        this.kd_produk = kd_produk;
    }

    public String getPupuk() {
        return pupuk;
    }

    public void setPupuk(String pupuk) {
        this.pupuk = pupuk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getHarga_produk() {
        return harga_produk;
    }

    public void setHarga_produk(String harga_produk) {
        this.harga_produk = harga_produk;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getNama_petani() {
        return nama_petani;
    }

    public void setNama_petani(String nama_petani) {
        this.nama_petani = nama_petani;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
