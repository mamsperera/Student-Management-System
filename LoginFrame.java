import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {

        setTitle("Student Management System");
        setSize(900,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // ==========================
        // Main Panel
        // ==========================

        JPanel main = new JPanel(new GridLayout(1,2));
        main.setBackground(new Color(245,247,250));

        add(main);

       // ==========================
       // Left Panel
       // ==========================

       JPanel leftPanel = new JPanel();
       leftPanel.setBackground(new Color(33,150,243));
       leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

       leftPanel.add(Box.createVerticalGlue());

       // Logo
       ImageIcon icon = new ImageIcon("icons/StudentManagementSystem.png");

       Image img = icon.getImage().getScaledInstance(
        80, 80, Image.SCALE_SMOOTH);

       // IMPORTANT
       icon = new ImageIcon(img);

       JLabel lblIcon = new JLabel(icon);
       lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

       // Title
       JLabel title = new JLabel("Student Management");
       title.setFont(new Font("Segoe UI", Font.BOLD,30));
       title.setForeground(Color.WHITE);
       title.setAlignmentX(Component.CENTER_ALIGNMENT);

       // Subtitle
       JLabel sub = new JLabel("Modern Student Management System");
       sub.setFont(new Font("Segoe UI", Font.PLAIN,16));
       sub.setForeground(Color.WHITE);
       sub.setAlignmentX(Component.CENTER_ALIGNMENT);

       // Add Components
       leftPanel.add(lblIcon);        // <-- මේක අනිවාර්යයි
       leftPanel.add(Box.createVerticalStrut(20));
       leftPanel.add(title);
       leftPanel.add(Box.createVerticalStrut(10));
       leftPanel.add(sub);
       leftPanel.add(Box.createVerticalGlue());

       main.add(leftPanel);

        // ==========================
        // Right Panel
        // ==========================

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(245,247,250));

        JPanel loginCard = new RoundedPanel(Color.WHITE,25);
        loginCard.setPreferredSize(new Dimension(340,420));
        loginCard.setLayout(new BoxLayout(loginCard,BoxLayout.Y_AXIS));
        loginCard.setBorder(
                BorderFactory.createEmptyBorder(30,30,30,30));

        rightPanel.add(loginCard);

        main.add(rightPanel);

        // ==========================
        // Login Title
        // ==========================

        JLabel lblLogin = new JLabel("LOGIN");

        lblLogin.setFont(
                new Font("Segoe UI",Font.BOLD,30));

        lblLogin.setForeground(
                new Color(33,150,243));

        lblLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginCard.add(Box.createVerticalGlue());
        loginCard.add(lblLogin);
        loginCard.add(Box.createVerticalStrut(30));


        // ==========================
        // Username
        // ==========================

        ImageIcon userIcon = new ImageIcon("icons/User.png");
        Image userImg = userIcon.getImage().getScaledInstance(
                20,20,Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(userImg);

        JLabel lblUser = new JLabel(
                "Username",
                userIcon,
                JLabel.LEFT);

        lblUser.setFont(
                new Font("Segoe UI",Font.BOLD,16));

        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblUser.setIconTextGap(10);

        txtUsername = new JTextField();

        txtUsername.setMaximumSize(
                new Dimension(270,45));

        txtUsername.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        txtUsername.setFont(
                new Font("Segoe UI",Font.PLAIN,16));

        loginCard.add(lblUser);
        loginCard.add(Box.createVerticalStrut(8));
        loginCard.add(txtUsername);
        loginCard.add(Box.createVerticalStrut(20));

        // ==========================
        // Password
        // ==========================

        ImageIcon passIcon = new ImageIcon("icons/Password.png");
        Image passImg = passIcon.getImage().getScaledInstance(
                20,20,Image.SCALE_SMOOTH);
        passIcon = new ImageIcon(passImg);

        JLabel lblPass = new JLabel(
                "Password",
                passIcon,
                JLabel.LEFT);

        lblPass.setFont(
                new Font("Segoe UI",Font.BOLD,16));

        lblPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPass.setIconTextGap(10);

        txtPassword = new JPasswordField();

        txtPassword.setMaximumSize(
                new Dimension(270,45));

        txtPassword.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        txtPassword.setFont(
                new Font("Segoe UI",Font.PLAIN,16));

        loginCard.add(lblPass);
        loginCard.add(Box.createVerticalStrut(8));
        loginCard.add(txtPassword);
        loginCard.add(Box.createVerticalStrut(20));

        // ==========================
        // Show Password
        // ==========================

        JCheckBox chkShow =
                new JCheckBox("Show Password");

        chkShow.setOpaque(false);
        chkShow.setAlignmentX(Component.CENTER_ALIGNMENT);
        chkShow.setFont(
                new Font("Segoe UI",Font.PLAIN,13));

        chkShow.addActionListener(e -> {

            if(chkShow.isSelected()){
                txtPassword.setEchoChar((char)0);
            }else{
                txtPassword.setEchoChar('•');
            }

        });

        loginCard.add(chkShow);
        loginCard.add(Box.createVerticalStrut(10));

        // ==========================
        // Remember Me
        // ==========================

        JCheckBox chkRemember =
                new JCheckBox("Remember Me");

        chkRemember.setOpaque(false);
        chkRemember.setAlignmentX(Component.CENTER_ALIGNMENT);

        chkRemember.setFont(
                new Font("Segoe UI",Font.PLAIN,13));

        loginCard.add(chkRemember);
        loginCard.add(Box.createVerticalStrut(10));

        // ==========================
        // Forgot Password
        // ==========================

        JLabel forgot =
                new JLabel("Forgot Password?");

        forgot.setForeground(
                new Color(33,150,243));

        forgot.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR));

        forgot.setAlignmentX(Component.CENTER_ALIGNMENT);

        forgot.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e){

                JOptionPane.showMessageDialog(
                        LoginFrame.this,
                        "Please contact the System Administrator.");

            }

        });

        loginCard.add(forgot);
        loginCard.add(Box.createVerticalStrut(25));


                // ==========================
        // Login Button
        // ==========================

        btnLogin = new JButton("LOGIN");

        btnLogin.setBackground(new Color(33,150,243));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnLogin.setMaximumSize(new Dimension(270,45));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginCard.add(btnLogin);

        loginCard.add(Box.createVerticalGlue());

        // ==========================
        // Button Hover Effect
        // ==========================

        btnLogin.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                btnLogin.setBackground(
                        new Color(25,118,210));

            }

            @Override
            public void mouseExited(MouseEvent e) {

                btnLogin.setBackground(
                        new Color(33,150,243));

            }

        });

        // ==========================
        // Login Action
        // ==========================

        btnLogin.addActionListener(e -> {

            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());

            if(username.equals("admin") &&
               password.equals("1234")){

                JOptionPane.showMessageDialog(
                        this,
                        "✅ Login Successful!");

                dispose();

                SwingUtilities.invokeLater(() -> {

                    new StudentGUI().setVisible(true);

                });

            }
            else{

                JOptionPane.showMessageDialog(
                        this,
                        "❌ Invalid Username or Password!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);

                txtPassword.setText("");
                txtPassword.requestFocus();

            }

        });

        // ==========================
        // Press ENTER to Login
        // ==========================

        getRootPane().setDefaultButton(btnLogin);

    }

}