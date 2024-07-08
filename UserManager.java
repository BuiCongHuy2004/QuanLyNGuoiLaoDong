import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static List<User> userDatabase = new ArrayList<>();
    private static int age;

    public static boolean authenticate(String username, String password) {
        for (User user : userDatabase) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean addUser(String username, String password) {
        
        if (age < 18 || age > 60) {
            return false; // Age not allowed
        }
        for (User user : userDatabase) {
            if (user.getUsername().equals(username)) {
                return false; // Username already exists
            }
        }
        return true;
    }
    public static List<User> getUserDatabase() {
        return userDatabase;
    }
}
