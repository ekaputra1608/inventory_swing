package Transaksi;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author putra
 */
public class carisupplierclass {
    private String id;
    private String nama;
    private String no_tlp;
    private String ket;
    
    public carisupplierclass(){
        
    }
    
    public carisupplierclass(String id, String nama, String no_telp, String ket){
        this.id = id;
        this.nama = nama;
        this.no_tlp = no_tlp;
        this.ket = ket;
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
    
    public String getno_tlp(){
        return no_tlp;
    }
    
    public void setno_tlp(String no_tlp){
        this.no_tlp = no_tlp;
    }
    
    public String getket(){
        return ket;
    }
    
    public void setket(String ket){
        this.ket = ket;
    }
    
    
}
