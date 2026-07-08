import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class StudentGUI extends JFrame {

    // ==========================
    // Theme Colors
    // ==========================

    private static final Color PRIMARY = new Color(41,128,185);
    private static final Color SUCCESS = new Color(39,174,96);
    private static final Color WARNING = new Color(243,156,18);
    private static final Color DANGER = new Color(231,76,60);
    private static final Color BACKGROUND = new Color(245,247,250);
    private static final Color CARD = Color.WHITE;

    // ==========================
    // Text Fields
    // ==========================

    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtMarks;
    private JTextField txtSearch;

    // ==========================
    // Labels
    // ==========================

    private JLabel lblCount;
    private JLabel lblStudents;
    private JLabel lblAverage;
    private JLabel lblTime;

    private JLabel lblTopTitle;
    private JLabel lblTopName;
    private JLabel lblTopID;
    private JLabel lblTopMarks;

    // ==========================
    // Main Buttons
    // ==========================

    private JButton btnAdd;
    private JButton btnClear;

    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnSave;

    private JButton btnStats;
    private JButton btnRank;
    private JButton btnReport;

    private JButton btnSortName;
    private JButton btnSortMarks;
    private JButton btnSortID;

    private JButton btnExport;
    private JButton btnImport;

    private JButton btnCharts;
    private JButton btnPieChart;
    private JButton btnDashboard;

    // ==========================
    // Table
    // ==========================

    private JTable table;
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;

    // ==========================
    // Panels
    // ==========================

    private JPanel panel;
    private JPanel inputPanel;
    private JPanel headerPanel;
    private JPanel cardPanel;
    private JPanel centerPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel statusPanel;

    // ==========================
    // Variables
    // ==========================

    private int nextId = 1;

    // ==========================
    // Constructor
    // ==========================

    public StudentGUI() {

        // ==========================
        // Window Settings
        // ==========================

       setTitle("Student Management System");
setSize(1500, 850);
setLocationRelativeTo(null);
setExtendedState(JFrame.MAXIMIZED_BOTH);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

getContentPane().setBackground(BACKGROUND);

// ==========================
// Fonts
// ==========================

UIManager.put("Label.font",
        new Font("Segoe UI", Font.PLAIN, 15));

UIManager.put("Button.font",
        new Font("Segoe UI", Font.BOLD, 15));

UIManager.put("TextField.font",
        new Font("Segoe UI", Font.PLAIN, 15));

// ==========================
// Main Panel
// ==========================

panel = new JPanel(new BorderLayout(20,20));
panel.setBackground(BACKGROUND);
panel.setBorder(
        BorderFactory.createEmptyBorder(
                20,20,20,20));

// ==========================
// Input Panel
// ==========================

inputPanel = new JPanel(new GridLayout(4,2,15,15));
inputPanel.setBackground(CARD);

inputPanel.setBorder(
        BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220)),
                BorderFactory.createEmptyBorder(
                        20,20,20,20)));

txtName = new JTextField();
txtAge = new JTextField();
txtMarks = new JTextField();

Dimension fieldSize = new Dimension(320,42);

txtName.setPreferredSize(fieldSize);
txtAge.setPreferredSize(fieldSize);
txtMarks.setPreferredSize(fieldSize);

txtName.setFont(new Font("Segoe UI", Font.PLAIN,16));
txtAge.setFont(new Font("Segoe UI", Font.PLAIN,16));
txtMarks.setFont(new Font("Segoe UI", Font.PLAIN,16));

ImageIcon nameIcon = new ImageIcon("icons/user.png");
Image img1 = nameIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
nameIcon = new ImageIcon(img1);

JLabel lblName = new JLabel("Name", nameIcon, JLabel.LEFT);
lblName.setFont(new Font("Segoe UI", Font.BOLD, 15));
lblName.setIconTextGap(10);

ImageIcon ageIcon = new ImageIcon("icons/age.png");
Image img2 = ageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
ageIcon = new ImageIcon(img2);

JLabel lblAge = new JLabel("Age", ageIcon, JLabel.LEFT);
lblAge.setFont(new Font("Segoe UI", Font.BOLD, 15));
lblAge.setIconTextGap(10);

