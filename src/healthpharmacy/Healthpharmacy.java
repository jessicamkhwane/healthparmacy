/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package healthpharmacy;
import javax.swing.*;
import java.awt.*;

public class CashierDashboard extends JFrame {
    public CashierDashboard(int userId, String name) {
        setTitle("HealthFirst - Cashier Point of Sale");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600); setLocationRelativeTo(null);
        add(new JLabel("  Cashier: " + name, SwingConstants.LEFT), BorderLayout.NORTH);
        add(new POSPanel(userId), BorderLayout.CENTER);
        JButton logout = new JButton("Log out");
        logout.addActionListener(e -> { dispose(); new LoginFrame().setVisible(true); });
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT)); p.add(logout); add(p, BorderLayout.SOUTH);
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
