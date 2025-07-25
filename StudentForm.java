import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentForm extends JFrame {

    JTextField txtId, txtName, txtCourse;
    JButton btnAdd, btnUpdate, btnDelete, btnView;
    JTextArea textArea;

    Connection con;

    public StudentForm() {
        setTitle("Student Record Manager");
        setSize(500, 400);
        setLayout(new GridLayout(6, 2));

        JLabel lblId = new JLabel("ID:");
        JLabel lblName = new JLabel("Name:");
        JLabel lblCourse = new JLabel("Course:");

        txtId = new JTextField();
        txtName = new JTextField();
        txtCourse = new JTextField();

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnView = new JButton("View");

        textArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(lblId); add(txtId);
        add(lblName); add(txtName);
        add(lblCourse); add(txtCourse);

        add(btnAdd); add(btnUpdate);
        add(btnDelete); add(btnView);
        add(scrollPane);

        connectDB();
        setupListeners();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void connectDB() {
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(
            "jdbc:sqlserver://DESKTOP-5HMSKEO\\SQLEXPRESS;databaseName=StudentDB;encrypt=true;trustServerCertificate=true",
            "sa", "monu"
        );
        JOptionPane.showMessageDialog(this, "Connected to DB"); // Optional
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "DB Connection Failed: " + e);
        e.printStackTrace();
    }
}

    private void setupListeners() {
        btnAdd.addActionListener(e -> addRecord());
        btnUpdate.addActionListener(e -> updateRecord());
        btnDelete.addActionListener(e -> deleteRecord());
        btnView.addActionListener(e -> viewRecords());
    }

    private void addRecord() {
        try {
            String sql = "INSERT INTO Students VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtId.getText()));
            pst.setString(2, txtName.getText());
            pst.setString(3, txtCourse.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record Added");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateRecord() {
        try {
            String sql = "UPDATE Students SET name = ?, course = ? WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, txtName.getText());
            pst.setString(2, txtCourse.getText());
            pst.setInt(3, Integer.parseInt(txtId.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record Updated");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteRecord() {
        try {
            String sql = "DELETE FROM Students WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtId.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record Deleted");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void viewRecords() {
        try {
            textArea.setText("");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                textArea.append(rs.getInt("id") + " - " + rs.getString("name") + " - " + rs.getString("course") + "\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new StudentForm();
    }
}