ImageIcon marksIcon = new ImageIcon("icons/marks.png");
Image img3 = marksIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
marksIcon = new ImageIcon(img3);

JLabel lblMarks = new JLabel("Marks", marksIcon, JLabel.LEFT);
lblMarks.setFont(new Font("Segoe UI", Font.BOLD, 15));
lblMarks.setIconTextGap(10);

// ==========================
// Buttons
// ==========================

ImageIcon addIcon = new ImageIcon("icons/AddStudent.png");

Image addImg = addIcon.getImage().getScaledInstance(
        15,
        15,
        Image.SCALE_SMOOTH);

addIcon = new ImageIcon(addImg);

btnAdd = new JButton("Add Student", addIcon);
btnAdd.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
btnAdd.setHorizontalTextPosition(SwingConstants.RIGHT);
btnAdd.setIconTextGap(8);

ImageIcon clearIcon = new ImageIcon("icons/Clear.png");

Image clearImg = clearIcon.getImage().getScaledInstance(
        20,
        20,
        Image.SCALE_SMOOTH);

clearIcon = new ImageIcon(clearImg);

btnClear = new JButton("Clear", clearIcon);
btnClear.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
btnClear.setHorizontalTextPosition(SwingConstants.RIGHT);
btnClear.setIconTextGap(8);

btnAdd.setBackground(PRIMARY);
btnAdd.setForeground(Color.WHITE);

btnClear.setBackground(DANGER);
btnClear.setForeground(Color.WHITE);

btnAdd.setFocusPainted(false);
btnClear.setFocusPainted(false);

btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));

inputPanel.add(lblName);
inputPanel.add(txtName);

inputPanel.add(lblAge);
inputPanel.add(txtAge);

inputPanel.add(lblMarks);
inputPanel.add(txtMarks);

inputPanel.add(btnAdd);
inputPanel.add(btnClear);


// ==========================
// Search Panel
// ==========================

txtSearch = new JTextField();
txtSearch.setPreferredSize(new Dimension(280,40));

ImageIcon searchIcon = new ImageIcon("icons/Search.png");

Image img = searchIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

searchIcon = new ImageIcon(img);

JLabel lblSearch = new JLabel("Search Student",searchIcon,JLabel.LEFT);

lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 16));
lblSearch.setIconTextGap(10);

JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

searchPanel.setBackground(BACKGROUND);

searchPanel.add(lblSearch);
searchPanel.add(txtSearch);


// ==========================
// Header
// ==========================

// Student Icon

ImageIcon studentIcon = new ImageIcon("icons/TotalStudents.png");
Image img5 = studentIcon.getImage().getScaledInstance(24, 30, Image.SCALE_SMOOTH);
studentIcon = new ImageIcon(img5);

lblCount = new JLabel("Total Students : 0", studentIcon, JLabel.LEFT);

lblCount.setFont(new Font("Segoe UI", Font.BOLD, 18));
lblCount.setForeground(PRIMARY);

headerPanel = new JPanel(new BorderLayout());

headerPanel.setBackground(BACKGROUND);

JPanel rightPanel = new JPanel();
rightPanel.setOpaque(false);
rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));


searchPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);


rightPanel.add(searchPanel);

headerPanel.add(lblCount, BorderLayout.WEST);
headerPanel.add(rightPanel, BorderLayout.EAST);
// ==========================
// Dashboard Cards
// ==========================


cardPanel = new JPanel(new GridLayout(1, 3, 20, 20));
cardPanel.setBackground(BACKGROUND);

// =====================================
// Students Card
// =====================================

lblStudents = new JLabel(
        "<html><center>👨‍🎓<br><b>Students</b><br>0</center></html>",
        SwingConstants.CENTER);

// =====================================
// Average Card
// =====================================

lblAverage = new JLabel(
        "<html><center>📊<br><b>Average</b><br>0.00</center></html>",
        SwingConstants.CENTER);

Font cardFont = new Font("Segoe UI", Font.BOLD, 18);

JLabel[] cards = { lblStudents, lblAverage };

Color[] colors = {
        new Color(52,152,219),
        new Color(39,174,96)
};

for(int i = 0; i < cards.length; i++) {

    cards[i].setFont(cardFont);
    cards[i].setOpaque(true);
    cards[i].setForeground(Color.WHITE);
    cards[i].setBackground(colors[i]);
    cards[i].setBorder(
            BorderFactory.createEmptyBorder(
                    25,25,25,25));

    cardPanel.add(cards[i]);
}

