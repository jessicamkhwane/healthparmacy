/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package healthpharmacy;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private final JTextField username = new JTextField(18);
    private final JPasswordField password = new JPasswordField(18);

    public LoginFrame() {
        setTitle("HealthFirst Pharmacy - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 250);
        setLocationRelativeTo(null);
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 6, 8, 6); c.anchor = GridBagConstraints.WEST;
        c.gridx=0; c.gridy=0; form.add(new JLabel("Username:"), c);
        c.gridx=1; form.add(username, c);
        c.gridx=1; form.add(username, c);
        c.gridx=0; c.gridy=1; form.add(new JLabel("Password:"), c);
        c.gridx=1; form.add(password, c);
        JButton login = new JButton("Login");
        c.gridx=1; c.gridy=2; c.anchor=GridBagConstraints.EAST; form.add(login, c);
        login.addActionListener(e -> authenticate());
        getRootPane().setDefaultButton(login);
        add(new JLabel("HealthFirst Pharmacy Inventory Management", SwingConstants.CENTER), BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
    }

    private void authenticate() {
        String user = username.getText().trim();
        String pass = new String(password.getPassword());
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter both username and password.");
            return;
        }
        String sql = "SELECT user_id, username, full_name, role FROM users WHERE username=? AND password_hash=? AND active=1";
        try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, DB.hashPassword(pass));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    String name = rs.getString("full_name");
                    String role = rs.getString("role");
                    dispose();
                    if ("Admin".equals(role)) new AdminDashboard(id, name).setVisible(true);
                    else new CashierDashboard(id, name).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Login failed. Check your credentials.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage()
                    + "\nCheck that MySQL is running and database.sql has been imported.");
        }
    }
}

/**
 *
 * @author ASUS
 */
public class Healthpharmacy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
