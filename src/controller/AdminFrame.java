import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminFrame extends JFrame {
    private JList<User> userList;

    public AdminFrame() {
        setTitle("Quản trị viên");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        userList = new JList<>();
        add(new JScrollPane(userList), BorderLayout.CENTER);

        JButton refreshButton = new JButton("Làm mới danh sách");
        refreshButton.addActionListener(e -> updateUserList());

        add(refreshButton, BorderLayout.SOUTH);
        updateUserList();
    }

    private void updateUserList() {
        List<User> users = UserInfoFrame.getUsers();
        userList.setListData(users.toArray(new User[0]));
    }
}
