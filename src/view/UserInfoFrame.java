import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class UserInfoFrame extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField genderField;
    private JComboBox<String> genderComboBox;
    private JTextField ageField;
    private JTextField birthDateField;
    private JTextField addressField;
    private JTextField permanentAddressField;
    private JTextField occupationField;
    private JTextField maritalStatusField;
    private JComboBox<String> maritalStatusComboBox;
    private JTextField incomeField;
    private JTextField idField;
    private JLabel imageLabel;
    private JButton uploadButton;
    private File imageFile;
    private List<User> users;
    private JList<User> userList;
    private DefaultListModel<User> listModel;
    private StatisticsFrame statisticsFrame;
    // Bảng thống kê
    private JLabel maleCountLabel;
    private JLabel femaleCountLabel;
    private JLabel averageIncomeLabel;

    public UserInfoFrame() {
        users = new ArrayList<>();

        setTitle("Thông tin người dùng");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        statisticsFrame = new StatisticsFrame();

        JPanel inputPanel = new JPanel(new GridLayout(15, 2));

        JLabel firstNameLabel = new JLabel("Tên:");
        JLabel lastNameLabel = new JLabel("Họ:");
        JLabel genderLabel = new JLabel("Giới tính:");
        JLabel ageLabel = new JLabel("Tuổi:");
        JLabel birthDateLabel = new JLabel("Ngày sinh (dd/MM/yyyy):");
        JLabel addressLabel = new JLabel("Nơi ở:");
        JLabel permanentAddressLabel = new JLabel("Hộ khẩu thường trú:");
        JLabel occupationLabel = new JLabel("Nghề nghiệp:");
        JLabel maritalStatusLabel = new JLabel("Tình trạng hôn nhân:");
        JLabel incomeLabel = new JLabel("Thu nhập:");
        JLabel idLabel = new JLabel("Số CMTND:");
        JLabel imageLabelTitle = new JLabel("Ảnh đại diện:");

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        genderComboBox = new JComboBox<>(new String[]{"Null","Nam", "Nữ"});
        ageField = new JTextField();
        birthDateField = new JTextField();
        addressField = new JTextField();
        permanentAddressField = new JTextField();
        occupationField = new JTextField();
        maritalStatusComboBox = new JComboBox<>(new String[]{"Null", "Married", "Unmarried"});
        incomeField = new JTextField();
        idField = new JTextField();
        imageLabel = new JLabel();

        uploadButton = new JButton("Tải ảnh");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    imageFile = fileChooser.getSelectedFile();
                    imageLabel.setIcon(new ImageIcon(new ImageIcon(imageFile.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                }
            }
        });

        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    if (age < 18 || age > 60) {
                        JOptionPane.showMessageDialog(UserInfoFrame.this, "Tuổi phải từ 18 đến 60", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String gender = (String) genderComboBox.getSelectedItem();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date birthDate = sdf.parse(birthDateField.getText());
                    String address = addressField.getText();
                    String permanentAddress = permanentAddressField.getText();
                    String occupation = occupationField.getText();
                    String maritalStatus = (String) maritalStatusComboBox.getSelectedItem();
                    double income = Double.parseDouble(incomeField.getText());
                    String id = idField.getText();
                    String imagePath = imageFile != null ? imageFile.getAbsolutePath() : "";
                    // Kiểm tra trùng tên và số CCCD
                    for (User user : users) {
                        if (user.getFirstName().equalsIgnoreCase(firstName) && user.getLastName().equalsIgnoreCase(lastName)) {
                            JOptionPane.showMessageDialog(UserInfoFrame.this, "Tên người dùng đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (user.getId().equals(id)) {
                            JOptionPane.showMessageDialog(UserInfoFrame.this, "Số CCCD đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    users.add(new User(firstName, lastName, gender, age, birthDate, address, permanentAddress, occupation, maritalStatus, income, id, imagePath));
                    updateUserList();
                    updateStatistics();
                    JOptionPane.showMessageDialog(UserInfoFrame.this, "Lưu thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(UserInfoFrame.this, "Ngày tháng không đúng định dạng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UserInfoFrame.this, "Thu nhập không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton deleteButton = new JButton("Xóa");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User selectedUser = userList.getSelectedValue();
                if (selectedUser != null) {
                    users.remove(selectedUser);
                    listModel.removeElement(selectedUser);
                    updateUserList();
                    updateStatistics();
                    JOptionPane.showMessageDialog(UserInfoFrame.this, "Xóa người dùng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(UserInfoFrame.this, "Vui lòng chọn người dùng để xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }

            private void clearFields() {
                firstNameField.setText("");
                lastNameField.setText("");
                ageField.setText("");
                genderField.setText("");
                birthDateField.setText("");
                addressField.setText("");
                permanentAddressField.setText("");
                occupationField.setText("");
                maritalStatusField.setText("");
                incomeField.setText("");
                idField.setText("");
                imageLabel.setIcon(null);
                imageFile = null;
            }
        });
        JButton showStatisticsButton = new JButton("Hiển thị thống kê");
        showStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statisticsFrame.updateStatistics(users);
                statisticsFrame.setVisible(true);
            }
        });

        inputPanel.add(firstNameLabel);
        inputPanel.add(firstNameField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(genderLabel);
        inputPanel.add(genderComboBox);
        inputPanel.add(ageLabel);
        inputPanel.add(ageField);
        inputPanel.add(birthDateLabel);
        inputPanel.add(birthDateField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(permanentAddressLabel);
        inputPanel.add(permanentAddressField);
        inputPanel.add(occupationLabel);
        inputPanel.add(occupationField);
        inputPanel.add(maritalStatusLabel);
        inputPanel.add(maritalStatusComboBox);
        inputPanel.add(incomeLabel);
        inputPanel.add(incomeField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(imageLabelTitle);
        inputPanel.add(uploadButton);
        inputPanel.add(imageLabel);
        inputPanel.add(clearButton);
        inputPanel.add(saveButton);
        inputPanel.add(deleteButton);
        inputPanel.add(showStatisticsButton);
        add(inputPanel, BorderLayout.NORTH);

        userList = new JList<>();
        add(new JScrollPane(userList), BorderLayout.CENTER);

        // Thêm bảng thống kê
        JPanel statisticsPanel = new JPanel(new GridLayout(1, 3));
        maleCountLabel = new JLabel("Số lượng nam: 0");
        femaleCountLabel = new JLabel("Số lượng nữ: 0");
        averageIncomeLabel = new JLabel("Thu nhập trung bình: 0");

        statisticsPanel.add(maleCountLabel);
        statisticsPanel.add(femaleCountLabel);
        statisticsPanel.add(averageIncomeLabel);

        add(statisticsPanel, BorderLayout.SOUTH);

        JPanel searchPanel = new JPanel(new GridLayout(1, 2));
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Tìm kiếm");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = searchField.getText();
                List<User> results = searchUser(searchName);
                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(UserInfoFrame.this, "Không tìm thấy người dùng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    userList.setListData(results.toArray(new User[0]));
                }
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.SOUTH);
    }

    private void updateUserList() {
        userList.setListData(users.toArray(new User[0]));
    }

    private List<User> searchUser(String name) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(name) || user.getLastName().equalsIgnoreCase(name)) {
                result.add(user);
            }
        }
        return result;
    }
    private void updateStatistics() {
        int maleCount = 0;
        int femaleCount = 0;
        double totalIncome = 0;

        for (User user : users) {
            totalIncome += user.getIncome();
        }

        int totalUsers = users.size();
        double averageIncome = totalUsers > 0 ? totalIncome / totalUsers : 0;

        maleCountLabel.setText("Số lượng nam: " + maleCount);
        femaleCountLabel.setText("Số lượng nữ: " + femaleCount);
        averageIncomeLabel.setText("Thu nhập trung bình: " + averageIncome);
    }
    public static List<User> getUsers() {
        return UserManager.getUserDatabase();
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