// =====================================
// Top Student Card
// =====================================

JPanel topCard = new JPanel();

topCard.setBackground(new Color(231,76,60));
topCard.setLayout(new BoxLayout(topCard, BoxLayout.Y_AXIS));


//========================
// Top Title
//========================

lblTopTitle = new JLabel(
        "TOP STUDENT",
        loadIcon("icons/TopStudent.png",40,40),
        SwingConstants.CENTER);

lblTopTitle.setHorizontalTextPosition(SwingConstants.CENTER);
lblTopTitle.setVerticalTextPosition(SwingConstants.BOTTOM);
lblTopTitle.setIconTextGap(10);
lblTopTitle.setFont(new Font("Segoe UI",Font.BOLD,20));
lblTopTitle.setForeground(Color.WHITE);
lblTopTitle.setAlignmentX(Component.CENTER_ALIGNMENT);


//========================
// Name
//========================

lblTopName = new JLabel("Name : -");
lblTopName.setMaximumSize(new Dimension(200,25));
lblTopName.setAlignmentX(Component.CENTER_ALIGNMENT);
lblTopName.setHorizontalAlignment(SwingConstants.CENTER);
lblTopName.setFont(new Font("Segoe UI",Font.BOLD,16));
lblTopName.setForeground(Color.WHITE);

//========================
// ID
//========================

lblTopID = new JLabel("ID : -");
lblTopID.setMaximumSize(new Dimension(200,25));
lblTopID.setAlignmentX(Component.CENTER_ALIGNMENT);
lblTopID.setHorizontalAlignment(SwingConstants.CENTER);
lblTopID.setFont(new Font("Segoe UI",Font.BOLD,16));
lblTopID.setForeground(Color.WHITE);


//========================
// Marks
//========================

lblTopMarks = new JLabel("Marks : 0");
lblTopMarks.setMaximumSize(new Dimension(200,25));
lblTopMarks.setAlignmentX(Component.CENTER_ALIGNMENT);
lblTopMarks.setHorizontalAlignment(SwingConstants.CENTER);
lblTopMarks.setFont(new Font("Segoe UI",Font.BOLD,16));
lblTopMarks.setForeground(Color.WHITE);

//========================
// Add Components
//========================

topCard.add(Box.createVerticalGlue());

topCard.setAlignmentX(Component.CENTER_ALIGNMENT);

topCard.add(lblTopTitle);

topCard.add(Box.createVerticalStrut(12));

topCard.add(lblTopName);

topCard.add(Box.createVerticalStrut(6));

topCard.add(lblTopID);

topCard.add(Box.createVerticalStrut(6));

topCard.add(lblTopMarks);

topCard.add(Box.createVerticalGlue());

cardPanel.add(topCard);


// ==========================
// Table
// ==========================

String[] columns = {
        "ID",
        "Name",
        "Age",
        "Marks"
};


