package Screens;

import ConnectionDB.ConnMySql;
import Object.DaxilOlan;
import Object.DaxilOlanNov;
import Object.MutexesisWork;
import java.text.SimpleDateFormat;

public class ScreenBax extends javax.swing.JDialog {

    MutexesisWork S;
//    private final Daxilolannov DaxilOlanNov;
    private final ConnMySql conn;
    private final DaxilOlanNov DaxilOlanNov;

    public ScreenBax(java.awt.Frame parent, boolean modal, MutexesisWork d) {
        super(parent, modal);
        initComponents();
        this.S = d;
        conn = new ConnMySql();
        DaxilOlanNov = conn.DaxilOlanNovFindByIdDaxilOlanNov(conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).idDaxilOlanNov);

        jTextFieldID.setText("" + conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).idDaxilOlan);
        jTextFieldAd.setText(conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Ad);
        jTextFieldSoyad.setText(conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Soyad);
        jTextFieldTelefon.setText(conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Telefon);
        jTextFieldDaxilOlan.setText(DaxilOlanNov.Ad);
        jTextFieldModel.setText(conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Model);
        jTextFieldMarka.setText(conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Marka);
        jTextFieldAksesuar.setText(conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Aksesuar);
        jEditorPaneProblem.setText(conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Problem);
        jEditorPaneQeyd.setText(conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Qeyd);
        jLabelDatePlan.setText("Planlaşdırılmış tarix: " + conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).DatePlan);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldAksesuar = new javax.swing.JTextField();
        jTextFieldMarka = new javax.swing.JTextField();
        jTextFieldModel = new javax.swing.JTextField();
        jTextFieldTelefon = new javax.swing.JTextField();
        jTextFieldSoyad = new javax.swing.JTextField();
        jTextFieldAd = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPaneProblem = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPaneQeyd = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jTextFieldDaxilOlan = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabelDatePlan = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Bağla");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Adı");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Soyadı");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Telefon");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Daxil olan");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Model");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Marka");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Aksesuar");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Problem");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Qeyd");

        jTextFieldAksesuar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldMarka.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldModel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldTelefon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldSoyad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldAd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jEditorPaneProblem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jEditorPaneProblem.setMinimumSize(new java.awt.Dimension(20, 20));
        jEditorPaneProblem.setPreferredSize(new java.awt.Dimension(20, 20));
        jScrollPane1.setViewportView(jEditorPaneProblem);

        jEditorPaneQeyd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jEditorPaneQeyd.setMinimumSize(new java.awt.Dimension(20, 20));
        jEditorPaneQeyd.setPreferredSize(new java.awt.Dimension(20, 20));
        jScrollPane2.setViewportView(jEditorPaneQeyd);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ID");

        jTextFieldID.setEditable(false);
        jTextFieldID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldDaxilOlan.setEditable(false);
        jTextFieldDaxilOlan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Yadda saxla");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabelDatePlan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDatePlan.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Plan tarixini dəyiş");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldModel, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAd, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSoyad, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAksesuar, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMarka, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDaxilOlan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelDatePlan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldSoyad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldDaxilOlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldMarka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAksesuar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDatePlan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            SimpleDateFormat as = new SimpleDateFormat("yyyy-MM-dd");
            String Date = as.format(jDateChooser1.getDate());
            conn.DaxilOlanInsertUpdate(new DaxilOlan(Integer.parseInt(jTextFieldID.getText()), jTextFieldAd.getText(), jTextFieldSoyad.getText(),
                     jTextFieldTelefon.getText(), DaxilOlanNov.idDaxilOlanNov, jTextFieldModel.getText(),
                     jTextFieldMarka.getText(), jTextFieldAksesuar.getText(), jEditorPaneProblem.getText(), conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Netice,
                    jEditorPaneQeyd.getText(), conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Date, conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).isActive,
                    Date, conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).DateTemir,
                    conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).DateTehvil, conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).GY));
        } catch (NumberFormatException e) {
            conn.DaxilOlanInsertUpdate(new DaxilOlan(Integer.parseInt(jTextFieldID.getText()), jTextFieldAd.getText(), jTextFieldSoyad.getText(),
                    jTextFieldTelefon.getText(), DaxilOlanNov.idDaxilOlanNov, jTextFieldModel.getText(),
                    jTextFieldMarka.getText(), jTextFieldAksesuar.getText(), jEditorPaneProblem.getText(), conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Netice,
                    jEditorPaneQeyd.getText(), conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).Date, conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).isActive,
                    conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).DatePlan, conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).DateTemir,
                    conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).DateTehvil, conn.DaxilOlanFindByIdDaxilOlan(S.IdDaxilOlan).GY));
        }
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    public javax.swing.JEditorPane jEditorPaneProblem;
    public javax.swing.JEditorPane jEditorPaneQeyd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDatePlan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextField jTextFieldAd;
    public javax.swing.JTextField jTextFieldAksesuar;
    public javax.swing.JTextField jTextFieldDaxilOlan;
    public javax.swing.JTextField jTextFieldID;
    public javax.swing.JTextField jTextFieldMarka;
    public javax.swing.JTextField jTextFieldModel;
    public javax.swing.JTextField jTextFieldSoyad;
    public javax.swing.JTextField jTextFieldTelefon;
    // End of variables declaration//GEN-END:variables
}
