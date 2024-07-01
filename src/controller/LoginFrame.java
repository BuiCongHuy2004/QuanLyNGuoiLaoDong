import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Đăng nhập");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        JLabel passwordLabel = new JLabel("Mật khẩu:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    dispose();
                    if (username.equals("admin")) {
                        AdminFrame adminFrame = new AdminFrame();
                        adminFrame.setVisible(true);
                    } else {
                        UserInfoFrame userInfoFrame = new UserInfoFrame();
                        userInfoFrame.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Tên đăng nhập hoặc mật khẩu không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);
    }

    private boolean authenticate(String username, String password) {
        // Giả sử "admin" là tài khoản quản trị viên và "user" là tài khoản người dùng
        return (username.equals("admin") && password.equals("adminpass")) ||
                (username.equals("user") && password.equals("userpass"));
    }
}

