package Transaksi;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author putra
 */
public class carirmclass {
    private String id;
    private String nama;
    private String satuan;
    private String jenis;
    private String harga;
    private String stock;
    
    public carirmclass(){
        
    }
    
    public carirmclass(String id, String nama, String satuan, String jenis, String harga, String stock){
        this.id = id;
        this.nama = nama;
        this.satuan = satuan;
        this.jenis = jenis;
        this.harga = harga;
        this.stock = stock;
    }
    
    public String getid(){
        return id;
    }
    
    public void setid(String id){
        this.id = id;
    }
    
    public String getnama(){
    return nama;
    }
    
    public void setnama(String nama){
        this.nama = nama;
    }
    
    public String getsatuan(){
        return satuan;
    }
    
    public void setsatuan(String satuan){
        this.satuan = satuan;
    }
    
    public String getjenis(){
        return jenis;
    }
    
    public void setjenis(String jenis){
        this.jenis = jenis;
    }
    
    public String getharga(){
        return harga;
    }
    
    public void setharga(String harga){
        this.harga = harga;
    }
    
    public String getstock(){
        return stock;
    }
    
    public void setstock(String stock){
        this.stock = stock;
    }
}
