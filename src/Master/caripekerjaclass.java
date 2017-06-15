package Master;

import Transaksi.*;
import Master.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author putra
 */
public class caripekerjaclass {
    private String id;
    private String nama;
    private String jenis;
    private String harga;
    
    public caripekerjaclass(){
        
    }
    
    public caripekerjaclass(String id, String nama, String jenis, String harga){
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
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
}
