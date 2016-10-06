/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import Entity.Daxilolan;
import Entity.Login;
import Entity.Mutexesisler;
import Entity.Mutexesiswork;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pallas
 */
public final class ScreenMain extends javax.swing.JFrame {

    private final EntityManager em;

    /**
     * Creates new form ScreenMain
     */
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
        ListOfMutexesislerWork = em.createNamedQuery("Mutexesiswork.findByIdMutexesis", Mutexesiswork.class)
                .setParameter("idMutexesisler", em.find(Mutexesisler.class, l.getIdMutexesis()))
                .getResultList();

        Mutexesis = em.createNamedQuery("Mutexesisler.findByIdMutexesisler", Mutexesisler.class)
                .setParameter("idMutexesisler", l.getIdMutexesis())
                .getSingleResult();
        jLabel1.setText(Mutexesis.getAd() + " " + Mutexesis.getSoyad());
        Refresh();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int a = ListOfMutexesislerWork.size();

                        ListOfMutexesislerWork = em.createNamedQuery("Mutexesiswork.findByIdMutexesis", Mutexesiswork.class)
                                .setParameter("idMutexesisler", em.find(Mutexesisler.class, l.getIdMutexesis()))
                                .getResultList();
                        if (ListOfMutexesislerWork.size() > a) {
                            Refresh();
                        }

                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ScreenMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    public void Refresh() {
        FillTheTableGonderilen();
        FillTheTableCetin();
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
                        s.format(b.getDate()),
                    });
                }
            }
        });
    }

    private void FillTheTableCetin() {
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
        
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setRowHeight(20);
        jTable2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTable2.setModel(tmodel);

        ListOfMutexesislerWork.stream().forEach((b) -> {
            SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");

            if (b.getIdDaxilOlan().getIsActive().equals("6")) {
                if (b.getStatus().equals("3")) {
                    tmodel.insertRow(jTable2.getRowCount(), new Object[]{
                        b.getIdMutexesisWork(),
                        b.getIdDaxilOlan().getIdDaxilOlan(),
                        b.getIdDaxilOlan().getAd() + " " + b.getIdDaxilOlan().getSoyad(),
                        b.getIdDaxilOlan().getTelefon(),
                        b.getIdDaxilOlan().getModel() + " " + b.getIdDaxilOlan().getMarka(),
                        b.getIdMutexesisler().getAd() + " " + b.getIdMutexesisler().getSoyad(),
                        b.getIdDaxilOlan().getDatePlan(),
                        d.format(b.getDate()),
                        s.format(b.getDate()),
                    });
                }
            }
        });
    }
//

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemCetineat = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jMenuItemCetineat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItemCetineat.setText("Çətin Tapşırığa yönəlt");
        jMenuItemCetineat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCetineatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemCetineat);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuItem1.setText("Tapşırığa yönəlt");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText(" ");

        jToolBar1.setRollover(true);
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

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Alien-wait-icon.png"))); // NOI18N
        jButton12.setToolTipText("Detal gözləyir");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton12);

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

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/connect-icon.png"))); // NOI18N
        jButton15.setToolTipText("Yeniden qosul");
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton15);

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tapşırıq", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setComponentPopupMenu(jPopupMenu2);
        jTable2.setGridColor(new java.awt.Color(153, 153, 153));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Çətin tapşırıqlar", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                Refresh();
                Mutexesiswork ScreenBax = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                        .setParameter("idMutexesisWork", Integer.parseInt(id))
                        .getSingleResult();
                ScreenBax j = new ScreenBax(this, rootPaneCheckingEnabled, ScreenBax);
                j.setVisible(true);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            Refresh();
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
        }

        Refresh();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            Mutexesiswork S = em.createNamedQuery("Mutexesiswork.findByIdMutexesisWork", Mutexesiswork.class)
                    .setParameter("idMutexesisWork", Integer.parseInt(id))
                    .getSingleResult();
            ScreenDetalGozleyir h = new ScreenDetalGozleyir(this, rootPaneCheckingEnabled, S);
            h.setVisible(rootPaneCheckingEnabled);
            if (ScreenDetalGozleyir.Status == 1) {

                Mutexesiswork f = new Mutexesiswork();
                f.setIdMutexesisWork(S.getIdMutexesisWork());
                f.setIdDaxilOlan(S.getIdDaxilOlan());
                f.setIdMutexesisler(S.getIdMutexesisler());
                f.setStatus("2");
                f.setDate(S.getDate());
                em.merge(f);
                em.getTransaction().begin();
                em.getTransaction().commit();
            }
        }
        Refresh();
    }//GEN-LAST:event_jButton12ActionPerformed

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

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        //        conn = new ConnMySql();
    }//GEN-LAST:event_jButton15ActionPerformed

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
            Refresh();
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
        }

        Refresh();
    }//GEN-LAST:event_jMenuItemCetineatActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int index = jTable2.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Sətirlərdən birini seçin!", "Xeta", JOptionPane.ERROR_MESSAGE);

        } else {
            String id = model.getValueAt(index, 0).toString();
            Refresh();
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
        }

        Refresh();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemCetineat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
