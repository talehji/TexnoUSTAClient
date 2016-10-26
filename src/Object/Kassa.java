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
public class Kassa {

    public int idKassa;
    public double MeDaxil;
    public double MeXaric;
    public String Date;
    public int idDaxilOlan;
    public String Terefinden;
    public String Aciqlama;
    public String Qeyd;

    public Kassa() {
    }

    public Kassa(int idKassa, double MeDaxil, double MeXaric, String Date, int idDaxilOlan, String Terefinden, String Aciqlama, String Qeyd) {
        this.idKassa = idKassa;
        this.MeDaxil = MeDaxil;
        this.MeXaric = MeXaric;
        this.Date = Date;
        this.idDaxilOlan = idDaxilOlan;
        this.Terefinden = Terefinden;
        this.Aciqlama = Aciqlama;
        this.Qeyd = Qeyd;
    }

    public Kassa(int idKassa) {
        this.idKassa = idKassa;
    }

}
