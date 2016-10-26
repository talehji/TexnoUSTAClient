/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author Pallas
 */
public class Detallar {
    public int iddetallar;
    public String Kataloq;
    public String KataloqNovu;
    public String Model;
    public String Marka;
    public String Qeyd;
    public String Sayi;
    public double AlisQiymeti;
    public double SatisQiymeti;
    public String AlisYeri;
    public String YeniKohne;
    public int Status;
    public String isActive;

    public Detallar(int iddetallar, String Kataloq, String KataloqNovu, String Model, String Marka, String Qeyd, String Sayi, double AlisQiymeti, double SatisQiymeti, String AlisYeri, String YeniKohne, int Status, String isActive) {
        this.iddetallar = iddetallar;
        this.Kataloq = Kataloq;
        this.KataloqNovu = KataloqNovu;
        this.Model = Model;
        this.Marka = Marka;
        this.Qeyd = Qeyd;
        this.Sayi = Sayi;
        this.AlisQiymeti = AlisQiymeti;
        this.SatisQiymeti = SatisQiymeti;
        this.AlisYeri = AlisYeri;
        this.YeniKohne = YeniKohne;
        this.Status = Status;
        this.isActive = isActive;
    }


    public Detallar() {
    }

    public Detallar(int iddetallar) {
        this.iddetallar = iddetallar;
    }    
}