model = new DefaultTableModel(columns, 0) {

    @Override
    public Class<?> getColumnClass(int column) {

        switch (column) {

            case 0:
                return Integer.class;

            case 2:
                return Integer.class;

            case 3:
                return Double.class;

            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};

table = new JTable(model);

table.setRowHeight(35);
table.setFont(new Font("Segoe UI", Font.PLAIN, 15));

table.getTableHeader().setFont(
        new Font("Segoe UI", Font.BOLD, 15));

table.getTableHeader().setBackground(PRIMARY);
table.getTableHeader().setForeground(Color.WHITE);

table.setSelectionBackground(
        new Color(174,214,241));

table.setGridColor(
        new Color(220,220,220));

// =====================================
// Header Center
// =====================================

DefaultTableCellRenderer headerRenderer =
        (DefaultTableCellRenderer)
        table.getTableHeader().getDefaultRenderer();

headerRenderer.setHorizontalAlignment(JLabel.CENTER);

// =====================================
// Center Renderer
// =====================================

DefaultTableCellRenderer centerRenderer =
        new DefaultTableCellRenderer();

centerRenderer.setHorizontalAlignment(JLabel.CENTER);

// =====================================
// Left Renderer
// =====================================

DefaultTableCellRenderer leftRenderer =
        new DefaultTableCellRenderer();

leftRenderer.setHorizontalAlignment(JLabel.LEFT);

// =====================================
// Apply Renderers
// =====================================

// ID
table.getColumnModel()
     .getColumn(0)
     .setCellRenderer(centerRenderer);

// Name
table.getColumnModel()
     .getColumn(1)
     .setCellRenderer(centerRenderer);

// Age
table.getColumnModel()
     .getColumn(2)
     .setCellRenderer(centerRenderer);

// Marks
table.getColumnModel()
     .getColumn(3)
     .setCellRenderer(centerRenderer);

// =====================================
// Table Sorter
// =====================================

sorter = new TableRowSorter<>(model);

table.setRowSorter(sorter);

// =====================================
// ScrollPane
// =====================================

JScrollPane scrollPane =
        new JScrollPane(table);

scrollPane.setPreferredSize(
        new Dimension(1200,280));



// ==========================
// Top Panel
// ==========================

topPanel = new JPanel(new BorderLayout(20,20));
topPanel.setBackground(BACKGROUND);

topPanel.add(inputPanel, BorderLayout.WEST);
topPanel.add(cardPanel, BorderLayout.CENTER);

// ==========================
// Center Panel
// ==========================

centerPanel = new JPanel(new BorderLayout());
centerPanel.setBackground(BACKGROUND);

centerPanel.add(scrollPane, BorderLayout.CENTER);

// ==========================
// Add to Main Panel
// ==========================

// ==========================
// Main Content
// ==========================

JPanel contentPanel = new JPanel(new BorderLayout(15,15));
contentPanel.setBackground(BACKGROUND);

contentPanel.add(topPanel, BorderLayout.NORTH);
contentPanel.add(centerPanel, BorderLayout.CENTER);

panel.add(headerPanel, BorderLayout.NORTH);
panel.add(contentPanel, BorderLayout.CENTER);

// ==========================
// Load Data
// ==========================

//loadStudentsFromDatabase();
//updateDashboard();


// ==========================================
// Bottom Toolbar
// ==========================================

bottomPanel = new JPanel(new GridLayout(2, 7, 10, 10));

bottomPanel.setBackground(BACKGROUND);

btnDelete = new JButton("Delete");
btnDelete.setBackground(new Color(231,76,60));   // Background Color
btnDelete.setForeground(Color.WHITE);  

btnUpdate = new JButton("Update");
btnUpdate.setIcon(loadIcon("icons/Update.png", 40, 40));

btnSave = new JButton("Save");
btnSave.setIcon(loadIcon("icons/Save.png", 35, 35));


btnStats = new JButton("Statistics");
btnStats.setIcon(loadIcon("icons/Statistic.png", 30, 30));

btnRank = new JButton("Rank");
btnRank.setIcon(loadIcon("icons/Rank.png", 30, 30));

btnReport = new JButton("Report");
btnReport.setIcon(loadIcon("icons/Report.png", 40, 40));


btnSortName = new JButton("Sort Name");
btnSortName.setIcon(loadIcon("icons/ShortName.png", 40, 40));



btnSortMarks = new JButton("Sort Marks");
btnSortMarks.setIcon(loadIcon("icons/SortAge.png", 40, 40));



btnSortID = new JButton("Sort ID");
btnSortID.setIcon(loadIcon("icons/SortID.png", 40, 40));


btnExport = new JButton("Export");
btnImport = new JButton("Import");


btnDashboard = new JButton("Dashboard");

// Button Style

styleButton(btnUpdate, new Color(52, 152, 219));
styleButton(btnSave, new Color(39, 174, 96));
styleButton(btnDelete, new Color(231, 76, 60));

styleButton(btnStats, new Color(52, 152, 219));
styleButton(btnRank, new Color(39, 174, 96));
styleButton(btnReport, new Color(231, 76, 60));

styleButton(btnSortName, new Color(52, 152, 219)); 
styleButton(btnSortMarks, new Color(39, 174, 96));
styleButton(btnSortID, new Color(231, 76, 60));

styleButton(btnExport, new Color(52, 152, 219));
styleButton(btnImport, new Color(39, 174, 96));
styleButton(btnDashboard, new Color(231, 76, 60));

// Add Buttons


bottomPanel.add(btnUpdate);
bottomPanel.add(btnSave);
bottomPanel.add(btnDelete);

bottomPanel.add(btnStats);
bottomPanel.add(btnRank);
bottomPanel.add(btnReport);

bottomPanel.add(btnSortName);
bottomPanel.add(btnSortMarks);
bottomPanel.add(btnSortID);

bottomPanel.add(btnExport);
bottomPanel.add(btnImport);

bottomPanel.add(btnDashboard);


// ==========================================
// Status Bar
// ==========================================


statusPanel = new JPanel(new BorderLayout());

statusPanel.setBackground(new Color(235,240,245));
statusPanel.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));


