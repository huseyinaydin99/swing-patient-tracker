package tr.com.huseyinaydin.view;

import tr.com.huseyinaydin.model.Patient;
import tr.com.huseyinaydin.service.IPatientService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.UUID;
import tr.com.huseyinaydin.repository.impl.FilePatientRepository;
import tr.com.huseyinaydin.service.impl.PatientService;

public class SwingUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(SwingUI.class.getName());

    private final IPatientService patientService;
    //private final JFrame frame;
    private final PatientTableModel tableModel;

    public SwingUI(IPatientService patientService) {
        this.patientService = patientService;
        //this.frame = new JFrame("Hasta Kayıt Sistemi — GUI");
        this.tableModel = new PatientTableModel(patientService);
        //this.table = new JTable(tableModel);
        
        initComponents();
        configureLookAndFeel();
        buildUI();
        
        this.table.setModel(tableModel);
    }

    private void buildUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        // Tam ekran yerine uygun boyut ve ortala
        this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(900, 600));

        // Başlık
        JLabel title = new JLabel("Hasta Yönetim Formu", JLabel.CENTER);
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(25, 118, 210)); // modern mavi
        this.add(title, BorderLayout.NORTH);

        // Ana içerik: sol tablo, sağ form
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setResizeWeight(0.6);
        split.setContinuousLayout(true);
        split.setDividerSize(6);
        split.setBorder(null);

        // Sol Panel: Tablo
        JPanel left = new JPanel(new BorderLayout());
        left.setBackground(Color.WHITE);
        left.setBorder(new EmptyBorder(16, 16, 16, 8));

        // Tablo stili
        table.setFillsViewportHeight(true);
        table.setRowHeight(32);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this::onTableSelection);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(245, 245, 245));
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
        left.add(scroll, BorderLayout.CENTER);

        // Tablo altındaki butonlar
        JPanel leftBottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftBottom.setBackground(Color.WHITE);

        JButton refreshBtn = styledButton("Yenile");
        refreshBtn.addActionListener(e -> tableModel.reload());
        leftBottom.add(refreshBtn);

        JButton clearSelectionBtn = styledButton("Seçimi Temizle");
        
        clearSelectionBtn.setPreferredSize(new Dimension(145, 40)); // Orantılı, sabit genişlik ve yükseklik
        clearSelectionBtn.setMaximumSize(new Dimension(150, 45));
        clearSelectionBtn.setMinimumSize(new Dimension(120, 35));
                
        clearSelectionBtn.addActionListener(e -> {
            table.clearSelection();
            clearForm();
        });
        leftBottom.add(clearSelectionBtn);

        left.add(leftBottom, BorderLayout.SOUTH);

        // Sağ Panel: Form
        JPanel right = new JPanel();
        right.setBackground(new Color(250, 250, 250));
        right.setBorder(new EmptyBorder(20, 12, 20, 20));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        // Form başlığı
        JLabel formTitle = new JLabel("Hasta Bilgileri: ");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        formTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        formTitle.setForeground(new Color(33, 37, 41));
        right.add(formTitle);
        right.add(Box.createRigidArea(new Dimension(0, 16)));

        // Form alanları: kart stili
        JPanel formCard = new JPanel(new GridBagLayout());
        formCard.setBackground(Color.WHITE);
        formCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
        formCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formCard.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        idField.setEditable(false);
        formCard.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formCard.add(new JLabel("Ad:"), gbc);
        gbc.gridx = 1;
        formCard.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formCard.add(new JLabel("Soyad:"), gbc);
        gbc.gridx = 1;
        formCard.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formCard.add(new JLabel("Yaş:"), gbc);
        gbc.gridx = 1;
        formCard.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formCard.add(new JLabel("Telefon:"), gbc);
        gbc.gridx = 1;
        formCard.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formCard.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        formCard.add(emailField, gbc);

        right.add(formCard);
        right.add(Box.createRigidArea(new Dimension(0, 24)));

        // Butonlar
        JPanel btnPanel = new JPanel(new GridLayout(1, 4, 14, 0));
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPanel.setBackground(new Color(250, 250, 250));

        JButton addBtn = styledButton("Ekle");
        JButton updateBtn = styledButton("Güncelle");
        JButton deleteBtn = styledButton("Sil");
        JButton clearBtn = styledButton("Temizle");

        addBtn.addActionListener(this::onAdd);
        updateBtn.addActionListener(this::onUpdate);
        deleteBtn.addActionListener(this::onDelete);
        clearBtn.addActionListener(e -> clearForm());

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(clearBtn);
        right.add(btnPanel);

        // Footer
        right.add(Box.createVerticalGlue());
        JLabel footer = new JLabel("© 2025 Hasta Kayıt Sistemi • Tüm Hakları Saklıdır | Hüseyin AYDIN Tarafından Oluşturulmuştur. Saygılarımla...", JLabel.LEFT);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footer.setForeground(new Color(130, 130, 130));
        footer.setAlignmentX(Component.LEFT_ALIGNMENT);
        right.add(footer);

        // Split pane ayarları
        split.setLeftComponent(left);
        split.setRightComponent(right);
        this.add(split, BorderLayout.CENTER);

        // Başlangıç yükleme
        tableModel.reload();
    }

    private JButton styledButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBackground(new Color(173, 216, 230));  // Açık mavi, siyah yazı için uygun
        b.setForeground(Color.BLACK);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(120, 40)); // Orantılı, sabit genişlik ve yükseklik
        b.setMaximumSize(new Dimension(140, 45));
        b.setMinimumSize(new Dimension(100, 35));
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237)), // Daha açık mavi çizgi
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));
        return b;
    }

    private void configureLookAndFeel() {
        // Mümkünse sistem look and feel, değilse cross-platform
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        // Global font + biraz görsel incelik
        Font base = new Font("Segoe UI", Font.PLAIN, 14);
        UIManager.put("Label.font", base);
        UIManager.put("Button.font", base.deriveFont(Font.BOLD));
        UIManager.put("TextField.font", base);
        UIManager.put("Table.font", base);
        UIManager.put("TableHeader.font", base.deriveFont(15f).deriveFont(Font.BOLD));
    }

    private void onTableSelection(ListSelectionEvent ev) {
        if (!ev.getValueIsAdjusting()) {
            int row = table.getSelectedRow();
            if (row >= 0) {
                Patient p = tableModel.getAt(row);
                fillForm(p);
            }
        }
    }

    private void fillForm(Patient p) {
        idField.setText(p.getId());
        firstNameField.setText(p.getFirstName());
        lastNameField.setText(p.getLastName());
        ageField.setText(String.valueOf(p.getAge()));
        phoneField.setText(p.getPhone());
        emailField.setText(p.getEmail());
    }

    private void clearForm() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        phoneField.setText("");
        emailField.setText("");
        table.clearSelection();
    }

    private void onAdd(ActionEvent e) {
        try {
            String id = UUID.randomUUID().toString();
            String fn = firstNameField.getText().trim();
            String ln = lastNameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (fn.isEmpty() || ln.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ad ve Soyad boş olamaz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Patient p = new Patient(id, fn, ln, age, phone, email);
            patientService.registerPatient(p);
            tableModel.reload();
            clearForm();
            JOptionPane.showMessageDialog(this, "Hasta kaydedildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Yaş için geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Kaydetme sırasında hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onUpdate(ActionEvent e) {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Önce bir hasta seçin veya ekleyin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            String fn = firstNameField.getText().trim();
            String ln = lastNameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            Patient p = new Patient(id, fn, ln, age, phone, email);
            patientService.updatePatient(p);
            tableModel.reload();
            JOptionPane.showMessageDialog(this, "Hasta güncellendi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Yaş için geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Güncelleme sırasında hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onDelete(ActionEvent e) {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silinecek hasta seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bu hastayı silmek istediğinize emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            patientService.deletePatient(id);
            tableModel.reload();
            clearForm();
            JOptionPane.showMessageDialog(this, "Hasta silindi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showUI() {
        SwingUtilities.invokeLater(() -> {
            this.setVisible(true);
        });
    }

    // ---------------- Table Model ----------------
    private static class PatientTableModel extends AbstractTableModel {

        private final String[] columns = {"ID", "Ad", "Soyad", "Yaş", "Telefon", "Email"};
        private List<Patient> data;
        private final IPatientService service;

        public PatientTableModel(IPatientService service) {
            this.service = service;
            this.data = List.of();
        }

        public void reload() {
            this.data = service.listPatients();
            fireTableDataChanged();
        }

        public Patient getAt(int row) {
            return data.get(row);
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Patient p = data.get(rowIndex);
            return switch (columnIndex) {
                case 0 ->
                    p.getId();
                case 1 ->
                    p.getFirstName();
                case 2 ->
                    p.getLastName();
                case 3 ->
                    p.getAge();
                case 4 ->
                    p.getPhone();
                case 5 ->
                    p.getEmail();
                default ->
                    "";
            };
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idField = new javax.swing.JTextField();
        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        ageField = new javax.swing.JTextField();
        phoneField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hasta Yönetimi Formu");
        setLocation(new java.awt.Point(220, 450));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(firstNameField)
                    .addComponent(idField)
                    .addComponent(lastNameField)
                    .addComponent(ageField)
                    .addComponent(phoneField)
                    .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new SwingUI(new PatientService(new FilePatientRepository("FileDatabase.data"))).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ageField;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JTextField idField;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}