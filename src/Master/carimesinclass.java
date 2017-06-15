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
public class carimesinclass {
    private String id;
    private String nama;
    private String harga;
    
    public carimesinclass(){
        
    }
    
    public carimesinclass(String id, String nama, String harga){
        this.id = id;
        this.nama = nama;
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
    
    public String getharga(){
        return harga;
    }
    
    public void setharga(String harga){
        this.harga = harga;
    }    
}