// ==========================================
// User PNG Icon
// ==========================================

ImageIcon userIcon = new ImageIcon("icons/User.png");

Image userImg = userIcon.getImage().getScaledInstance(
        18,
        18,
        Image.SCALE_SMOOTH);

userIcon = new ImageIcon(userImg);


// ==========================================
// Logged User Label
// ==========================================

JLabel lblUser = new JLabel(
        "Logged User : Admin",
        userIcon,
        JLabel.LEFT);

lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
lblUser.setHorizontalTextPosition(SwingConstants.RIGHT);
lblUser.setIconTextGap(8);


// ==========================================
// Status Panel
// ==========================================

statusPanel.add(lblUser, BorderLayout.WEST);


// ==========================================
// South Panel
// ==========================================

JPanel southPanel = new JPanel(new BorderLayout());

southPanel.setBackground(BACKGROUND);

southPanel.add(bottomPanel, BorderLayout.CENTER);
southPanel.add(statusPanel, BorderLayout.SOUTH);

panel.add(southPanel, BorderLayout.SOUTH);



ImageIcon dateIcon = new ImageIcon("icons/Date.png");

Image dateImg = dateIcon.getImage().getScaledInstance(
        18,
        18,
        Image.SCALE_SMOOTH);

dateIcon = new ImageIcon(dateImg);

lblTime = new JLabel(
        "",
        dateIcon,
        JLabel.RIGHT);

lblTime.setHorizontalTextPosition(SwingConstants.RIGHT);
lblTime.setIconTextGap(8);
lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 14));


statusPanel.add(lblUser, BorderLayout.CENTER);
statusPanel.add(lblTime, BorderLayout.EAST);

SimpleDateFormat sdf =
        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

lblTime.setText(sdf.format(new Date()));

Timer timer = new Timer(1000, e -> {

    lblTime.setText(sdf.format(new Date()));

});

timer.start();


registerButtonEvents();

txtSearch.getDocument().addDocumentListener(new DocumentListener() {

    @Override
    public void insertUpdate(DocumentEvent e) {
        searchStudent();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        searchStudent();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        searchStudent();
    }

});

loadStudentsFromDatabase();
updateDashboard();

setContentPane(panel);
setVisible(true);

}


// ==========================================
// Button Style Method
// ==========================================

private void styleButton(JButton button, Color color) {

    if(button == null){
        return;
    }

    button.setBackground(color);
    button.setForeground(Color.WHITE);

    button.setFocusPainted(false);

    button.setFont(new Font("Segoe UI", Font.BOLD, 14));

    button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    button.setPreferredSize(new Dimension(130,40));

}

// ==========================================
// Button Events
// ==========================================

