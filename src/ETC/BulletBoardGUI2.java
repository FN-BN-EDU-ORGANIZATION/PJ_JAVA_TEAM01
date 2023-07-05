package src.ETC;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BulletBoardGUI2 extends JFrame implements ActionListener {
    private JButton loginButton;
    private JButton logoutButton;
    private boolean loggedIn = false; // 로그인 상태

    public BulletBoardGUI2() {
        super("Bulletin Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel();

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        logoutButton.setEnabled(false); // 로그아웃 버튼은 초기에 비활성화 상태로 설정
        panel.add(logoutButton);

        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            loggedIn = true;
            loginButton.setEnabled(false);
            logoutButton.setEnabled(true);
            System.out.println("Logged in");
        } else if (e.getSource() == logoutButton) {
            loggedIn = false;
            loginButton.setEnabled(true);
            logoutButton.setEnabled(false);
            System.out.println("Logged out");
        }
    }

    public static void main(String[] args) {
        new BulletBoardGUI2();
    }
}