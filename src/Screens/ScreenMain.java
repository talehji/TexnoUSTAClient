package Screens;

import ConnectionDB.ConnMySql;
import Object.Login;
import Object.DaxilOlan;
import Object.Tehvil;
import Object.Temir;
import Object.MutexesisWork;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public final class ScreenMain extends javax.swing.JFrame {


    Login l;
    private final ConnMySql conn;
    private List<MutexesisWork> ListOfMutexesislerWork;

    public ScreenMain(Login l) {
        initComponents();
        this.setExtendedState(ScreenMain.MAXIMIZED_BOTH);
        this.l = l;

        conn = new ConnMySql();
        jMenuMember.setText(conn.MutexesislerFindByIdMutexesisler(l.idMutexesis).Ad + " " + conn.MutexesislerFindByIdMutexesisler(l.idMutexesis).Soyad);

        new Thread() {
            @Override
            public void run() {
                SimpleDateFormat as = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat ab = new SimpleDateFormat("HHmmss");
                SimpleDateFormat ad = new SimpleDateFormat("dd-MM-yyyy");
                while (true) {
                    try {

                        Date ag = new Date();
                        String Date = ad.format(ag);
                        String Time = as.format(ag);
                        jLabelTarixsaat.setText(Date + " " + Time);
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ScreenMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();

        Refresh();
    }

    public void Refresh() {
        ListOfMutexesislerWork = conn.MutexesisWorkFindListByIdMutexesisler(l.idMutexesis);
        FillTheTableGonderilen();
        FillTheTableAnakart();
        FillTheTableSadeDetalProblemi();
        FillTheTableProqramTeminati();
        FillTheTableMusteridencavab();
        FillTheTabledetalGozleyir();
        FillTheTableTemiriMumkunsuz();
        FillTheTableKuryerPlataAxtarsin();
        FillTheTableDiaqostika();
        FillTheTableTemirEtdiklerim();
        FillTheTableTehvilVerdiklerim();
    }

    private void FillTheTableTemirEtdiklerim() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("Ad Soyad");
        tmodel.addColumn("Daxil Olan");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Tarix");

        jTableTemir.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableTemir.setRowHeight(20);
        jTableTemir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableTemir.setModel(tmodel);
        List<Temir> ListOfTemir = conn.TemirFindListByIdMutexesis(l.idMutexesis);

        ListOfTemir.forEach((t) -> {
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(t.idDaxilOlan);
            tmodel.insertRow(jTableTemir.getRowCount(), new Object[]{
                DaxilolanSingle.idDaxilOlan,
                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                DaxilolanSingle.Telefon,
                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                DaxilolanSingle.DateTehvil,});
        });
    }

    private void FillTheTableTehvilVerdiklerim() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("Ad Soyad");
        tmodel.addColumn("Daxil Olan");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Tarix");

        jTableğTehvil.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableğTehvil.setRowHeight(20);
        jTableğTehvil.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableğTehvil.setModel(tmodel);
        List<Tehvil> ListOfTehvil = conn.TehvilFindListByIdMutexesis(l.idMutexesis);

        ListOfTehvil.forEach((t) -> {
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(t.idDaxilOlan);
            tmodel.insertRow(jTableğTehvil.getRowCount(), new Object[]{
                DaxilolanSingle.idDaxilOlan,
                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                DaxilolanSingle.Telefon,
                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                DaxilolanSingle.DateTehvil,});
        });
    }

    private void FillTheTableGonderilen() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setRowHeight(20);
        jTable1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTable1.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);
            if (DaxilolanSingle.isActive.equals("6")) {
                if (b.Status.equals("1")) {

                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),});
                }
            }
        });
    }

    private void FillTheTableAnakart() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");

        jTableAnakart.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableAnakart.setRowHeight(20);
        jTableAnakart.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableAnakart.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);

            if (DaxilolanSingle.isActive.equals("6")) {
                if (b.Status.equals("3")) {

                    tmodel.insertRow(jTableAnakart.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),});
                }
            }
        });
    }

    private void FillTheTableSadeDetalProblemi() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");

        jTableSadedetalProblemi.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableSadedetalProblemi.setRowHeight(20);
        jTableSadedetalProblemi.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableSadedetalProblemi.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);

            if (DaxilolanSingle.isActive.equals("6")) {
                if (b.Status.equals("4")) {

                    tmodel.insertRow(jTableSadedetalProblemi.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),});
                }
            }
        });
    }

    private void FillTheTableProqramTeminati() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");

        jTableProqramTeminati.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableProqramTeminati.setRowHeight(20);
        jTableProqramTeminati.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableProqramTeminati.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);

            if (DaxilolanSingle.isActive.equals("6")) {
                if (b.Status.equals("5")) {

                    tmodel.insertRow(jTableProqramTeminati.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),});
                }
            }
        });
    }

    private void FillTheTableMusteridencavab() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");

        jTableMusteridenCavab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableMusteridenCavab.setRowHeight(20);
        jTableMusteridenCavab.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableMusteridenCavab.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);

            if (DaxilolanSingle.isActive.equals("6")) {
                if (b.Status.equals("6")) {

                    tmodel.insertRow(jTableMusteridenCavab.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),});
                }
            }
        });
    }

    private void FillTheTabledetalGozleyir() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");

        jTableDetalGozleyir.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableDetalGozleyir.setRowHeight(20);
        jTableDetalGozleyir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableDetalGozleyir.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);

            if (DaxilolanSingle.isActive.equals("6")) {
                if (b.Status.equals("7")) {

                    tmodel.insertRow(jTableDetalGozleyir.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),});
                }
            }
        });
    }

    private void FillTheTableTemiriMumkunsuz() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");

        jTableTemiriMumkunsuz.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableTemiriMumkunsuz.setRowHeight(20);
        jTableTemiriMumkunsuz.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableTemiriMumkunsuz.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);

            if (DaxilolanSingle.isActive.equals("6")) {
                if (b.Status.equals("8")) {

                    tmodel.insertRow(jTableTemiriMumkunsuz.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),});
                }
            }
        });
    }

    private void FillTheTableKuryerPlataAxtarsin() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");

        jTableKuryerPlataAxtarsin.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableKuryerPlataAxtarsin.setRowHeight(20);
        jTableKuryerPlataAxtarsin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableKuryerPlataAxtarsin.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);

            if (DaxilolanSingle.isActive.equals("6")) {
                if (b.Status.equals("9")) {

                    tmodel.insertRow(jTableKuryerPlataAxtarsin.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),});
                }
            }
        });
    }

    private void FillTheTableDiaqostika() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");

        jTableDiaqnostika.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableDiaqnostika.setRowHeight(20);
        jTableDiaqnostika.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableDiaqnostika.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);

            if (conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan).isActive.equals("6")) {
                if (b.Status.equals("10")) {

                    tmodel.insertRow(jTableDiaqnostika.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),});
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuTapşırıq = new javax.swing.JPopupMenu();
        jMenuItemCetineat = new javax.swing.JMenuItem();
        jMenuItemCetineat3 = new javax.swing.JMenuItem();
        jMenuItemCetineat4 = new javax.swing.JMenuItem();
        jMenuItemCetineat5 = new javax.swing.JMenuItem();
        jMenuItemCetineat6 = new javax.swing.JMenuItem();
        jMenuItemCetineat7 = new javax.swing.JMenuItem();
        jMenuItemCetineat38 = new javax.swing.JMenuItem();
        jMenuItemCetineat39 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jPopupMenuAnakart = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemCetineat8 = new javax.swing.JMenuItem();
        jMenuItemCetineat9 = new javax.swing.JMenuItem();
        jMenuItemCetineat10 = new javax.swing.JMenuItem();
        jMenuItemCetineat11 = new javax.swing.JMenuItem();
        jMenuItemCetineat12 = new javax.swing.JMenuItem();
        jMenuItemCetineat40 = new javax.swing.JMenuItem();
        jMenuItemCetineat41 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jPopupMenuSadedetalProblemi = new javax.swing.JPopupMenu();
        jMenuItemCetineat1 = new javax.swing.JMenuItem();
        jMenuItemCetineat13 = new javax.swing.JMenuItem();
        jMenuItemCetineat14 = new javax.swing.JMenuItem();
        jMenuItemCetineat15 = new javax.swing.JMenuItem();
        jMenuItemCetineat16 = new javax.swing.JMenuItem();
        jMenuItemCetineat17 = new javax.swing.JMenuItem();
        jMenuItemCetineat42 = new javax.swing.JMenuItem();
        jMenuItemCetineat43 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jPopupMenuProqramTeminati = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItemCetineat18 = new javax.swing.JMenuItem();
        jMenuItemCetineat19 = new javax.swing.JMenuItem();
        jMenuItemCetineat20 = new javax.swing.JMenuItem();
        jMenuItemCetineat21 = new javax.swing.JMenuItem();
        jMenuItemCetineat22 = new javax.swing.JMenuItem();
        jMenuItemCetineat44 = new javax.swing.JMenuItem();
        jMenuItemCetineat45 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jPopupMenuMusteridenCavab = new javax.swing.JPopupMenu();
        jMenuItemCetineat2 = new javax.swing.JMenuItem();
        jMenuItemCetineat23 = new javax.swing.JMenuItem();
        jMenuItemCetineat24 = new javax.swing.JMenuItem();
        jMenuItemCetineat25 = new javax.swing.JMenuItem();
        jMenuItemCetineat26 = new javax.swing.JMenuItem();
        jMenuItemCetineat27 = new javax.swing.JMenuItem();
        jMenuItemCetineat46 = new javax.swing.JMenuItem();
        jMenuItemCetineat47 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jPopupMenuDetalGozleyir = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItemCetineat28 = new javax.swing.JMenuItem();
        jMenuItemCetineat29 = new javax.swing.JMenuItem();
        jMenuItemCetineat30 = new javax.swing.JMenuItem();
        jMenuItemCetineat31 = new javax.swing.JMenuItem();
        jMenuItemCetineat32 = new javax.swing.JMenuItem();
        jMenuItemCetineat48 = new javax.swing.JMenuItem();
        jMenuItemCetineat49 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jPopupMenuTemiriMumkunsuz = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItemCetineat33 = new javax.swing.JMenuItem();
        jMenuItemCetineat34 = new javax.swing.JMenuItem();
        jMenuItemCetineat35 = new javax.swing.JMenuItem();
        jMenuItemCetineat36 = new javax.swing.JMenuItem();
        jMenuItemCetineat37 = new javax.swing.JMenuItem();
        jMenuItemCetineat50 = new javax.swing.JMenuItem();
        jMenuItemCetineat51 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jPopupMenuKuryerPlataAxtarsin = new javax.swing.JPopupMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItemCetineat52 = new javax.swing.JMenuItem();
        jMenuItemCetineat53 = new javax.swing.JMenuItem();
        jMenuItemCetineat54 = new javax.swing.JMenuItem();
        jMenuItemCetineat55 = new javax.swing.JMenuItem();
        jMenuItemCetineat56 = new javax.swing.JMenuItem();
        jMenuItemCetineat57 = new javax.swing.JMenuItem();
        jMenuItemCetineat58 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jPopupMenuDiaqnostika = new javax.swing.JPopupMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItemCetineat59 = new javax.swing.JMenuItem();
        jMenuItemCetineat60 = new javax.swing.JMenuItem();
        jMenuItemCetineat61 = new javax.swing.JMenuItem();
        jMenuItemCetineat62 = new javax.swing.JMenuItem();
        jMenuItemCetineat63 = new javax.swing.JMenuItem();
        jMenuItemCetineat64 = new javax.swing.JMenuItem();
        jMenuItemCetineat65 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAnakart = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableSadedetalProblemi = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableProqramTeminati = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableMusteridenCavab = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableDetalGozleyir = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableTemiriMumkunsuz = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableKuryerPlataAxtarsin = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableDiaqnostika = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableTemir = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableğTehvil = new javax.swing.JTable();
        jLabelTarixsaat = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuMember = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();

        jMenuItemCetineat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat.setText("Ana kart");
        jMenuItemCetineat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineatActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItemCetineat);

        jMenuItemCetineat3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat3.setText("Sadə detal problemi");
        jMenuItemCetineat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat3ActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItemCetineat3);

        jMenuItemCetineat4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat4.setText("Proqram təminatı");
        jMenuItemCetineat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat4ActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItemCetineat4);

        jMenuItemCetineat5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat5.setText("Müştəridən cavab");
        jMenuItemCetineat5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat5ActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItemCetineat5);

        jMenuItemCetineat6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat6.setText("Detal gözləyir");
        jMenuItemCetineat6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat6ActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItemCetineat6);

        jMenuItemCetineat7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat7.setText("Təmiri mümkünsüz");
        jMenuItemCetineat7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat7ActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItemCetineat7);

        jMenuItemCetineat38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat38.setText("Kuryer plata axtarsın");
        jMenuItemCetineat38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat38ActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItemCetineat38);

        jMenuItemCetineat39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat39.setText("Diaqnostika");
        jMenuItemCetineat39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat39ActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItemCetineat39);

        jMenuItem16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem16.setText("Təmir etdim");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItem16);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem1.setText("Tapşırığa yönəlt");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenuAnakart.add(jMenuItem1);

        jMenuItemCetineat8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat8.setText("Sadə detal problemi");
        jMenuItemCetineat8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat8ActionPerformed(evt);
            }
        });
        jPopupMenuAnakart.add(jMenuItemCetineat8);

        jMenuItemCetineat9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat9.setText("Proqram təminatı");
        jMenuItemCetineat9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat9ActionPerformed(evt);
            }
        });
        jPopupMenuAnakart.add(jMenuItemCetineat9);

        jMenuItemCetineat10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat10.setText("Müştəridən cavab");
        jMenuItemCetineat10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat10ActionPerformed(evt);
            }
        });
        jPopupMenuAnakart.add(jMenuItemCetineat10);

        jMenuItemCetineat11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat11.setText("Detal gözləyir");
        jMenuItemCetineat11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat11ActionPerformed(evt);
            }
        });
        jPopupMenuAnakart.add(jMenuItemCetineat11);

        jMenuItemCetineat12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat12.setText("Təmiri mümkünsüz");
        jMenuItemCetineat12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat12ActionPerformed(evt);
            }
        });
        jPopupMenuAnakart.add(jMenuItemCetineat12);

        jMenuItemCetineat40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat40.setText("Kuryer plata axtarsın");
        jMenuItemCetineat40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat40ActionPerformed(evt);
            }
        });
        jPopupMenuAnakart.add(jMenuItemCetineat40);

        jMenuItemCetineat41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat41.setText("Diaqnostika");
        jMenuItemCetineat41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat41ActionPerformed(evt);
            }
        });
        jPopupMenuAnakart.add(jMenuItemCetineat41);

        jMenuItem17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem17.setText("Təmir etdim");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jPopupMenuAnakart.add(jMenuItem17);

        jMenuItemCetineat1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat1.setText("Tapşırığa yönəlt");
        jMenuItemCetineat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat1ActionPerformed(evt);
            }
        });
        jPopupMenuSadedetalProblemi.add(jMenuItemCetineat1);

        jMenuItemCetineat13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat13.setText("Ana Kart");
        jMenuItemCetineat13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat13ActionPerformed(evt);
            }
        });
        jPopupMenuSadedetalProblemi.add(jMenuItemCetineat13);

        jMenuItemCetineat14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat14.setText("Proqram təminatı");
        jMenuItemCetineat14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat14ActionPerformed(evt);
            }
        });
        jPopupMenuSadedetalProblemi.add(jMenuItemCetineat14);

        jMenuItemCetineat15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat15.setText("Müştəridən cavab");
        jMenuItemCetineat15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat15ActionPerformed(evt);
            }
        });
        jPopupMenuSadedetalProblemi.add(jMenuItemCetineat15);

        jMenuItemCetineat16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat16.setText("Detal gözləyir");
        jMenuItemCetineat16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat16ActionPerformed(evt);
            }
        });
        jPopupMenuSadedetalProblemi.add(jMenuItemCetineat16);

        jMenuItemCetineat17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat17.setText("Təmiri mümkünsüz");
        jMenuItemCetineat17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat17ActionPerformed(evt);
            }
        });
        jPopupMenuSadedetalProblemi.add(jMenuItemCetineat17);

        jMenuItemCetineat42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat42.setText("Kuryer plata axtarsın");
        jMenuItemCetineat42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat42ActionPerformed(evt);
            }
        });
        jPopupMenuSadedetalProblemi.add(jMenuItemCetineat42);

        jMenuItemCetineat43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat43.setText("Diaqnostika");
        jMenuItemCetineat43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat43ActionPerformed(evt);
            }
        });
        jPopupMenuSadedetalProblemi.add(jMenuItemCetineat43);

        jMenuItem18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem18.setText("Təmir etdim");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jPopupMenuSadedetalProblemi.add(jMenuItem18);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem2.setText("Tapşırığa yönəlt");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenuProqramTeminati.add(jMenuItem2);

        jMenuItemCetineat18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat18.setText("Ana Kart");
        jMenuItemCetineat18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat18ActionPerformed(evt);
            }
        });
        jPopupMenuProqramTeminati.add(jMenuItemCetineat18);

        jMenuItemCetineat19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat19.setText("Sadə detal problemi");
        jMenuItemCetineat19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat19ActionPerformed(evt);
            }
        });
        jPopupMenuProqramTeminati.add(jMenuItemCetineat19);

        jMenuItemCetineat20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat20.setText("Müştəridən cavab");
        jMenuItemCetineat20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat20ActionPerformed(evt);
            }
        });
        jPopupMenuProqramTeminati.add(jMenuItemCetineat20);

        jMenuItemCetineat21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat21.setText("Detal gözləyir");
        jMenuItemCetineat21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat21ActionPerformed(evt);
            }
        });
        jPopupMenuProqramTeminati.add(jMenuItemCetineat21);

        jMenuItemCetineat22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat22.setText("Təmiri mümkünsüz");
        jMenuItemCetineat22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat22ActionPerformed(evt);
            }
        });
        jPopupMenuProqramTeminati.add(jMenuItemCetineat22);

        jMenuItemCetineat44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat44.setText("Kuryer plata axtarsın");
        jMenuItemCetineat44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat44ActionPerformed(evt);
            }
        });
        jPopupMenuProqramTeminati.add(jMenuItemCetineat44);

        jMenuItemCetineat45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat45.setText("Diaqnostika");
        jMenuItemCetineat45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat45ActionPerformed(evt);
            }
        });
        jPopupMenuProqramTeminati.add(jMenuItemCetineat45);

        jMenuItem19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem19.setText("Təmir etdim");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jPopupMenuProqramTeminati.add(jMenuItem19);

        jMenuItemCetineat2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat2.setText("Tapşırığa yönəlt");
        jMenuItemCetineat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat2ActionPerformed(evt);
            }
        });
        jPopupMenuMusteridenCavab.add(jMenuItemCetineat2);

        jMenuItemCetineat23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat23.setText("Ana Kart");
        jMenuItemCetineat23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat23ActionPerformed(evt);
            }
        });
        jPopupMenuMusteridenCavab.add(jMenuItemCetineat23);

        jMenuItemCetineat24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat24.setText("Sadə detal problemi");
        jMenuItemCetineat24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat24ActionPerformed(evt);
            }
        });
        jPopupMenuMusteridenCavab.add(jMenuItemCetineat24);

        jMenuItemCetineat25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat25.setText("Proqram Təminatı");
        jMenuItemCetineat25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat25ActionPerformed(evt);
            }
        });
        jPopupMenuMusteridenCavab.add(jMenuItemCetineat25);

        jMenuItemCetineat26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat26.setText("Detal gözləyir");
        jMenuItemCetineat26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat26ActionPerformed(evt);
            }
        });
        jPopupMenuMusteridenCavab.add(jMenuItemCetineat26);

        jMenuItemCetineat27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat27.setText("Təmiri mümkünsüz");
        jMenuItemCetineat27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat27ActionPerformed(evt);
            }
        });
        jPopupMenuMusteridenCavab.add(jMenuItemCetineat27);

        jMenuItemCetineat46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat46.setText("Kuryer plata axtarsın");
        jMenuItemCetineat46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat46ActionPerformed(evt);
            }
        });
        jPopupMenuMusteridenCavab.add(jMenuItemCetineat46);

        jMenuItemCetineat47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat47.setText("Diaqnostika");
        jMenuItemCetineat47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat47ActionPerformed(evt);
            }
        });
        jPopupMenuMusteridenCavab.add(jMenuItemCetineat47);

        jMenuItem20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem20.setText("Təmir etdim");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jPopupMenuMusteridenCavab.add(jMenuItem20);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem3.setText("Tapşırığa yönəlt");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenuDetalGozleyir.add(jMenuItem3);

        jMenuItemCetineat28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat28.setText("Ana Kart");
        jMenuItemCetineat28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat28ActionPerformed(evt);
            }
        });
        jPopupMenuDetalGozleyir.add(jMenuItemCetineat28);

        jMenuItemCetineat29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat29.setText("Sadə detal problemi");
        jMenuItemCetineat29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat29ActionPerformed(evt);
            }
        });
        jPopupMenuDetalGozleyir.add(jMenuItemCetineat29);

        jMenuItemCetineat30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat30.setText("Proqram Təminatı");
        jMenuItemCetineat30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat30ActionPerformed(evt);
            }
        });
        jPopupMenuDetalGozleyir.add(jMenuItemCetineat30);

        jMenuItemCetineat31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat31.setText("Müştəridən cavab");
        jMenuItemCetineat31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat31ActionPerformed(evt);
            }
        });
        jPopupMenuDetalGozleyir.add(jMenuItemCetineat31);

        jMenuItemCetineat32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat32.setText("Təmiri mümkünsüz");
        jMenuItemCetineat32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat32ActionPerformed(evt);
            }
        });
        jPopupMenuDetalGozleyir.add(jMenuItemCetineat32);

        jMenuItemCetineat48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat48.setText("Kuryer plata axtarsın");
        jMenuItemCetineat48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat48ActionPerformed(evt);
            }
        });
        jPopupMenuDetalGozleyir.add(jMenuItemCetineat48);

        jMenuItemCetineat49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat49.setText("Diaqnostika");
        jMenuItemCetineat49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat49ActionPerformed(evt);
            }
        });
        jPopupMenuDetalGozleyir.add(jMenuItemCetineat49);

        jMenuItem21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem21.setText("Təmir etdim");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jPopupMenuDetalGozleyir.add(jMenuItem21);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem4.setText("Tapşırığa yönəlt");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenuTemiriMumkunsuz.add(jMenuItem4);

        jMenuItemCetineat33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat33.setText("Ana Kart");
        jMenuItemCetineat33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat33ActionPerformed(evt);
            }
        });
        jPopupMenuTemiriMumkunsuz.add(jMenuItemCetineat33);

        jMenuItemCetineat34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat34.setText("Sadə detal problemi");
        jMenuItemCetineat34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat34ActionPerformed(evt);
            }
        });
        jPopupMenuTemiriMumkunsuz.add(jMenuItemCetineat34);

        jMenuItemCetineat35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat35.setText("Proqram Təminatı");
        jMenuItemCetineat35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat35ActionPerformed(evt);
            }
        });
        jPopupMenuTemiriMumkunsuz.add(jMenuItemCetineat35);

        jMenuItemCetineat36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat36.setText("Müştəridən cavab");
        jMenuItemCetineat36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat36ActionPerformed(evt);
            }
        });
        jPopupMenuTemiriMumkunsuz.add(jMenuItemCetineat36);

        jMenuItemCetineat37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat37.setText("Detal gözləyir");
        jMenuItemCetineat37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat37ActionPerformed(evt);
            }
        });
        jPopupMenuTemiriMumkunsuz.add(jMenuItemCetineat37);

        jMenuItemCetineat50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat50.setText("Kuryer plata axtarsın");
        jMenuItemCetineat50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat50ActionPerformed(evt);
            }
        });
        jPopupMenuTemiriMumkunsuz.add(jMenuItemCetineat50);

        jMenuItemCetineat51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat51.setText("Diaqnostika");
        jMenuItemCetineat51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat51ActionPerformed(evt);
            }
        });
        jPopupMenuTemiriMumkunsuz.add(jMenuItemCetineat51);

        jMenuItem22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem22.setText("Təmir etdim");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jPopupMenuTemiriMumkunsuz.add(jMenuItem22);

        jMenuItem14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem14.setText("Tapşırığa yönəlt");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jPopupMenuKuryerPlataAxtarsin.add(jMenuItem14);

        jMenuItemCetineat52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat52.setText("Ana Kart");
        jMenuItemCetineat52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat52ActionPerformed(evt);
            }
        });
        jPopupMenuKuryerPlataAxtarsin.add(jMenuItemCetineat52);

        jMenuItemCetineat53.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat53.setText("Sadə detal problemi");
        jMenuItemCetineat53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat53ActionPerformed(evt);
            }
        });
        jPopupMenuKuryerPlataAxtarsin.add(jMenuItemCetineat53);

        jMenuItemCetineat54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat54.setText("Proqram Təminatı");
        jMenuItemCetineat54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat54ActionPerformed(evt);
            }
        });
        jPopupMenuKuryerPlataAxtarsin.add(jMenuItemCetineat54);

        jMenuItemCetineat55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat55.setText("Müştəridən cavab");
        jMenuItemCetineat55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat55ActionPerformed(evt);
            }
        });
        jPopupMenuKuryerPlataAxtarsin.add(jMenuItemCetineat55);

        jMenuItemCetineat56.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat56.setText("Detal Gözləyir");
        jMenuItemCetineat56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat56ActionPerformed(evt);
            }
        });
        jPopupMenuKuryerPlataAxtarsin.add(jMenuItemCetineat56);

        jMenuItemCetineat57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat57.setText("Təmiri mümkünsüz");
        jMenuItemCetineat57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat57ActionPerformed(evt);
            }
        });
        jPopupMenuKuryerPlataAxtarsin.add(jMenuItemCetineat57);

        jMenuItemCetineat58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat58.setText("Diaqnostika");
        jMenuItemCetineat58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat58ActionPerformed(evt);
            }
        });
        jPopupMenuKuryerPlataAxtarsin.add(jMenuItemCetineat58);

        jMenuItem23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem23.setText("Təmir etdim");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jPopupMenuKuryerPlataAxtarsin.add(jMenuItem23);

        jMenuItem15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem15.setText("Tapşırığa yönəlt");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jPopupMenuDiaqnostika.add(jMenuItem15);

        jMenuItemCetineat59.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat59.setText("Ana Kart");
        jMenuItemCetineat59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat59ActionPerformed(evt);
            }
        });
        jPopupMenuDiaqnostika.add(jMenuItemCetineat59);

        jMenuItemCetineat60.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat60.setText("Sadə detal problemi");
        jMenuItemCetineat60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat60ActionPerformed(evt);
            }
        });
        jPopupMenuDiaqnostika.add(jMenuItemCetineat60);

        jMenuItemCetineat61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat61.setText("Proqram Təminatı");
        jMenuItemCetineat61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat61ActionPerformed(evt);
            }
        });
        jPopupMenuDiaqnostika.add(jMenuItemCetineat61);

        jMenuItemCetineat62.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat62.setText("Müştəridən cavab");
        jMenuItemCetineat62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat62ActionPerformed(evt);
            }
        });
        jPopupMenuDiaqnostika.add(jMenuItemCetineat62);

        jMenuItemCetineat63.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat63.setText("Detal gözləyir");
        jMenuItemCetineat63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat63ActionPerformed(evt);
            }
        });
        jPopupMenuDiaqnostika.add(jMenuItemCetineat63);

        jMenuItemCetineat64.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat64.setText("Təmiri mümkünsüz");
        jMenuItemCetineat64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat64ActionPerformed(evt);
            }
        });
        jPopupMenuDiaqnostika.add(jMenuItemCetineat64);

        jMenuItemCetineat65.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat65.setText("Kuryer plata axtarsın");
        jMenuItemCetineat65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat65ActionPerformed(evt);
            }
        });
        jPopupMenuDiaqnostika.add(jMenuItemCetineat65);

        jMenuItem24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem24.setText("Təmir etdim");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jPopupMenuDiaqnostika.add(jMenuItem24);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setEnabled(false);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/reload-icon.png"))); // NOI18N
        jButton8.setToolTipText("Yenilə");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/toolbox.png"))); // NOI18N
        jButton11.setToolTipText("Təmir etdim");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Button-Close-icon.png"))); // NOI18N
        jButton13.setToolTipText("İmtina");
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton13);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Apps-Dialog-Logout-icon.png"))); // NOI18N
        jButton14.setToolTipText("Çıxış");
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton14);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/settings-icon.png"))); // NOI18N
        jButton6.setToolTipText("Ayarlar");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenuTapşırıq);
        jTable1.setGridColor(new java.awt.Color(153, 153, 153));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  Tapşırıq  ", jPanel1);

        jTableAnakart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableAnakart.setComponentPopupMenu(jPopupMenuAnakart);
        jTableAnakart.setGridColor(new java.awt.Color(153, 153, 153));
        jTableAnakart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAnakartMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableAnakart);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  Ana kart  ", jPanel2);

        jTableSadedetalProblemi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableSadedetalProblemi.setComponentPopupMenu(jPopupMenuSadedetalProblemi);
        jTableSadedetalProblemi.setGridColor(new java.awt.Color(153, 153, 153));
        jTableSadedetalProblemi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSadedetalProblemiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableSadedetalProblemi);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  Sadə detal problemi  ", jPanel3);

        jTableProqramTeminati.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableProqramTeminati.setComponentPopupMenu(jPopupMenuProqramTeminati);
        jTableProqramTeminati.setGridColor(new java.awt.Color(153, 153, 153));
        jTableProqramTeminati.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProqramTeminatiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableProqramTeminati);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  Proqram təminatı  ", jPanel4);

        jTableMusteridenCavab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableMusteridenCavab.setComponentPopupMenu(jPopupMenuMusteridenCavab);
        jTableMusteridenCavab.setGridColor(new java.awt.Color(153, 153, 153));
        jTableMusteridenCavab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMusteridenCavabMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableMusteridenCavab);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  Müştəridən cavab  ", jPanel5);

        jTableDetalGozleyir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableDetalGozleyir.setComponentPopupMenu(jPopupMenuDetalGozleyir);
        jTableDetalGozleyir.setGridColor(new java.awt.Color(153, 153, 153));
        jTableDetalGozleyir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDetalGozleyirMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableDetalGozleyir);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  Detal gözləyir  ", jPanel6);

        jTableTemiriMumkunsuz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableTemiriMumkunsuz.setComponentPopupMenu(jPopupMenuTemiriMumkunsuz);
        jTableTemiriMumkunsuz.setGridColor(new java.awt.Color(153, 153, 153));
        jTableTemiriMumkunsuz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTemiriMumkunsuzMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableTemiriMumkunsuz);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  Təmiri mümkünsüz  ", jPanel7);

        jTableKuryerPlataAxtarsin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableKuryerPlataAxtarsin.setComponentPopupMenu(jPopupMenuKuryerPlataAxtarsin);
        jTableKuryerPlataAxtarsin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKuryerPlataAxtarsinMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTableKuryerPlataAxtarsin);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Kuryer Detal Axtarsın", jPanel8);

        jTableDiaqnostika.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableDiaqnostika.setComponentPopupMenu(jPopupMenuDiaqnostika);
        jTableDiaqnostika.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDiaqnostikaMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTableDiaqnostika);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Diaqnostika", jPanel9);

        jTableTemir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableTemir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTemirMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTableTemir);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Təmir etdiklərim", jPanel11);

        jTableğTehvil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableğTehvil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableğTehvilMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTableğTehvil);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Təhvil verdiklərim", jPanel10);

        jLabelTarixsaat.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTarixsaat.setText(" ");

        jMenuMember.setText(" ");
        jMenuMember.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem12.setText("Parolu dəyiş");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenuMember.add(jMenuItem12);

        jMenuItem13.setText("Çıxış");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenuMember.add(jMenuItem13);

        jMenuBar1.add(jMenuMember);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelTarixsaat)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTarixsaat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Refresh();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));

            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date, ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
            Refresh();
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork S = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            DaxilOlan GetDaxilOlan = conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan);

            conn.DaxilOlanInsertUpdate(new DaxilOlan(GetDaxilOlan.idDaxilOlan, GetDaxilOlan.Ad,
                    GetDaxilOlan.Soyad, GetDaxilOlan.Telefon, GetDaxilOlan.idDaxilOlanNov,
                    GetDaxilOlan.Model, GetDaxilOlan.Marka, GetDaxilOlan.Aksesuar, GetDaxilOlan.Problem,
                    GetDaxilOlan.Netice, GetDaxilOlan.Qeyd, GetDaxilOlan.Date, GetDaxilOlan.isActive,
                    GetDaxilOlan.DatePlan, GetDaxilOlan.DateTemir, GetDaxilOlan.DateTehvil, GetDaxilOlan.GY));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(S.IdMutexesisWork,
                    S.Date, S.IdMutexesisler, "2", S.IdDaxilOlan));
        }
        Refresh();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        conn.LoginInsertUpdate(new Login(l.idLogin, l.idMutexesis, l.Password, "0"));
        System.exit(0);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ChangePassword d = new ChangePassword(this, rootPaneCheckingEnabled, l);
        d.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItemCetineatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineatActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, 
                    ScreenBax.Date, ScreenBax.IdMutexesisler, "3", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineatActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork,
                    ScreenBax.Date, ScreenBax.IdMutexesisler, "1", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemCetineat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, 
                    ScreenBax.Date, ScreenBax.IdMutexesisler, "1", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
        int index = jTableProqramTeminati.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, 
                    ScreenBax.Date, ScreenBax.IdMutexesisler, "1", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemCetineat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
        int index = jTableMusteridenCavab.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "1", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
        int index = jTableDetalGozleyir.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "1", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
        int index = jTableTemiriMumkunsuz.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "1", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItemCetineat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "4", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat3ActionPerformed

    private void jMenuItemCetineat4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat4ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "5", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat4ActionPerformed

    private void jMenuItemCetineat5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat5ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "6", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat5ActionPerformed

    private void jMenuItemCetineat6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat6ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "7", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat6ActionPerformed

    private void jMenuItemCetineat7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat7ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "8", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat7ActionPerformed

    private void jMenuItemCetineat8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat8ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "4", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat8ActionPerformed

    private void jMenuItemCetineat9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat9ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "5", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat9ActionPerformed

    private void jMenuItemCetineat10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat10ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "6", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat10ActionPerformed

    private void jMenuItemCetineat11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat11ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "7", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat11ActionPerformed

    private void jMenuItemCetineat12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat12ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "8", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat12ActionPerformed

    private void jMenuItemCetineat13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat13ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "3", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat13ActionPerformed

    private void jMenuItemCetineat14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat14ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "5", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat14ActionPerformed

    private void jMenuItemCetineat15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat15ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "6", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat15ActionPerformed

    private void jMenuItemCetineat16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat16ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "7", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat16ActionPerformed

    private void jMenuItemCetineat17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat17ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "8", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat17ActionPerformed

    private void jMenuItemCetineat18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat18ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
        int index = jTableProqramTeminati.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "3", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat18ActionPerformed

    private void jMenuItemCetineat19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat19ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
        int index = jTableProqramTeminati.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "4", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat19ActionPerformed

    private void jMenuItemCetineat20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat20ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
        int index = jTableProqramTeminati.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "6", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat20ActionPerformed

    private void jMenuItemCetineat21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat21ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
        int index = jTableProqramTeminati.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "7", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat21ActionPerformed

    private void jMenuItemCetineat22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat22ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
        int index = jTableProqramTeminati.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "8", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat22ActionPerformed

    private void jMenuItemCetineat23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat23ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
        int index = jTableMusteridenCavab.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "3", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat23ActionPerformed

    private void jMenuItemCetineat24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat24ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
        int index = jTableMusteridenCavab.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "4", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat24ActionPerformed

    private void jMenuItemCetineat25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat25ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
        int index = jTableMusteridenCavab.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "5", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat25ActionPerformed

    private void jMenuItemCetineat26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat26ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
        int index = jTableMusteridenCavab.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "7", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat26ActionPerformed

    private void jMenuItemCetineat27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat27ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
        int index = jTableMusteridenCavab.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "8", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat27ActionPerformed

    private void jMenuItemCetineat28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat28ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
        int index = jTableDetalGozleyir.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "3", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat28ActionPerformed

    private void jMenuItemCetineat29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat29ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
        int index = jTableDetalGozleyir.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "4", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat29ActionPerformed

    private void jMenuItemCetineat30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat30ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
        int index = jTableDetalGozleyir.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "5", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat30ActionPerformed

    private void jMenuItemCetineat31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat31ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
        int index = jTableDetalGozleyir.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "6", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat31ActionPerformed

    private void jMenuItemCetineat32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat32ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
        int index = jTableDetalGozleyir.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "8", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat32ActionPerformed

    private void jMenuItemCetineat33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat33ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
        int index = jTableTemiriMumkunsuz.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "3", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat33ActionPerformed

    private void jMenuItemCetineat34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat34ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
        int index = jTableTemiriMumkunsuz.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "4", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat34ActionPerformed

    private void jMenuItemCetineat35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat35ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
        int index = jTableTemiriMumkunsuz.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "5", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat35ActionPerformed

    private void jMenuItemCetineat36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat36ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
        int index = jTableTemiriMumkunsuz.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "6", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat36ActionPerformed

    private void jMenuItemCetineat37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat37ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
        int index = jTableTemiriMumkunsuz.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "7", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat37ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        ChangePassword d = new ChangePassword(this, rootPaneCheckingEnabled, l);
        d.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        conn.LoginInsertUpdate(new Login(l.idLogin, l.idMutexesis, l.Password, "0"));
        System.exit(0);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jTableTemiriMumkunsuzMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTemiriMumkunsuzMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
            int index = jTableTemiriMumkunsuz.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));

                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableTemiriMumkunsuzMouseClicked

    private void jTableDetalGozleyirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDetalGozleyirMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
            int index = jTableDetalGozleyir.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));

                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableDetalGozleyirMouseClicked

    private void jTableMusteridenCavabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMusteridenCavabMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
            int index = jTableMusteridenCavab.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));

                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableMusteridenCavabMouseClicked

    private void jTableProqramTeminatiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProqramTeminatiMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
            int index = jTableProqramTeminati.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));

                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableProqramTeminatiMouseClicked

    private void jTableSadedetalProblemiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSadedetalProblemiMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
            int index = jTableSadedetalProblemi.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));

                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableSadedetalProblemiMouseClicked

    private void jTableAnakartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAnakartMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
            int index = jTableAnakart.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));

                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableAnakartMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int index = jTable1.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();

                MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                ListOfMutexesislerWork.removeAll(ListOfMutexesislerWork);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jMenuItemCetineat38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat38ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "9", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat38ActionPerformed

    private void jMenuItemCetineat39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat39ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "10", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat39ActionPerformed

    private void jMenuItemCetineat40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat40ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "9", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat40ActionPerformed

    private void jMenuItemCetineat41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat41ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "10", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat41ActionPerformed

    private void jMenuItemCetineat42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat42ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "9", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat42ActionPerformed

    private void jMenuItemCetineat43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat43ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "10", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat43ActionPerformed

    private void jMenuItemCetineat44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat44ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
        int index = jTableProqramTeminati.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "9", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat44ActionPerformed

    private void jMenuItemCetineat45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat45ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
        int index = jTableProqramTeminati.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "10", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat45ActionPerformed

    private void jMenuItemCetineat46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat46ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
        int index = jTableMusteridenCavab.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "9", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat46ActionPerformed

    private void jMenuItemCetineat47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat47ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
        int index = jTableMusteridenCavab.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "10", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat47ActionPerformed

    private void jMenuItemCetineat48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat48ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
        int index = jTableDetalGozleyir.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "9", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat48ActionPerformed

    private void jMenuItemCetineat49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat49ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
        int index = jTableDetalGozleyir.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "10", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat49ActionPerformed

    private void jMenuItemCetineat50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat50ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
        int index = jTableTemiriMumkunsuz.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "9", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat50ActionPerformed

    private void jMenuItemCetineat51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat51ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
        int index = jTableTemiriMumkunsuz.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "10", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat51ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
        int index = jTableKuryerPlataAxtarsin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "1", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItemCetineat52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat52ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
        int index = jTableKuryerPlataAxtarsin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "3", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat52ActionPerformed

    private void jMenuItemCetineat53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat53ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
        int index = jTableKuryerPlataAxtarsin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "4", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat53ActionPerformed

    private void jMenuItemCetineat54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat54ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
        int index = jTableKuryerPlataAxtarsin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "5", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat54ActionPerformed

    private void jMenuItemCetineat55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat55ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
        int index = jTableKuryerPlataAxtarsin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "6", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat55ActionPerformed

    private void jMenuItemCetineat56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat56ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
        int index = jTableKuryerPlataAxtarsin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "7", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat56ActionPerformed

    private void jMenuItemCetineat57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat57ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
        int index = jTableKuryerPlataAxtarsin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "8", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat57ActionPerformed

    private void jMenuItemCetineat58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat58ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
        int index = jTableKuryerPlataAxtarsin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "10", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat58ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
        int index = jTableDiaqnostika.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "1", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItemCetineat59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat59ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
        int index = jTableDiaqnostika.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "3", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat59ActionPerformed

    private void jMenuItemCetineat60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat60ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
        int index = jTableDiaqnostika.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "4", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat60ActionPerformed

    private void jMenuItemCetineat61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat61ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
        int index = jTableDiaqnostika.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "5", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat61ActionPerformed

    private void jMenuItemCetineat62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat62ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
        int index = jTableDiaqnostika.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "6", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat62ActionPerformed

    private void jMenuItemCetineat63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat63ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
        int index = jTableDiaqnostika.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "7", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat63ActionPerformed

    private void jMenuItemCetineat64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat64ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
        int index = jTableDiaqnostika.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "8", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat64ActionPerformed

    private void jMenuItemCetineat65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat65ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
        int index = jTableDiaqnostika.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                    ScreenBax.IdMutexesisler, "9", ScreenBax.IdDaxilOlan));
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat65ActionPerformed

    private void jTableKuryerPlataAxtarsinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKuryerPlataAxtarsinMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
            int index = jTableKuryerPlataAxtarsin.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));

                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableKuryerPlataAxtarsinMouseClicked

    private void jTableDiaqnostikaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDiaqnostikaMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
            int index = jTableDiaqnostika.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));

                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableDiaqnostikaMouseClicked

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                        ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
            Refresh();
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                        ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
            Refresh();
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                        ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
        int index = jTableProqramTeminati.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                        ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
        int index = jTableMusteridenCavab.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                        ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
        int index = jTableDetalGozleyir.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                        ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
        }
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
        int index = jTableTemiriMumkunsuz.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                        ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
        }
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableKuryerPlataAxtarsin.getModel();
        int index = jTableKuryerPlataAxtarsin.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                        ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
        }
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDiaqnostika.getModel();
        int index = jTableDiaqnostika.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {
                conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork, ScreenBax.Date,
                        ScreenBax.IdMutexesisler, "2", ScreenBax.IdDaxilOlan));
            }
        }
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jTableTemirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTemirMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableTemir.getModel();
            int index = jTableTemir.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                DaxilOlan GetDaxilOlan = conn.DaxilOlanFindByIdDaxilOlan(Integer.parseInt(id));
                ScreenBax1 d = new ScreenBax1(this, rootPaneCheckingEnabled, GetDaxilOlan);
                d.setVisible(rootPaneCheckingEnabled);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableTemirMouseClicked

    private void jTableğTehvilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableğTehvilMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableğTehvil.getModel();
            int index = jTableğTehvil.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                DaxilOlan GetDaxilOlan = conn.DaxilOlanFindByIdDaxilOlan(Integer.parseInt(id));
                ScreenBax1 d = new ScreenBax1(this, rootPaneCheckingEnabled, GetDaxilOlan);
                d.setVisible(rootPaneCheckingEnabled);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableğTehvilMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabelTarixsaat;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItemCetineat;
    private javax.swing.JMenuItem jMenuItemCetineat1;
    private javax.swing.JMenuItem jMenuItemCetineat10;
    private javax.swing.JMenuItem jMenuItemCetineat11;
    private javax.swing.JMenuItem jMenuItemCetineat12;
    private javax.swing.JMenuItem jMenuItemCetineat13;
    private javax.swing.JMenuItem jMenuItemCetineat14;
    private javax.swing.JMenuItem jMenuItemCetineat15;
    private javax.swing.JMenuItem jMenuItemCetineat16;
    private javax.swing.JMenuItem jMenuItemCetineat17;
    private javax.swing.JMenuItem jMenuItemCetineat18;
    private javax.swing.JMenuItem jMenuItemCetineat19;
    private javax.swing.JMenuItem jMenuItemCetineat2;
    private javax.swing.JMenuItem jMenuItemCetineat20;
    private javax.swing.JMenuItem jMenuItemCetineat21;
    private javax.swing.JMenuItem jMenuItemCetineat22;
    private javax.swing.JMenuItem jMenuItemCetineat23;
    private javax.swing.JMenuItem jMenuItemCetineat24;
    private javax.swing.JMenuItem jMenuItemCetineat25;
    private javax.swing.JMenuItem jMenuItemCetineat26;
    private javax.swing.JMenuItem jMenuItemCetineat27;
    private javax.swing.JMenuItem jMenuItemCetineat28;
    private javax.swing.JMenuItem jMenuItemCetineat29;
    private javax.swing.JMenuItem jMenuItemCetineat3;
    private javax.swing.JMenuItem jMenuItemCetineat30;
    private javax.swing.JMenuItem jMenuItemCetineat31;
    private javax.swing.JMenuItem jMenuItemCetineat32;
    private javax.swing.JMenuItem jMenuItemCetineat33;
    private javax.swing.JMenuItem jMenuItemCetineat34;
    private javax.swing.JMenuItem jMenuItemCetineat35;
    private javax.swing.JMenuItem jMenuItemCetineat36;
    private javax.swing.JMenuItem jMenuItemCetineat37;
    private javax.swing.JMenuItem jMenuItemCetineat38;
    private javax.swing.JMenuItem jMenuItemCetineat39;
    private javax.swing.JMenuItem jMenuItemCetineat4;
    private javax.swing.JMenuItem jMenuItemCetineat40;
    private javax.swing.JMenuItem jMenuItemCetineat41;
    private javax.swing.JMenuItem jMenuItemCetineat42;
    private javax.swing.JMenuItem jMenuItemCetineat43;
    private javax.swing.JMenuItem jMenuItemCetineat44;
    private javax.swing.JMenuItem jMenuItemCetineat45;
    private javax.swing.JMenuItem jMenuItemCetineat46;
    private javax.swing.JMenuItem jMenuItemCetineat47;
    private javax.swing.JMenuItem jMenuItemCetineat48;
    private javax.swing.JMenuItem jMenuItemCetineat49;
    private javax.swing.JMenuItem jMenuItemCetineat5;
    private javax.swing.JMenuItem jMenuItemCetineat50;
    private javax.swing.JMenuItem jMenuItemCetineat51;
    private javax.swing.JMenuItem jMenuItemCetineat52;
    private javax.swing.JMenuItem jMenuItemCetineat53;
    private javax.swing.JMenuItem jMenuItemCetineat54;
    private javax.swing.JMenuItem jMenuItemCetineat55;
    private javax.swing.JMenuItem jMenuItemCetineat56;
    private javax.swing.JMenuItem jMenuItemCetineat57;
    private javax.swing.JMenuItem jMenuItemCetineat58;
    private javax.swing.JMenuItem jMenuItemCetineat59;
    private javax.swing.JMenuItem jMenuItemCetineat6;
    private javax.swing.JMenuItem jMenuItemCetineat60;
    private javax.swing.JMenuItem jMenuItemCetineat61;
    private javax.swing.JMenuItem jMenuItemCetineat62;
    private javax.swing.JMenuItem jMenuItemCetineat63;
    private javax.swing.JMenuItem jMenuItemCetineat64;
    private javax.swing.JMenuItem jMenuItemCetineat65;
    private javax.swing.JMenuItem jMenuItemCetineat7;
    private javax.swing.JMenuItem jMenuItemCetineat8;
    private javax.swing.JMenuItem jMenuItemCetineat9;
    private javax.swing.JMenu jMenuMember;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenuAnakart;
    private javax.swing.JPopupMenu jPopupMenuDetalGozleyir;
    private javax.swing.JPopupMenu jPopupMenuDiaqnostika;
    private javax.swing.JPopupMenu jPopupMenuKuryerPlataAxtarsin;
    private javax.swing.JPopupMenu jPopupMenuMusteridenCavab;
    private javax.swing.JPopupMenu jPopupMenuProqramTeminati;
    private javax.swing.JPopupMenu jPopupMenuSadedetalProblemi;
    private javax.swing.JPopupMenu jPopupMenuTapşırıq;
    private javax.swing.JPopupMenu jPopupMenuTemiriMumkunsuz;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableAnakart;
    private javax.swing.JTable jTableDetalGozleyir;
    private javax.swing.JTable jTableDiaqnostika;
    private javax.swing.JTable jTableKuryerPlataAxtarsin;
    private javax.swing.JTable jTableMusteridenCavab;
    private javax.swing.JTable jTableProqramTeminati;
    private javax.swing.JTable jTableSadedetalProblemi;
    private javax.swing.JTable jTableTemir;
    private javax.swing.JTable jTableTemiriMumkunsuz;
    private javax.swing.JTable jTableğTehvil;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