private void registerButtonEvents() {

    // ==========================
    // Add
    // ==========================

    btnAdd.addActionListener(e -> addStudent());

    // ==========================
    // Clear
    // ==========================

    btnClear.addActionListener(e -> {

        txtName.setText("");
        txtAge.setText("");
        txtMarks.setText("");

        txtName.requestFocus();

    });

    // ==========================
    // Delete
    // ==========================

    btnDelete.addActionListener(e -> {

        int row = table.getSelectedRow();

        if(row == -1){

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student.");

            return;
        }

        int id = Integer.parseInt(
                model.getValueAt(row,0).toString());

        deleteStudent(id);

        model.removeRow(row);

        updateDashboard();

    });

    // ==========================
    // Update
    // ==========================

    btnUpdate.addActionListener(e -> {

        if(table.getSelectedRow()==-1){

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student.");

            return;
        }

        updateSelectedStudent();

    });

    // ==========================
    // Save
    // ==========================

    btnSave.addActionListener(e -> {

        saveStudentsToFile();

    });




// ==========================
// Statistics
// ==========================

btnStats.addActionListener(e -> {

    try{

        Connection con = DBConnection.getConnection();

        String sql =
                "SELECT COUNT(*) total," +
                "AVG(marks) average," +
                "MAX(marks) highest," +
                "MIN(marks) lowest " +
                "FROM students";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        if(rs.next()){

    int total = rs.getInt("total");

    double average = rs.getDouble("average");

    double highest = rs.getDouble("highest");

    double lowest = rs.getDouble("lowest");

    new StatisticsFrame(
            total,
            average,
            highest,
            lowest
    ).setVisible(true);

}

        

        rs.close();
        ps.close();
        con.close();

    }

    catch(Exception ex){

        ex.printStackTrace();

    }

});


// ==========================
// Rank
// ==========================

btnRank.addActionListener(e -> {

    new RankFrame().setVisible(true);

});

// ==========================
// Report
// ==========================

btnReport.addActionListener(e -> {

    int row = table.getSelectedRow();

    if(row==-1){

        JOptionPane.showMessageDialog(
                this,
                "Select a student.");

        return;
    }

    int id =
            Integer.parseInt(model.getValueAt(row,0).toString());

    String name =
            model.getValueAt(row,1).toString();

    int age =
            Integer.parseInt(model.getValueAt(row,2).toString());

    double marks =
            Double.parseDouble(model.getValueAt(row,3).toString());

    new ReportCardFrame(
            id,
            name,
            age,
            marks
    ).setVisible(true);

});


// ==========================
// Dashboard
// ==========================

btnDashboard.addActionListener(e -> {

    updateDashboard();

    new DashboardFrame().setVisible(true);

});

// ==========================
// Sort Name
// ==========================

btnSortName.addActionListener(e -> {

    sorter.setSortKeys(
            java.util.List.of(
                    new RowSorter.SortKey(
                            1,
                            SortOrder.ASCENDING)));

    sorter.sort();

});

// ==========================
// Sort Marks
// ==========================

btnSortMarks.addActionListener(e -> {

    sorter.setSortKeys(
            java.util.List.of(
                    new RowSorter.SortKey(
                            3,
                            SortOrder.DESCENDING)));

    sorter.sort();

});

// ==========================
// Sort ID
// ==========================

btnSortID.addActionListener(e -> {

    sorter.setSortKeys(
            java.util.Arrays.asList(
                    new RowSorter.SortKey(
                            0,
                            SortOrder.ASCENDING)));

    sorter.sort();

});

// ==========================
// Export
// ==========================

btnExport.addActionListener(e -> {

    exportCSV();

});

// ==========================
// Import
// ==========================

btnImport.addActionListener(e -> {

    JFileChooser chooser = new JFileChooser();

    if(chooser.showOpenDialog(this)
            == JFileChooser.APPROVE_OPTION){

        importCSV(chooser.getSelectedFile());

    }

});
}


    // ==========================
    // Load PNG Icon Method
    // ==========================

    



// ==========================================
// Add Student
// ==========================================

private void addStudent() {

    try {

        String name = txtName.getText().trim();

        if (name.isEmpty() ||
            txtAge.getText().trim().isEmpty() ||
            txtMarks.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.");

            return;
        }

        int age = Integer.parseInt(txtAge.getText().trim());
        double marks = Double.parseDouble(txtMarks.getText().trim());

        int id = nextId++;

        insertStudent(id, name, age, marks);

        loadStudentsFromDatabase();

        updateDashboard();

        txtName.setText("");
        txtAge.setText("");
        txtMarks.setText("");

        txtName.requestFocus();

    }

    catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid Input!");

    }

}

// ==========================================
// Update Student
// ==========================================

