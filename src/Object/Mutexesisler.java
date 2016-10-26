/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author TexnoUSTA.com
 */
public class Mutexesisler {
    public int idmutexesis;
    public String Ad;
    public String Soyad;
    public String Telefon;
    public String Qeyd;
    public String isActive;

    public Mutexesisler(int idmutexesis, String Ad, String Soyad, String Telefon, String Qeyd, String isActive) {
        this.idmutexesis = idmutexesis;
        this.Ad = Ad;
        this.Soyad = Soyad;
        this.Telefon = Telefon;
        this.Qeyd = Qeyd;
        this.isActive = isActive;
    }
    public Mutexesisler(int idmutexesis) {
        this.idmutexesis = idmutexesis;
    }

    public Mutexesisler() {
    }

   
}

