import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatisticsFrame extends JFrame {
    private JLabel totalUsersLabel;
    private JLabel maleUsersLabel;
    private JLabel femaleUsersLabel;
    private JLabel averageIncomeLabel;

    public StatisticsFrame() {
        setTitle("Thống kê người dùng");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        totalUsersLabel = new JLabel("Tổng số người dùng: ");
        maleUsersLabel = new JLabel("Số người dùng nam: ");
        femaleUsersLabel = new JLabel("Số người dùng nữ: ");
        averageIncomeLabel = new JLabel("Thu nhập trung bình: ");

        add(totalUsersLabel);
        add(maleUsersLabel);
        add(femaleUsersLabel);
        add(averageIncomeLabel);
    }

    public void updateStatistics(List<User> users) {
        int totalUsers = users.size();
        int maleUsers = 0;
        int femaleUsers = 0;
        double totalIncome = 0;

        for (User user : users) {
            totalIncome += user.getIncome();
        }

        double averageIncome = totalUsers > 0 ? totalIncome / totalUsers : 0;

        totalUsersLabel.setText("Tổng số người dùng: " + totalUsers);
        maleUsersLabel.setText("Số người dùng nam: " + maleUsers);
        femaleUsersLabel.setText("Số người dùng nữ: " + femaleUsers);
        averageIncomeLabel.setText("Thu nhập trung bình: " + averageIncome);
    }
}