private void updateSelectedStudent() {

    int row = table.getSelectedRow();

    if (row == -1)
        return;

    try {

        int id = Integer.parseInt(
                model.getValueAt(row, 0).toString());

        String name = JOptionPane.showInputDialog(
                this,
                "Student Name",
                model.getValueAt(row,1));

        if(name == null)
            return;

        int age = Integer.parseInt(

                JOptionPane.showInputDialog(
                        this,
                        "Age",
                        model.getValueAt(row,2)));

        double marks = Double.parseDouble(

                JOptionPane.showInputDialog(
                        this,
                        "Marks",
                        model.getValueAt(row,3)));

        model.setValueAt(name,row,1);
        model.setValueAt(age,row,2);
        model.setValueAt(marks,row,3);

        updateStudent(id,name,age,marks);

        updateDashboard();

    }

    catch(Exception ex){

        JOptionPane.showMessageDialog(
                this,
                "Update Failed!");

    }

}

// ==========================================
// Save Students
// ==========================================

private void saveStudentsToFile(){

    try(PrintWriter writer =
            new PrintWriter("Students.txt")){

        for(int i=0;i<model.getRowCount();i++){

            writer.println(

                    model.getValueAt(i,0)+","+

                    model.getValueAt(i,1)+","+

                    model.getValueAt(i,2)+","+

                    model.getValueAt(i,3)

            );

        }

        JOptionPane.showMessageDialog(
                this,
                "Saved Successfully.");

    }

    catch(Exception ex){

        JOptionPane.showMessageDialog(
                this,
                "Save Failed.");

    }

}

// ==========================================
// Insert Student
// ==========================================

private void insertStudent(
        int id,
        String name,
        int age,
        double marks){

    String sql =
            "INSERT INTO students(id,name,age,marks) VALUES(?,?,?,?)";

    try(Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement(sql)){

        ps.setInt(1,id);
        ps.setString(2,name);
        ps.setInt(3,age);
        ps.setDouble(4,marks);

        ps.executeUpdate();

    }

    catch(Exception ex){

        JOptionPane.showMessageDialog(
                this,
                "Database Insert Failed!");

    }

}

// ==========================================
// Update Database
// ==========================================

private void updateStudent(
        int id,
        String name,
        int age,
        double marks){

    String sql =
            "UPDATE students SET name=?, age=?, marks=? WHERE id=?";

    try(Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement(sql)){

        ps.setString(1,name);
        ps.setInt(2,age);
        ps.setDouble(3,marks);
        ps.setInt(4,id);

        ps.executeUpdate();

    }

    catch(Exception ex){

        JOptionPane.showMessageDialog(
                this,
                "Database Update Failed!");

    }

}

// ==========================================
// Delete Student
// ==========================================

private void deleteStudent(int id){

    String sql =
            "DELETE FROM students WHERE id=?";

    try(Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement(sql)){

        ps.setInt(1,id);

        ps.executeUpdate();

    }

    catch(Exception ex){

        JOptionPane.showMessageDialog(
                this,
                "Database Delete Failed!");

    }

}

// ==========================================
// Search Student
// ==========================================

private void searchStudent() {

    String text = txtSearch.getText().trim();

    if(text.isEmpty()){

        sorter.setRowFilter(null);

    }else{

        sorter.setRowFilter(
                RowFilter.regexFilter(
                        "(?i)"+text,
                        1));

    }

}

// ==========================================
// Export CSV
// ==========================================

private void exportCSV(){

    JFileChooser chooser = new JFileChooser();

    chooser.setSelectedFile(
            new File("Students.csv"));

    if(chooser.showSaveDialog(this)
            != JFileChooser.APPROVE_OPTION)
        return;

    try(PrintWriter writer =
            new PrintWriter(chooser.getSelectedFile())){

        writer.println("ID,Name,Age,Marks");

        for(int i=0;i<model.getRowCount();i++){

            writer.println(

                    model.getValueAt(i,0)+","+

                    model.getValueAt(i,1)+","+

                    model.getValueAt(i,2)+","+

                    model.getValueAt(i,3)

            );

        }

        JOptionPane.showMessageDialog(
                this,
                "CSV Exported Successfully!");

    }

    catch(Exception ex){

        JOptionPane.showMessageDialog(
                this,
                "Export Failed!");

    }

}

// ==========================================
// Import CSV
// ==========================================

