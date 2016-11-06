package Screens;

import ConnectionDB.ConnMySql;
import Object.Login;
import Object.DaxilOlan;
import Object.Tehvil;
import Object.Temir;
import Object.MutexesisWork;
import java.awt.AWTException;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
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

        Refresh(false, "");
    }

    public void Refresh(boolean f, String Text) {
        if (f == true) {
            ListOfMutexesislerWork = conn.MutexesisWorkFindBySetName(Text, jTextField1.getText());
        } else {
            ListOfMutexesislerWork = conn.MutexesisWorkFindListByIdMutexesisler(l.idMutexesis);
        }
        FillTheTableGonderilen();
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
        tmodel.addColumn("Status");

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setRowHeight(20);
        jTable1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTable1.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((MutexesisWork b) -> {
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
                        d.format(b.Date),
                        "Tapşırıq"
                    });
                } else if (b.Status.equals("3")) {

                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),
                        "Ana Kart"
                    });
                } else if (b.Status.equals("4")) {

                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),
                        "Sadə Detal Problemi"
                    });
                } else if (b.Status.equals("5")) {

                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),
                        "Proqram Təminatı"
                    });
                } else if (b.Status.equals("6")) {

                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),
                        "Müştəridən Cavab"
                    });
                } else if (b.Status.equals("7")) {

                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),
                        "Detal Gözləyir"
                    });
                } else if (b.Status.equals("8")) {

                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),
                        "Təmiri Mümkünsüz"
                    });
                } else if (b.Status.equals("9")) {

                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),
                        "Kuryer Plata Axtarsın"
                    });
                } else if (b.Status.equals("10")) {

                    tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                        b.IdMutexesisWork,
                        DaxilolanSingle.idDaxilOlan,
                        DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                        DaxilolanSingle.Telefon,
                        DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                        DaxilolanSingle.DatePlan,
                        d.format(b.Date),
                        "Diaqnostika"
                    });
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuTapşırıq = new javax.swing.JPopupMenu();
        jMenuItemCetineat1 = new javax.swing.JMenuItem();
        jMenuItemCetineat = new javax.swing.JMenuItem();
        jMenuItemCetineat3 = new javax.swing.JMenuItem();
        jMenuItemCetineat4 = new javax.swing.JMenuItem();
        jMenuItemCetineat5 = new javax.swing.JMenuItem();
        jMenuItemCetineat6 = new javax.swing.JMenuItem();
        jMenuItemCetineat7 = new javax.swing.JMenuItem();
        jMenuItemCetineat38 = new javax.swing.JMenuItem();
        jMenuItemCetineat39 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jComboBoxFilter = new javax.swing.JComboBox<>();
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

        jMenuItemCetineat1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat1.setText("Tapşırıq");
        jMenuItemCetineat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineat1ActionPerformed(evt);
            }
        });
        jPopupMenuTapşırıq.add(jMenuItemCetineat1);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setEnabled(false);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Refresh_52.png"))); // NOI18N
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

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Maintenance-52.png"))); // NOI18N
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

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Cancel-52.png"))); // NOI18N
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

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Exit-52.png"))); // NOI18N
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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Message-52.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Ad", "Soyad", "Telefon" }));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBoxFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ümumi", "Tapşırıq", "Ana Kart", "Sadə Detal Problemi", "Proqram Təminatı", "Müştəridən Cavab", "Detal Gözləyir", "Təmiri Mümkünsüz", "Kuryer Plata Axtarsın", "Diaqnostika" }));
        jComboBoxFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("  Tapşırıq  ", jPanel1);

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
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
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
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Təhvil verdiklərim", jPanel10);

        jLabelTarixsaat.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTarixsaat.setText(" ");

        jMenuMember.setText(" ");
        jMenuMember.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem12.setText("Parolu dəyiş");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenuMember.add(jMenuItem12);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Refresh(false, "");
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
            Refresh(false, "");
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
                    GetDaxilOlan.Netice, GetDaxilOlan.Qeyd, GetDaxilOlan.Date, "1",
                    GetDaxilOlan.DatePlan, GetDaxilOlan.DateTemir, GetDaxilOlan.DateTehvil, GetDaxilOlan.GY));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(S.IdMutexesisWork,
                    S.Date, S.IdMutexesisler, "2", S.IdDaxilOlan));
        }
        Refresh(false, "");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        conn.LoginInsertUpdate(new Login(l.idLogin, l.idMutexesis, l.Password, "0"));
        System.exit(0);
    }//GEN-LAST:event_jButton14ActionPerformed

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
            Refresh(false, "");
        }
    }//GEN-LAST:event_jMenuItemCetineatActionPerformed

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
            Refresh(false, "");
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
            Refresh(false, "");
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
            Refresh(false, "");
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
            Refresh(false, "");
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
            Refresh(false, "");
        }
    }//GEN-LAST:event_jMenuItemCetineat7ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        ChangePassword d = new ChangePassword(this, rootPaneCheckingEnabled, l);
        d.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        conn.LoginInsertUpdate(new Login(l.idLogin, l.idMutexesis, l.Password, "0"));
        System.exit(0);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

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
                Refresh(false, "");
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
            Refresh(false, "");
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
            Refresh(false, "");
        }
    }//GEN-LAST:event_jMenuItemCetineat39ActionPerformed

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
            Refresh(false, "");
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

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
                Refresh(false, "");
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
                Refresh(false, "");
            }
        }
    }//GEN-LAST:event_jTableğTehvilMouseClicked

    private void jMenuItemCetineat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCetineat1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            MutexesisWork ScreenBax = conn.MutexesisWorkFindByIdMutexesisWork(Integer.parseInt(id));
            conn.MutexesisWorkInsertUpdate(new MutexesisWork(ScreenBax.IdMutexesisWork,
                    ScreenBax.Date, ScreenBax.IdMutexesisler, "1", ScreenBax.IdDaxilOlan));
            Refresh(false, "");
        }
    }//GEN-LAST:event_jMenuItemCetineat1ActionPerformed

    private void jComboBoxFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFilterActionPerformed
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("ID Daxil Olan");
        tmodel.addColumn("Ad Soyad");
        tmodel.addColumn("Telefon");
        tmodel.addColumn("Model Marka");
        tmodel.addColumn("Planlaşdırılmış Tarix");
        tmodel.addColumn("Tapşırıq Tarixi");
        tmodel.addColumn("Status");

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setRowHeight(20);
        jTable1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTable1.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((MutexesisWork b) -> {
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            DaxilOlan DaxilolanSingle = conn.DaxilOlanFindByIdDaxilOlan(b.IdDaxilOlan);

            switch (jComboBoxFilter.getSelectedIndex()) {
                case 0:
                    if (DaxilolanSingle.isActive.equals("6")) {
                        if (b.Status.equals("1")) {

                            tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                                b.IdMutexesisWork,
                                DaxilolanSingle.idDaxilOlan,
                                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                                DaxilolanSingle.Telefon,
                                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                                DaxilolanSingle.DatePlan,
                                d.format(b.Date),
                                "Tapşırıq"
                            });
                        } else if (b.Status.equals("3")) {

                            tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                                b.IdMutexesisWork,
                                DaxilolanSingle.idDaxilOlan,
                                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                                DaxilolanSingle.Telefon,
                                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                                DaxilolanSingle.DatePlan,
                                d.format(b.Date),
                                "Ana Kart"
                            });
                        } else if (b.Status.equals("4")) {

                            tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                                b.IdMutexesisWork,
                                DaxilolanSingle.idDaxilOlan,
                                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                                DaxilolanSingle.Telefon,
                                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                                DaxilolanSingle.DatePlan,
                                d.format(b.Date),
                                "Sadə Detal Problemi"
                            });
                        } else if (b.Status.equals("5")) {

                            tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                                b.IdMutexesisWork,
                                DaxilolanSingle.idDaxilOlan,
                                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                                DaxilolanSingle.Telefon,
                                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                                DaxilolanSingle.DatePlan,
                                d.format(b.Date),
                                "Proqram Təminatı"
                            });
                        } else if (b.Status.equals("6")) {

                            tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                                b.IdMutexesisWork,
                                DaxilolanSingle.idDaxilOlan,
                                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                                DaxilolanSingle.Telefon,
                                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                                DaxilolanSingle.DatePlan,
                                d.format(b.Date),
                                "Müştəridən Cavab"
                            });
                        } else if (b.Status.equals("7")) {

                            tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                                b.IdMutexesisWork,
                                DaxilolanSingle.idDaxilOlan,
                                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                                DaxilolanSingle.Telefon,
                                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                                DaxilolanSingle.DatePlan,
                                d.format(b.Date),
                                "Detal Gözləyir"
                            });
                        } else if (b.Status.equals("8")) {

                            tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                                b.IdMutexesisWork,
                                DaxilolanSingle.idDaxilOlan,
                                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                                DaxilolanSingle.Telefon,
                                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                                DaxilolanSingle.DatePlan,
                                d.format(b.Date),
                                "Təmiri Mümkünsüz"
                            });
                        } else if (b.Status.equals("9")) {

                            tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                                b.IdMutexesisWork,
                                DaxilolanSingle.idDaxilOlan,
                                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                                DaxilolanSingle.Telefon,
                                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                                DaxilolanSingle.DatePlan,
                                d.format(b.Date),
                                "Kuryer Plata Axtarsın"
                            });
                        } else if (b.Status.equals("10")) {

                            tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                                b.IdMutexesisWork,
                                DaxilolanSingle.idDaxilOlan,
                                DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                                DaxilolanSingle.Telefon,
                                DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                                DaxilolanSingle.DatePlan,
                                d.format(b.Date),
                                "Diaqnostika"
                            });
                        }

                    }
                    break;
                case 1:
                    if (b.Status.equals("1")) {
                        tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                            b.IdMutexesisWork,
                            DaxilolanSingle.idDaxilOlan,
                            DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                            DaxilolanSingle.Telefon,
                            DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                            DaxilolanSingle.DatePlan,
                            d.format(b.Date),
                            "Tapşırıq"
                        });
                    }
                    break;
                case 2:
                    if (b.Status.equals("3")) {

                        tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                            b.IdMutexesisWork,
                            DaxilolanSingle.idDaxilOlan,
                            DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                            DaxilolanSingle.Telefon,
                            DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                            DaxilolanSingle.DatePlan,
                            d.format(b.Date),
                            "Ana Kart"
                        });
                    }
                    break;
                case 3:
                    if (b.Status.equals("4")) {

                        tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                            b.IdMutexesisWork,
                            DaxilolanSingle.idDaxilOlan,
                            DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                            DaxilolanSingle.Telefon,
                            DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                            DaxilolanSingle.DatePlan,
                            d.format(b.Date),
                            "Sadə Detal Problemi"
                        });
                    }
                    break;
                case 4:
                    if (b.Status.equals("5")) {

                        tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                            b.IdMutexesisWork,
                            DaxilolanSingle.idDaxilOlan,
                            DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                            DaxilolanSingle.Telefon,
                            DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                            DaxilolanSingle.DatePlan,
                            d.format(b.Date),
                            "Proqram Təminatı"
                        });
                    }
                    break;
                case 5:
                    if (b.Status.equals("6")) {

                        tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                            b.IdMutexesisWork,
                            DaxilolanSingle.idDaxilOlan,
                            DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                            DaxilolanSingle.Telefon,
                            DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                            DaxilolanSingle.DatePlan,
                            d.format(b.Date),
                            "Müştəridən Cavab"
                        });
                    }
                    break;
                case 6:
                    if (b.Status.equals("7")) {

                        tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                            b.IdMutexesisWork,
                            DaxilolanSingle.idDaxilOlan,
                            DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                            DaxilolanSingle.Telefon,
                            DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                            DaxilolanSingle.DatePlan,
                            d.format(b.Date),
                            "Detal Gözləyir"
                        });
                    }
                    break;
                case 7:
                    if (b.Status.equals("8")) {

                        tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                            b.IdMutexesisWork,
                            DaxilolanSingle.idDaxilOlan,
                            DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                            DaxilolanSingle.Telefon,
                            DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                            DaxilolanSingle.DatePlan,
                            d.format(b.Date),
                            "Təmiri Mümkünsüz"
                        });
                    }
                    break;
                case 8:
                    if (b.Status.equals("9")) {

                        tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                            b.IdMutexesisWork,
                            DaxilolanSingle.idDaxilOlan,
                            DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                            DaxilolanSingle.Telefon,
                            DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                            DaxilolanSingle.DatePlan,
                            d.format(b.Date),
                            "Kuryer Plata Axtarsın"
                        });
                    }
                    break;
                case 9:
                    if (b.Status.equals("10")) {

                        tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                            b.IdMutexesisWork,
                            DaxilolanSingle.idDaxilOlan,
                            DaxilolanSingle.Ad + " " + DaxilolanSingle.Soyad,
                            DaxilolanSingle.Telefon,
                            DaxilolanSingle.Model + " " + DaxilolanSingle.Marka,
                            DaxilolanSingle.DatePlan,
                            d.format(b.Date),
                            "Diaqnostika"
                        });
                    }
                    break;
                default:
                    break;
            }
        });
    }//GEN-LAST:event_jComboBoxFilterActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (jTextField1.getText().trim().length() == 0) {
            Refresh(false, "");
        } else {
            if (jComboBox1.getSelectedIndex() == 0) {
                Refresh(true, "idDaxilOlan");
            }
//            else if (jComboBox1.getSelectedIndex() == 1) {
//                Refresh(true, "Ad");
//            } else if (jComboBox1.getSelectedIndex() == 2) {
//                Refresh(true, "Soyad");
//            } else if (jComboBox1.getSelectedIndex() == 3) {
//                Refresh(true, "Telefon");
//            }
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ScreenMessage d = new ScreenMessage(this, rootPaneCheckingEnabled, l);
        d.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxFilter;
    private javax.swing.JLabel jLabelTarixsaat;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItemCetineat;
    private javax.swing.JMenuItem jMenuItemCetineat1;
    private javax.swing.JMenuItem jMenuItemCetineat3;
    private javax.swing.JMenuItem jMenuItemCetineat38;
    private javax.swing.JMenuItem jMenuItemCetineat39;
    private javax.swing.JMenuItem jMenuItemCetineat4;
    private javax.swing.JMenuItem jMenuItemCetineat5;
    private javax.swing.JMenuItem jMenuItemCetineat6;
    private javax.swing.JMenuItem jMenuItemCetineat7;
    private javax.swing.JMenu jMenuMember;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPopupMenu jPopupMenuTapşırıq;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableTemir;
    private javax.swing.JTable jTableğTehvil;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
