package ConnectionDB;

import Object.ChatService;
import Object.DaxilOlan;
import Object.DaxilOlanNov;
import Object.GroupChat;
import Object.Kassa;
import Object.Login;
import Object.MutexesisWork;
import Object.Mutexesisler;
import Object.Tehvil;
import Object.Temir;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    private String Connection;

    public ConnMySql() {
        try {

            try (BufferedReader br = new BufferedReader(new FileReader("conn.txt"))) {

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    Connection = sCurrentLine;
                }

            } catch (IOException e) {
            }
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + Connection + ":3306/texnousta";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
            st = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error baglanti :" + e);
            JOptionPane.showMessageDialog(null, "Serverlə bağlantı alınmadı");

        }
    }

//-------------------------------I-N-S-E-R-T------------------------------------
    public void DaxilOlanInsertUpdate(DaxilOlan f) {
        try {
            String SqlEmri;
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
            JOptionPane.showMessageDialog(null, "Error: 1001");
        }
    }

    public void MutexesisWorkInsertUpdate(MutexesisWork f) {
        try {
            String SqlEmri;
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
            JOptionPane.showMessageDialog(null, "Error: 1002");
        }
    }

    public void LoginInsertUpdate(Login f) {
        try {
            String SqlEmri;
            if (f.idLogin == 0) {
                SqlEmri = "INSERT into Login (idMutexesis, Password, Status) values("
                        + "'" + f.idMutexesis + "',"
                        + "'" + f.Password + "',"
                        + "'" + f.Status + "')";
            } else {
                SqlEmri = "UPDATE Login SET "
                        + "idMutexesis='" + f.idMutexesis + "',"
                        + "Password='" + f.Password + "',"
                        + "Status='" + f.Status + "'"
                        + " where idLogin=" + f.idLogin;
            }

            st.execute(SqlEmri);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: 1003");
        }
    }

    public void TemirInsertUpdate(Temir f) {
        try {
            String SqlEmri;
            if (f.idTemir == 0) {
                SqlEmri = "INSERT into Temir (idMutexesis, idDaxilOlan) values("
                        + "'" + f.idMutexesis + "',"
                        + "'" + f.idDaxilOlan + "')";
            } else {
                SqlEmri = "UPDATE Temir SET "
                        + "idMutexesis='" + f.idMutexesis + "',"
                        + "idDaxilOlan='" + f.idDaxilOlan + "'"
                        + "WHERE idTemir='" + f.idTemir + "'";
            }
            st.execute(SqlEmri);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: 1002");
        }
    }

    public void KassaInsertUpdate(Kassa M) {
        try {
            String SqlEmri;
            if (M.idKassa == 0) {
                SqlEmri = "INSERT into Kassa (Medaxil, Mexaric, Date, idDaxilOlan, Terefinden, Aciqlama, Qeyd) values ("
                        + "'" + M.MeDaxil + "',"
                        + "'" + M.MeXaric + "',"
                        + "'" + M.Date + "',"
                        + "'" + M.idDaxilOlan + "',"
                        + "'" + M.Terefinden + "',"
                        + "'" + M.Aciqlama + "',"
                        + "'" + M.Qeyd + "')";
            } else {
                SqlEmri = "UPDATE Kassa SET "
                        + "MeDaxil='" + M.MeDaxil + "',"
                        + "MeXaric='" + M.MeXaric + "',"
                        + "Date='" + M.Date + "',"
                        + "idDaxilOlan='" + M.idDaxilOlan + "',"
                        + "Terefinden='" + M.Terefinden + "',"
                        + "Aciqlama='" + M.Aciqlama + "',"
                        + "Qeyd='" + M.Qeyd + "'"
                        + "WHERE idKassa='" + M.idKassa + "'";
            }
            st.execute(SqlEmri);
        } catch (SQLException e) {
            System.out.println("" + e);
        }
    }

    public void ChatServiceInsertUpdate(ChatService M) {
        try {
            String SqlEmri;
            if (M.idChatService == 0) {
                SqlEmri = "INSERT into ChatService (idGroup, Text, Date) values ("
                        + "'" + M.idGroup + "',"
                        + "'" + M.Text + "',"
                        + "'" + M.Date + "')";
            } else {
                SqlEmri = "UPDATE ChatService SET "
                        + "idGroup='" + M.idGroup + "',"
                        + "Text='" + M.Text + "',"
                        + "Date='" + M.Date + "'"
                        + "WHERE idChatService='" + M.idChatService + "'";
            }
            st.execute(SqlEmri);
        } catch (SQLException e) {
            System.out.println("" + e);
        }
    }

    public void GroupChatInsertUpdate(GroupChat M) {
        try {
            String SqlEmri;
            if (M.idGroupChat == 0) {
                SqlEmri = "INSERT into GroupChat (idServer, idMembers, Status) values ("
                        + "'" + M.idServer + "',"
                        + "'" + M.idMembers + "',"
                        + "'" + M.Status + "')";
            } else {
                SqlEmri = "UPDATE GroupChat SET "
                        + "idServer='" + M.idServer + "',"
                        + "idMembers='" + M.idMembers + "',"
                        + "Status='" + M.Status + "'"
                        + "WHERE idGroupChat='" + M.idGroupChat + "'";
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
            JOptionPane.showMessageDialog(null, "Error: 1004");
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

    private List<Login> Login(String where) {
        try {
            String SqlEmri = "SELECT * from Login " + where;
            List<Login> list = new ArrayList<>();
            ResultSet rs = st.executeQuery(SqlEmri);
            while (rs.next()) {
                list.add(new Login(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    private List<Temir> Temir(String where) {
        try {
            String SqlEmri = "SELECT * from Temir " + where;
            List<Temir> list = new ArrayList<>();
            ResultSet rs = st.executeQuery(SqlEmri);
            while (rs.next()) {

                list.add(new Temir(rs.getInt(1), rs.getInt(2), rs.getInt(3)));

            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error AllInfo : " + e);
            JOptionPane.showMessageDialog(null, "Error: Daxil olan məlumatlar bazadan oxuna bilmədi");
            return null;
        }
    }

    private List<Tehvil> Tehvil(String where) {
        try {
            String SqlEmri = "SELECT * from Tehvil " + where;
            List<Tehvil> list = new ArrayList<>();
            ResultSet rs = st.executeQuery(SqlEmri);
            while (rs.next()) {

                list.add(new Tehvil(rs.getInt(1), rs.getInt(2), rs.getInt(3)));

            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error AllInfo : " + e);
            JOptionPane.showMessageDialog(null, "Error: Daxil olan məlumatlar bazadan oxuna bilmədi");
            return null;
        }
    }

    private List<ChatService> ChatService(String where) {
        try {
            String SqlEmri = "SELECT * from ChatService " + where;
            List<ChatService> list = new ArrayList<>();
            ResultSet rs = st.executeQuery(SqlEmri);
            while (rs.next()) {

                list.add(new ChatService(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4)));

            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error AllInfo : " + e);
            JOptionPane.showMessageDialog(null, "Error: Daxil olan məlumatlar bazadan oxuna bilmədi");
            return null;
        }
    }

    private List<GroupChat> GroupChat(String where) {
        try {
            String SqlEmri = "SELECT * from GroupChat " + where;
            List<GroupChat> list = new ArrayList<>();
            ResultSet rs = st.executeQuery(SqlEmri);
            while (rs.next()) {

                list.add(new GroupChat(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4)));

            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error AllInfo : " + e);
            JOptionPane.showMessageDialog(null, "Error: Daxil olan məlumatlar bazadan oxuna bilmədi");
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

    public List<MutexesisWork> MutexesisWorkFindBySetName(String SetName, String Text) {
        return MutexesisWork("where " + SetName + "='" + Text + "'");
    }

    public List<MutexesisWork> MutexesisWorkFindListByIdMutexesisler(int ID) {
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

    public List<Login> LoginFindAll() {
        return Login("");
    }

    public Login LoginFindByIdLogin(int idLogin) {
        return Login("where idLogin=" + idLogin).get(0);
    }

    public List<Login> LoginFindListByIdMutexesisler(int idMutexesis) {
        return Login("where idMutexesis=" + idMutexesis);
    }

    public Login LoginFindByIdMutexesisler(int idMutexesis) {
        return Login("where idMutexesis=" + idMutexesis).get(0);
    }

    public List<Temir> TemirFindAll() {
        return Temir("");
    }

    public List<Temir> TemirFindListByIdMutexesis(int ID) {
        return Temir("where idMutexesis=" + ID);
    }

    public Temir TemirFindListByIdTemir(int ID) {
        return Temir("where idTemir=" + ID).get(0);
    }

    public List<Tehvil> TehvilFindAll() {
        return Tehvil("");
    }

    public List<Tehvil> TehvilFindListByIdMutexesis(int ID) {
        return Tehvil("where idMutexesis=" + ID);
    }

    public Tehvil TehvilFindListByIdTehvil(int ID) {
        return Tehvil("where idTehvil=" + ID).get(0);
    }

    public List<ChatService> ChatServiceFindAll() {
        return ChatService("");
    }

    public List<ChatService> ChatServiceFindBySetName(String SetName, String Text) {
        return ChatService("where " + SetName + "='" + Text + "'");
    }

    public ChatService ChatServiceFindByIdChatService(int ID) {
        return ChatService("where idChatService=" + ID).get(0);
    }

    public List<GroupChat> GroupChatFindAll() {
        return GroupChat("");
    }

    public List<GroupChat> GroupChatFindBySetName(String SetName, String Text) {
        return GroupChat("where " + SetName + "='" + Text + "'");
    }

    public GroupChat GroupChatFindByIdChatService(int ID) {
        return GroupChat("where idGroupChat=" + ID).get(0);
    }
}
