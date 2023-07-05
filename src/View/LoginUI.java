package src.View;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import src.Controller.FrontController;


public class LoginUI extends JFrame implements ActionListener, KeyListener{

	private FrontController controller;

	JTextField id_txt;
	JPasswordField pw_txt;
	JButton login_btn;
	JButton join_btn;
	Label L_id;
	Label L_pw;
	
	JFrame Frm_login;
	
	//
	MainGUI maingui;
	Log_MainGUI logmaingui;
	JoinUI joinUI;
	
//	MemberUI membergui;
//	UserUI usergui;
	

	
	public LoginUI(){
		super("LOGIN");
		setBounds(400, 100, 300, 170);

		// 패널
		JPanel panel = new JPanel();
		panel.setLayout(null);

		
		//
		id_txt = new JTextField();
		pw_txt = new JPasswordField();
		login_btn = new JButton("로그인");
		join_btn = new JButton("회원가입");
		L_id = new Label(" ID");
		L_pw = new Label("PW");
		
		//
		id_txt.setBounds(40,15,140,30);
		pw_txt.setBounds(40,50,140,30);
		login_btn.setBounds(185,15,80,65);
		join_btn.setBounds(40,90,225,30);
		L_id.setBounds(10, 15, 30, 30);
		L_pw.setBounds(10, 50, 30, 30);
		
		//EVENT
		login_btn.addActionListener(this);
		join_btn.addActionListener(this);
		pw_txt.addKeyListener(this); //pw_txt에서 엔터를 눌렀을 때 검색버튼 눌리도록!
		
		// 컴포넌트를 패널에 추가
		panel.add(id_txt);
		panel.add(pw_txt);
		panel.add(login_btn);
		panel.add(join_btn);
		panel.add(L_id);
		panel.add(L_pw);
		 
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		controller = new FrontController();
		
		//joinUI
		joinUI = new JoinUI();
		joinUI.setVisible(false);
//		joinUI.setMainGUI(this);
		
		
//		membergui = new MemberUI();
//		usergui = new UserUI();
		
		
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	maingui = new MainGUI();
            	maingui.setVisible(true);
            	
            	
            }
        });
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login_btn) {
			System.out.println("LOGIN_BTN");
			String id = id_txt.getText();
			String pw = pw_txt.getText();
			
			Map<String, Object> param = new HashMap();
			param.put("id", id);
			param.put("pw", pw);
			
			Map<String, Object> result = controller.execute("/member", 5, param, id);
			if(result!=null) {
				JOptionPane.showMessageDialog(null,"로그인에 성공했습니다!", "LogIn",JOptionPane.INFORMATION_MESSAGE);
				logmaingui = new Log_MainGUI();
				logmaingui.setVisible(true);
				this.setVisible(false);
				this.setUserName(id);
				
			} else{
				JOptionPane.showMessageDialog(null,"로그인에 실패했습니다..", "LogIn",JOptionPane.ERROR_MESSAGE);	
				this.Frm_login = new JFrame();
				Frm_login.setVisible(false);
				}
		} else if(e.getSource()==join_btn) {
			System.out.println("JOIN_BTN");
			joinUI = new JoinUI();
			joinUI.setVisible(true);
			this.setVisible(false);
		}
	}
	
	@Override
    public void keyPressed(KeyEvent e) { //엔터키 눌렀을 때 로그인버튼이 눌리도록
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Simulate a click on the login button
            login_btn.doClick();
		}
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setUserName(String username) {
		logmaingui.label.setText(username+"님, 자유롭게 음악을 검색해보세요!");
	}
	
	
	public void setMainGUI(MainGUI maingui) {
		this.maingui = maingui;
//		membergui.setMainUI(maingui);
//		usergui.setMainUI(maingui);
		
		
	}
	
}