private void importCSV(File file){

    try(Scanner scanner =
            new Scanner(file)){

        if(scanner.hasNextLine())
            scanner.nextLine();

        while(scanner.hasNextLine()){

            String[] data =
                    scanner.nextLine().split(",");

            insertStudent(

                    Integer.parseInt(data[0]),

                    data[1],

                    Integer.parseInt(data[2]),

                    Double.parseDouble(data[3])

            );

        }

        loadStudentsFromDatabase();

        updateDashboard();

    }

    catch(Exception ex){

        JOptionPane.showMessageDialog(
                this,
                "Import Failed!");

    }

}

// ==========================================
// Load Students
// ==========================================

private void loadStudentsFromDatabase(){

    model.setRowCount(0);

    String sql =
            "SELECT * FROM students ORDER BY id";

    try(

        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery()

    ){

        int maxId = 0;

        while(rs.next()){

            int id = rs.getInt("id");

            model.addRow(new Object[]{

                    id,

                    rs.getString("name"),

                    rs.getInt("age"),

                    rs.getDouble("marks")

            });

            if(id > maxId)
                maxId = id;

        }

        nextId = maxId + 1;

        updateDashboard();

    }

    catch(Exception ex){

        JOptionPane.showMessageDialog(
                this,
                "Error Loading Students!");

    }

}

// ==========================================
// Update Dashboard
// ==========================================

private void updateDashboard() {

    int totalStudents = model.getRowCount();

    lblCount.setText("Total Students : " + totalStudents);

    if (totalStudents == 0) {

        lblStudents.setText(
                "<html><center>👨‍🎓<br><b>Students</b><br>0</center></html>");

        lblAverage.setText(
                "<html><center>📊<br><b>Average</b><br>0.00</center></html>");

        lblTopName.setText("-");
        lblTopID.setText("ID : -");
        lblTopMarks.setText("Marks : 0");

        return;
    }

    double totalMarks = 0;
    double highestMarks = -1;

    String topStudent = "-";
    int topStudentId = 0;

    for (int i = 0; i < model.getRowCount(); i++) {

        double marks = Double.parseDouble(
                model.getValueAt(i, 3).toString());

        totalMarks += marks;

        if (marks > highestMarks) {

            highestMarks = marks;

            topStudent =
                    model.getValueAt(i, 1).toString();

            topStudentId =
                    Integer.parseInt(
                            model.getValueAt(i, 0).toString());

        }

    }

    double average = totalMarks / totalStudents;

    ImageIcon studentIcon = new ImageIcon("icons/TotalStudents.png");
    Image img5 = studentIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    studentIcon = new ImageIcon(img5);

    lblStudents.setIcon(studentIcon);
    lblStudents.setHorizontalTextPosition(SwingConstants.CENTER);
    lblStudents.setVerticalTextPosition(SwingConstants.BOTTOM);
    lblStudents.setHorizontalAlignment(SwingConstants.CENTER);

    lblStudents.setText(
        "<html><center><b>Students</b><br>"
        + totalStudents
        + "</center></html>");


    ImageIcon averageIcon = new ImageIcon("icons/Average.png");
    Image img6 = averageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
    averageIcon = new ImageIcon(img6);

    lblAverage.setIcon(averageIcon);
    lblAverage.setHorizontalTextPosition(SwingConstants.CENTER);
    lblAverage.setVerticalTextPosition(SwingConstants.BOTTOM);
    lblAverage.setHorizontalAlignment(SwingConstants.CENTER);

    lblAverage.setText(
        "<html><center><b>Average</b><br>"
        + String.format("%.2f", average)
        + "</center></html>");


    // ===========================
    // Top Student Card
    // ===========================

    lblTopName.setText(
    "<html><div style='width:160px; text-align:center;'>"
    + "<b>Name :</b> "
    + topStudent
    + "</div></html>");

    lblTopID.setText(
    "<html><div style='text-align:center;'>"
    + "<b>ID :</b> "
    + topStudentId
    + "</div></html>");

    lblTopMarks.setText(
    "<html><div style='text-align:center;'>"
    + "<b>Marks :</b> "
    + String.format("%.2f", highestMarks)
    + "</div></html>");
}

private ImageIcon loadIcon(String path, int width, int height) {

    ImageIcon icon = new ImageIcon(path);

    Image image = icon.getImage().getScaledInstance(
            width,
            height,
            Image.SCALE_SMOOTH);

    return new ImageIcon(image);
}




}