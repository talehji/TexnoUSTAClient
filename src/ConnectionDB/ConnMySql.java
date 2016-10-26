/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import Object.DaxilOlan;
import Object.DaxilOlanNov;
import Object.MutexesisWork;
import Object.Mutexesisler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Pallas
 */
public class ConnMySql {

    private Connection connection;
    private Statement st;

    public ConnMySql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://192.168.1.103:3306/texnousta";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
            st = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error baglanti :" + e);
            JOptionPane.showMessageDialog(null, "Baza ilə bağlantı alınmadı");
        }
    }

//-------------------------------I-N-S-E-R-T------------------------------------
    public void DaxilOlanInsertUpdate(DaxilOlan f) {
        try {
            String SqlEmri = "";
            if (f.idDaxilOlan == 0) {
                SqlEmri = "INSERT into DaxilOlan (Ad, Soyad, Telefon, idDaxilOlanNov, Model, Marka, Aksesuar, Problem, Netice, Qeyd, Date, isActive, DatePlan, DateTemir, DateTehvil, GY) values("
                        + "'" + f.Ad + "',"
                        + "'" + f.Soyad + "',"
                        + "'" + f.Telefon + "',"
                        + "'" + f.idDaxilOlanNov + "',"
                        + "'" + f.Model + "',"
                        + "'" + f.Marka + "',"
                        + "'" + f.Aksesuar + "',"
                        + "'" + f.Problem + "',"
                        + "'" + f.Netice + "',"
                        + "'" + f.Qeyd + "',"
                        + "'" + f.Date + "',"
                        + "'" + f.isActive + "',"
                        + "'" + f.DatePlan + "',"
                        + "'" + f.DateTemir + "',"
                        + "'" + f.DateTehvil + "',"
                        + "'" + f.GY + "')";
            } else {
                SqlEmri = "UPDATE DaxilOlan SET "
                        + "Ad='" + f.Ad + "',"
                        + "Soyad='" + f.Soyad + "',"
                        + "Telefon='" + f.Telefon + "',"
                        + "idDaxilOlanNov='" + f.idDaxilOlanNov + "',"
                        + "Model='" + f.Model + "',"
                        + "Marka='" + f.Marka + "',"
                        + "Aksesuar='" + f.Aksesuar + "',"
                        + "Problem='" + f.Problem + "',"
                        + "Netice='" + f.Netice + "',"
                        + "Qeyd='" + f.Qeyd + "',"
                        + "Date='" + f.Date + "',"
                        + "isActive='" + f.isActive + "',"
                        + "DatePlan='" + f.DatePlan + "',"
                        + "DateTemir='" + f.DateTemir + "',"
                        + "DateTehvil='" + f.DateTehvil + "',"
                        + "GY='" + f.GY + "'"
                        + "WHERE iddaxilOlan='" + f.idDaxilOlan + "'";
            }

            st.execute(SqlEmri);

        } catch (SQLException e) {
            System.out.println("" + e);
        }
    }

    public void MutexesisWorkInsertUpdate(MutexesisWork f) {
        try {
            String SqlEmri = "";
            if (f.IdMutexesisWork == 0) {
                SqlEmri = "INSERT into MutexesisWork (Date, IdMutexesisler, Status, IdDaxilOlan) values("
                        + "'" + f.Date + "',"
                        + "'" + f.IdMutexesisler + "',"
                        + "'" + f.Status + "',"
                        + "'" + f.IdDaxilOlan + "')";
            } else {
                SqlEmri = "UPDATE MutexesisWork SET "
                        + "Date='" + f.Date + "',"
                        + "IdMutexesisler='" + f.IdMutexesisler + "',"
                        + "Status='" + f.Status + "',"
                        + "IdDaxilOlan='" + f.IdDaxilOlan + "'"
                        + "WHERE IdMutexesisWork='" + f.IdMutexesisWork + "'";
            }

            st.execute(SqlEmri);

        } catch (SQLException e) {
            System.out.println("" + e);
        }
    }

//-----------------------S-E-L-E-C-T---F-O-R------------------------------------
    private List<DaxilOlan> DaxilOlan(String where) {
        try {
            String SqlEmri = "SELECT * from DaxilOlan " + where;
            List<DaxilOlan> list = new ArrayList<>();
            ResultSet rs = st.executeQuery(SqlEmri);
            while (rs.next()) {

                list.add(new DaxilOlan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));

            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error AllInfo : " + e);
            JOptionPane.showMessageDialog(null, "Error: Daxil olan məlumatlar bazadan oxuna bilmədi");
            return null;
        }
    }

    private List<MutexesisWork> MutexesisWork(String where) {
        try {
            String SqlEmri = "SELECT * from MutexesisWork " + where;
            List<MutexesisWork> list = new ArrayList<>();
            ResultSet rs = st.executeQuery(SqlEmri);
            while (rs.next()) {

                list.add(new MutexesisWork(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));

            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error AllMutexesisWork : " + e);
            JOptionPane.showMessageDialog(null, "Error: MutexesisWork bazadan oxuna bilmədi");
            return null;
        }
    }

    private List<Mutexesisler> Mutexesisler(String where) {
        try {
            String SqlEmri = "select * from Mutexesisler " + where;
            List<Mutexesisler> listMutexesisler = new ArrayList<>();
            ResultSet rs = st.executeQuery(SqlEmri);
            while (rs.next()) {
                listMutexesisler.add(new Mutexesisler(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            return listMutexesisler;
        } catch (SQLException e) {
            System.out.println("Error AllMutexesisler : " + e);
            JOptionPane.showMessageDialog(null, "Error: Mutexesisler bazadan oxuna bilmədi");
            return null;
        }
    }

    private List<DaxilOlanNov> DaxilOlanNov(String where) {
        try {
            String SqlEmri = "SELECT * from DaxilOlanNov " + where;
            List<DaxilOlanNov> list = new ArrayList<>();
            ResultSet rs = st.executeQuery(SqlEmri);
            while (rs.next()) {

                list.add(new DaxilOlanNov(rs.getInt(1), rs.getString(2)));

            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error AllInfo : " + e);
            JOptionPane.showMessageDialog(null, "Error: Daxil olan məlumatlar bazadan oxuna bilmədi!!!");
            return null;
        }
    }

//-------------S-E-L-E-C-T----W-I-T-H----P-A-R-A-M-E-T-R------------------------
    public List<DaxilOlan> DaxilOlanFindAll() {
        return DaxilOlan("");
    }

    public DaxilOlan DaxilOlanFindByIdDaxilOlan(int ID) {
        return DaxilOlan("where idDaxilOlan=" + ID).get(0);
    }

    public List<MutexesisWork> MutexesisWorkFindAll() {
        return MutexesisWork("");
    }

    public MutexesisWork MutexesisWorkFindByIdMutexesisWork(int ID) {
        return MutexesisWork("where idMutexesisWork=" + ID).get(0);
    }

    public List<MutexesisWork> MutexesisWorkFindByIdMutexesisler(int ID) {
        return MutexesisWork("where idMutexesisler=" + ID);
    }

    public List<Mutexesisler> MutexesislerFindAll() {
        return Mutexesisler("");
    }

    public Mutexesisler MutexesislerFindByIdMutexesisler(int ID) {
        return Mutexesisler("where idMutexesisler='" + ID + "'").get(0);
    }

    public List<DaxilOlanNov> DaxilOlanNovFindAll() {
        return DaxilOlanNov("");
    }

    public DaxilOlanNov DaxilOlanNovFindByIdDaxilOlanNov(int ID) {
        return DaxilOlanNov("where idDaxilOlanNov=" + ID).get(0);
    }
}
