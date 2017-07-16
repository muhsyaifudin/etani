package com.etanixyz.etani;

/**
 * Created by Horizon on 7/14/2017.
 */

public class Keranjang {
    private String kd_keranjang, jml, subtotal;
    private String kd_produk;
    private String pupuk;
    private String nama_produk;
    private String harga_produk;
    private String nama_petani;
    private String foto_produk;

    public Keranjang(String kd_keranjang, String jml, String kd_produk, String pupuk, String nama_produk, String harga_produk, String nama_petani, String foto_produk) {
        this.kd_keranjang = kd_keranjang;
        this.jml = jml;
        this.kd_produk = kd_produk;
        this.pupuk = pupuk;
        this.nama_produk = nama_produk;
        this.harga_produk = harga_produk;
        this.nama_petani = nama_petani;
        this.foto_produk = foto_produk;
        this.setSubtotal();
    }

    public String getKd_keranjang() {
        return kd_keranjang;
    }

    public void setKd_keranjang(String kd_keranjang) {
        this.kd_keranjang = kd_keranjang;
    }

    public String getJml() {
        return jml;
    }

    public void setJml(String jml) {
        this.jml = jml;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal() {
        int sub, hrg, jml;
        hrg= Integer.parseInt(getHarga_produk());
        jml= Integer.parseInt(getJml());

        sub=hrg*jml;
        this.subtotal = String.valueOf(sub);
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

    public String getNama_petani() {
        return nama_petani;
    }

    public void setNama_petani(String nama_petani) {
        this.nama_petani = nama_petani;
    }

    public String getFoto_produk() {
        return foto_produk;
    }

    public void setFoto_produk(String foto_produk) {
        this.foto_produk = foto_produk;
    }
}
