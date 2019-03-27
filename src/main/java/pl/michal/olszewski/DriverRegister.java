package pl.michal.olszewski;

public class DriverRegister {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
