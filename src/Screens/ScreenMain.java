package Screens;

import Entity.Daxilolan;
import Entity.Login;
import Entity.Mutexesisler;
import Entity.Mutexesiswork;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public final class ScreenMain extends javax.swing.JFrame {

    private final EntityManager em;

    Login l;
    private List<Mutexesiswork> ListOfMutexesislerWork;
    private final EntityManagerFactory emf;
    private final Mutexesisler Mutexesis;

    public ScreenMain(Login l) {
        initComponents();
        this.setExtendedState(ScreenMain.MAXIMIZED_BOTH);
        this.l = l;
        emf = Persistence.createEntityManagerFactory("TexnoUSTA_ClientPU");
        em = emf.createEntityManager();

        Mutexesis = em.createNamedQuery("Mutexesisler.findByIdMutexesisler", Mutexesisler.class)
                .setParameter("idMutexesisler", l.getIdMutexesis())
                .getSingleResult();
        jMenuMember.setText(Mutexesis.getAd() + " " + Mutexesis.getSoyad());

        Refresh();

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
                        String TimeBackup = ab.format(ag);
                        jLabelTarixsaat.setText(Date + " " + Time);
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ScreenMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    public void Refresh() {
        ListOfMutexesislerWork = em.createNamedQuery("Mutexesiswork.findByIdMutexesis", Mutexesiswork.class)
                .setParameter("idMutexesisler", em.find(Mutexesisler.class, l.getIdMutexesis()))
                .getResultList();
        FillTheTableGonderilen();
        FillTheTableAnakart();
        FillTheTableSadeDetalProblemi();
        FillTheTableProqramTeminati();
        FillTheTableMusteridencavab();
        FillTheTabledetalGozleyir();
        FillTheTableTemiriMumkunsuz();
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
        tmodel.addColumn("Mütəxəsis");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");
        tmodel.addColumn("Saatı");

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setRowHeight(20);
        jTable1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTable1.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");

            if (b.getIdDaxilOlan().getIsActive().equals("6")) {
                if (b.getStatus().equals("1")) {
                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.getIdMutexesisWork(),
                        b.getIdDaxilOlan().getIdDaxilOlan(),
                        b.getIdDaxilOlan().getAd() + " " + b.getIdDaxilOlan().getSoyad(),
                        b.getIdDaxilOlan().getTelefon(),
                        b.getIdDaxilOlan().getModel() + " " + b.getIdDaxilOlan().getMarka(),
                        b.getIdMutexesisler().getAd() + " " + b.getIdMutexesisler().getSoyad(),
                        b.getIdDaxilOlan().getDatePlan(),
                        d.format(b.getDate()),
                        s.format(b.getDate()),});
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
        tmodel.addColumn("Mütəxəsis");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");
        tmodel.addColumn("Saatı");

        jTableAnakart.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableAnakart.setRowHeight(20);
        jTableAnakart.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableAnakart.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");

            if (b.getIdDaxilOlan().getIsActive().equals("6")) {
                if (b.getStatus().equals("3")) {
                    tmodel.insertRow(jTableAnakart.getRowCount(), new Object[]{
                        b.getIdMutexesisWork(),
                        b.getIdDaxilOlan().getIdDaxilOlan(),
                        b.getIdDaxilOlan().getAd() + " " + b.getIdDaxilOlan().getSoyad(),
                        b.getIdDaxilOlan().getTelefon(),
                        b.getIdDaxilOlan().getModel() + " " + b.getIdDaxilOlan().getMarka(),
                        b.getIdMutexesisler().getAd() + " " + b.getIdMutexesisler().getSoyad(),
                        b.getIdDaxilOlan().getDatePlan(),
                        d.format(b.getDate()),
                        s.format(b.getDate()),});
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
        tmodel.addColumn("Mütəxəsis");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");
        tmodel.addColumn("Saatı");

        jTableSadedetalProblemi.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableSadedetalProblemi.setRowHeight(20);
        jTableSadedetalProblemi.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableSadedetalProblemi.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");

            if (b.getIdDaxilOlan().getIsActive().equals("6")) {
                if (b.getStatus().equals("4")) {
                    tmodel.insertRow(jTableSadedetalProblemi.getRowCount(), new Object[]{
                        b.getIdMutexesisWork(),
                        b.getIdDaxilOlan().getIdDaxilOlan(),
                        b.getIdDaxilOlan().getAd() + " " + b.getIdDaxilOlan().getSoyad(),
                        b.getIdDaxilOlan().getTelefon(),
                        b.getIdDaxilOlan().getModel() + " " + b.getIdDaxilOlan().getMarka(),
                        b.getIdMutexesisler().getAd() + " " + b.getIdMutexesisler().getSoyad(),
                        b.getIdDaxilOlan().getDatePlan(),
                        d.format(b.getDate()),
                        s.format(b.getDate()),});
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
        tmodel.addColumn("Mütəxəsis");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");
        tmodel.addColumn("Saatı");

        jTableProqramTeminati.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableProqramTeminati.setRowHeight(20);
        jTableProqramTeminati.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableProqramTeminati.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");

            if (b.getIdDaxilOlan().getIsActive().equals("6")) {
                if (b.getStatus().equals("5")) {
                    tmodel.insertRow(jTableProqramTeminati.getRowCount(), new Object[]{
                        b.getIdMutexesisWork(),
                        b.getIdDaxilOlan().getIdDaxilOlan(),
                        b.getIdDaxilOlan().getAd() + " " + b.getIdDaxilOlan().getSoyad(),
                        b.getIdDaxilOlan().getTelefon(),
                        b.getIdDaxilOlan().getModel() + " " + b.getIdDaxilOlan().getMarka(),
                        b.getIdMutexesisler().getAd() + " " + b.getIdMutexesisler().getSoyad(),
                        b.getIdDaxilOlan().getDatePlan(),
                        d.format(b.getDate()),
                        s.format(b.getDate()),});
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
        tmodel.addColumn("Mütəxəsis");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");
        tmodel.addColumn("Saatı");

        jTableMusteridenCavab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableMusteridenCavab.setRowHeight(20);
        jTableMusteridenCavab.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableMusteridenCavab.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");

            if (b.getIdDaxilOlan().getIsActive().equals("6")) {
                if (b.getStatus().equals("6")) {
                    tmodel.insertRow(jTableMusteridenCavab.getRowCount(), new Object[]{
                        b.getIdMutexesisWork(),
                        b.getIdDaxilOlan().getIdDaxilOlan(),
                        b.getIdDaxilOlan().getAd() + " " + b.getIdDaxilOlan().getSoyad(),
                        b.getIdDaxilOlan().getTelefon(),
                        b.getIdDaxilOlan().getModel() + " " + b.getIdDaxilOlan().getMarka(),
                        b.getIdMutexesisler().getAd() + " " + b.getIdMutexesisler().getSoyad(),
                        b.getIdDaxilOlan().getDatePlan(),
                        d.format(b.getDate()),
                        s.format(b.getDate()),});
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
        tmodel.addColumn("Mütəxəsis");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");
        tmodel.addColumn("Saatı");

        jTableDetalGozleyir.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableDetalGozleyir.setRowHeight(20);
        jTableDetalGozleyir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableDetalGozleyir.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");

            if (b.getIdDaxilOlan().getIsActive().equals("6")) {
                if (b.getStatus().equals("7")) {
                    tmodel.insertRow(jTableDetalGozleyir.getRowCount(), new Object[]{
                        b.getIdMutexesisWork(),
                        b.getIdDaxilOlan().getIdDaxilOlan(),
                        b.getIdDaxilOlan().getAd() + " " + b.getIdDaxilOlan().getSoyad(),
                        b.getIdDaxilOlan().getTelefon(),
                        b.getIdDaxilOlan().getModel() + " " + b.getIdDaxilOlan().getMarka(),
                        b.getIdMutexesisler().getAd() + " " + b.getIdMutexesisler().getSoyad(),
                        b.getIdDaxilOlan().getDatePlan(),
                        d.format(b.getDate()),
                        s.format(b.getDate()),});
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
        tmodel.addColumn("Mütəxəsis");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");
        tmodel.addColumn("Saatı");

        jTableTemiriMumkunsuz.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableTemiriMumkunsuz.setRowHeight(20);
        jTableTemiriMumkunsuz.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTableTemiriMumkunsuz.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");

            if (b.getIdDaxilOlan().getIsActive().equals("6")) {
                if (b.getStatus().equals("8")) {
                    tmodel.insertRow(jTableTemiriMumkunsuz.getRowCount(), new Object[]{
                        b.getIdMutexesisWork(),
                        b.getIdDaxilOlan().getIdDaxilOlan(),
                        b.getIdDaxilOlan().getAd() + " " + b.getIdDaxilOlan().getSoyad(),
                        b.getIdDaxilOlan().getTelefon(),
                        b.getIdDaxilOlan().getModel() + " " + b.getIdDaxilOlan().getMarka(),
                        b.getIdMutexesisler().getAd() + " " + b.getIdMutexesisler().getSoyad(),
                        b.getIdDaxilOlan().getDatePlan(),
                        d.format(b.getDate()),
                        s.format(b.getDate()),});
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
        jPopupMenuAnakart = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemCetineat8 = new javax.swing.JMenuItem();
        jMenuItemCetineat9 = new javax.swing.JMenuItem();
        jMenuItemCetineat10 = new javax.swing.JMenuItem();
        jMenuItemCetineat11 = new javax.swing.JMenuItem();
        jMenuItemCetineat12 = new javax.swing.JMenuItem();
        jPopupMenuSadedetalProblemi = new javax.swing.JPopupMenu();
        jMenuItemCetineat1 = new javax.swing.JMenuItem();
        jMenuItemCetineat13 = new javax.swing.JMenuItem();
        jMenuItemCetineat14 = new javax.swing.JMenuItem();
        jMenuItemCetineat15 = new javax.swing.JMenuItem();
        jMenuItemCetineat16 = new javax.swing.JMenuItem();
        jMenuItemCetineat17 = new javax.swing.JMenuItem();
        jPopupMenuProqramTeminati = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItemCetineat18 = new javax.swing.JMenuItem();
        jMenuItemCetineat19 = new javax.swing.JMenuItem();
        jMenuItemCetineat20 = new javax.swing.JMenuItem();
        jMenuItemCetineat21 = new javax.swing.JMenuItem();
        jMenuItemCetineat22 = new javax.swing.JMenuItem();
        jPopupMenuMusteridenCavab = new javax.swing.JPopupMenu();
        jMenuItemCetineat2 = new javax.swing.JMenuItem();
        jMenuItemCetineat23 = new javax.swing.JMenuItem();
        jMenuItemCetineat24 = new javax.swing.JMenuItem();
        jMenuItemCetineat25 = new javax.swing.JMenuItem();
        jMenuItemCetineat26 = new javax.swing.JMenuItem();
        jMenuItemCetineat27 = new javax.swing.JMenuItem();
        jPopupMenuDetalGozleyir = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItemCetineat28 = new javax.swing.JMenuItem();
        jMenuItemCetineat29 = new javax.swing.JMenuItem();
        jMenuItemCetineat30 = new javax.swing.JMenuItem();
        jMenuItemCetineat31 = new javax.swing.JMenuItem();
        jMenuItemCetineat32 = new javax.swing.JMenuItem();
        jPopupMenuTemiriMumkunsuz = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItemCetineat33 = new javax.swing.JMenuItem();
        jMenuItemCetineat34 = new javax.swing.JMenuItem();
        jMenuItemCetineat35 = new javax.swing.JMenuItem();
        jMenuItemCetineat36 = new javax.swing.JMenuItem();
        jMenuItemCetineat37 = new javax.swing.JMenuItem();
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
        jLabelTarixsaat = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemDaxilOlanNov = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
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
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
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
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  Təmiri mümkünsüz  ", jPanel7);

        jLabelTarixsaat.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTarixsaat.setText(" ");

        jMenu1.setBackground(new java.awt.Color(255, 102, 255));
        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem5.setText("Mutexesislerimiz");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Detallar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("Kassa");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setText("Çıxış");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(255, 102, 255));
        jMenu2.setText("Düzəliş");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItemDaxilOlanNov.setText("Daxil Olan Növ");
        jMenuItemDaxilOlanNov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDaxilOlanNovActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemDaxilOlanNov);

        jMenuItem9.setText("Yenilə");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayarlar");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem10.setText("Arxivləşdir");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem11.setText("Geri qaytar");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuBar1.add(jMenu3);

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
                    .addComponent(jTabbedPane1)
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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int index = jTable1.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                        .setParameter("idMutexesisWork", Integer.parseInt(id))
                        .getSingleResult();
                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        ListOfMutexesislerWork = em.createNamedQuery("Mutexesiswork.findByIdMutexesis", Mutexesiswork.class)
                .setParameter("idMutexesisler", em.find(Mutexesisler.class, l.getIdMutexesis()))
                .getResultList();
        Refresh();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();
            ScreenChangeToTemirEdilmis j = new ScreenChangeToTemirEdilmis(this, rootPaneCheckingEnabled, ScreenBax);
            j.setVisible(true);

            if (ScreenChangeToTemirEdilmis.Status == 1) {

                Mutexesiswork f = new Mutexesiswork();
                f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
                f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
                f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
                f.setStatus("2");
                f.setDate(ScreenBax.getDate());
                em.merge(f);
                em.getTransaction().begin();
                em.getTransaction().commit();
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
            Mutexesiswork S = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Daxilolan d = new Daxilolan(S.getIdDaxilOlan().getIdDaxilOlan());
            d.setAd(S.getIdDaxilOlan().getAd());
            d.setSoyad(S.getIdDaxilOlan().getSoyad());
            d.setTelefon(S.getIdDaxilOlan().getTelefon());
            d.setIdDaxilOlanNov(S.getIdDaxilOlan().getIdDaxilOlanNov());
            d.setModel(S.getIdDaxilOlan().getModel());
            d.setMarka(S.getIdDaxilOlan().getMarka());
            d.setAksesuar(S.getIdDaxilOlan().getAksesuar());
            d.setProblem(S.getIdDaxilOlan().getProblem());
            d.setNetice(S.getIdDaxilOlan().getNetice());
            d.setQeyd(S.getIdDaxilOlan().getQeyd());
            d.setDate(S.getIdDaxilOlan().getDate());
            d.setIsActive("1");
            d.setDatePlan(S.getIdDaxilOlan().getDatePlan());
            d.setDateTemir(S.getIdDaxilOlan().getDateTemir());
            d.setDateTehvil(S.getIdDaxilOlan().getDateTehvil());
            d.setGy(S.getIdDaxilOlan().getGy());

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(S.getIdMutexesisWork());
            f.setIdDaxilOlan(S.getIdDaxilOlan());
            f.setIdMutexesisler(S.getIdMutexesisler());
            f.setStatus("2");
            f.setDate(S.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.merge(d);
            em.getTransaction().begin();
            em.getTransaction().commit();
        }
        Refresh();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Login d = new Login(l.getIdLogin());
        d.setIdMutexesis(l.getIdMutexesis());
        d.setPassword(l.getPassword());
        d.setStatus("0");
        em.merge(d);
        em.getTransaction().begin();
        em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("3");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineatActionPerformed

    private void jTableAnakartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAnakartMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
            int index = jTableAnakart.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                        .setParameter("idMutexesisWork", Integer.parseInt(id))
                        .getSingleResult();
                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableAnakartMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableAnakart.getModel();
        int index = jTableAnakart.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("1");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
            Refresh();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTableSadedetalProblemiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSadedetalProblemiMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
            int index = jTableSadedetalProblemi.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                        .setParameter("idMutexesisWork", Integer.parseInt(id))
                        .getSingleResult();
                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableSadedetalProblemiMouseClicked

    private void jTableProqramTeminatiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProqramTeminatiMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableProqramTeminati.getModel();
            int index = jTableProqramTeminati.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                        .setParameter("idMutexesisWork", Integer.parseInt(id))
                        .getSingleResult();
                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableProqramTeminatiMouseClicked

    private void jTableMusteridenCavabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMusteridenCavabMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableMusteridenCavab.getModel();
            int index = jTableMusteridenCavab.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                        .setParameter("idMutexesisWork", Integer.parseInt(id))
                        .getSingleResult();
                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableMusteridenCavabMouseClicked

    private void jTableDetalGozleyirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDetalGozleyirMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableDetalGozleyir.getModel();
            int index = jTableDetalGozleyir.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                        .setParameter("idMutexesisWork", Integer.parseInt(id))
                        .getSingleResult();
                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableDetalGozleyirMouseClicked

    private void jTableTemiriMumkunsuzMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTemiriMumkunsuzMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultTableModel model = (DefaultTableModel) jTableTemiriMumkunsuz.getModel();
            int index = jTableTemiriMumkunsuz.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

            } else {
                String id = model.getValueAt(index, 0).toString();
                Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                        .setParameter("idMutexesisWork", Integer.parseInt(id))
                        .getSingleResult();
                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
                Refresh();
            }
        }
    }//GEN-LAST:event_jTableTemiriMumkunsuzMouseClicked

    private void jMenuItemCetineat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSadedetalProblemi.getModel();
        int index = jTableSadedetalProblemi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("1");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("1");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("1");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("1");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("1");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("4");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("5");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("6");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("7");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("8");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("4");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("5");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("6");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("7");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("8");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("3");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("5");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("6");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("7");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("8");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("3");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("4");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("6");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("7");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("8");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("3");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("4");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("5");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("7");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("8");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("3");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("4");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("5");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("6");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("8");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("3");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("4");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("5");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("6");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
            Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();

            Mutexesiswork f = new Mutexesiswork();
            f.setIdMutexesisWork(ScreenBax.getIdMutexesisWork());
            f.setIdDaxilOlan(ScreenBax.getIdDaxilOlan());
            f.setIdMutexesisler(ScreenBax.getIdMutexesisler());
            f.setStatus("7");
            f.setDate(ScreenBax.getDate());
            em.merge(f);
            em.getTransaction().begin();
            em.getTransaction().commit();
            Refresh();
        }
    }//GEN-LAST:event_jMenuItemCetineat37ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
//        MutexesislerMain d = new MutexesislerMain();
//        d.setVisible(rootPaneCheckingEnabled);
//        Refresh();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
//        DetallarMain d = new DetallarMain();
//        d.setVisible(rootPaneCheckingEnabled);
//        Refresh();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
//        Logins l = new Logins();
//        l.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItemDaxilOlanNovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDaxilOlanNovActionPerformed
//        ScreenDaxilOlanNov d = new ScreenDaxilOlanNov(this, rootPaneCheckingEnabled);
//        d.setVisible(true);
//        Refresh();
    }//GEN-LAST:event_jMenuItemDaxilOlanNovActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        Refresh();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
//        ScreenBackup d = new ScreenBackup(this, rootPaneCheckingEnabled);
//        d.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
//        ScreenRestore d = new ScreenRestore(this, rootPaneCheckingEnabled);
//        d.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        ChangePassword d = new ChangePassword(this, rootPaneCheckingEnabled, l);
        d.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
//        conn.InsertUpdateLogin(new Login(1, l.idMutexesis, l.Password, "0"));
//        System.exit(0);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabelTarixsaat;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
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
    private javax.swing.JMenuItem jMenuItemCetineat4;
    private javax.swing.JMenuItem jMenuItemCetineat5;
    private javax.swing.JMenuItem jMenuItemCetineat6;
    private javax.swing.JMenuItem jMenuItemCetineat7;
    private javax.swing.JMenuItem jMenuItemCetineat8;
    private javax.swing.JMenuItem jMenuItemCetineat9;
    private javax.swing.JMenuItem jMenuItemDaxilOlanNov;
    private javax.swing.JMenu jMenuMember;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenuAnakart;
    private javax.swing.JPopupMenu jPopupMenuDetalGozleyir;
    private javax.swing.JPopupMenu jPopupMenuMusteridenCavab;
    private javax.swing.JPopupMenu jPopupMenuProqramTeminati;
    private javax.swing.JPopupMenu jPopupMenuSadedetalProblemi;
    private javax.swing.JPopupMenu jPopupMenuTapşırıq;
    private javax.swing.JPopupMenu jPopupMenuTemiriMumkunsuz;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableAnakart;
    private javax.swing.JTable jTableDetalGozleyir;
    private javax.swing.JTable jTableMusteridenCavab;
    private javax.swing.JTable jTableProqramTeminati;
    private javax.swing.JTable jTableSadedetalProblemi;
    private javax.swing.JTable jTableTemiriMumkunsuz;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
