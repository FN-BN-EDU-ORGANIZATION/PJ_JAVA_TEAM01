package src.View;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JoinUI extends JFrame implements ActionListener{

	JTable table;
	JTextField txt1; //아이디
	JTextField txt2; //비밀번호
	JTextField txt3; //비밀번호확인
	JTextField txt4; //이름
	JTextField txt5; //주소
	JTextField txt6; //핸드폰번호
	JButton btn1; //가입버튼
	JButton btn2; //뒤로가기버튼(로그인화면출력)
	Label L_txt1; 
	Label L_txt2;
	Label L_txt3;
	Label L_txt4;
	Label L_txt5;
	Label L_txt6;
	
	LoginUI loginUI;
	MainGUI mainGUI;
	
	
	
	JoinUI(){
		super("JOIN US!");
		setBounds(100, 100, 400, 380);
		
		// 패널
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		//
		txt1 = new JTextField();
		txt2 = new JTextField();
		txt3 = new JTextField();
		txt4 = new JTextField();
		txt5 = new JTextField();
		txt6 = new JTextField();
		btn1 = new JButton("돌아가기");
		btn2 = new JButton("회원가입");
		L_txt1 = new Label("ID");
		L_txt2 = new Label("PW");
		L_txt3 = new Label("PW Check");
		L_txt4 = new Label("NAME");
		L_txt5 = new Label("ADDRESS");
		L_txt6 = new Label("PHONE");
		
		//
		txt1.setBounds(120,10,230,30);
		txt2.setBounds(120,50,230,30);
		txt3.setBounds(120,90,230,30);
		txt4.setBounds(120,130,230,30);
		txt5.setBounds(120,170,230,30);
		txt6.setBounds(120,210,230,30);
		btn1.setBounds(30,270,100,40);//뒤로
		btn2.setBounds(250,270,100,40);//가입
		L_txt1.setBounds(50,10,60,30);
		L_txt2.setBounds(45,50,60,30);
		L_txt3.setBounds(30,90,60,30);
		L_txt4.setBounds(40,130,60,30);
		L_txt5.setBounds(30,170,60,30);
		L_txt6.setBounds(35,210,60,30);
		
		//EVENT
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		//컴포넌트 패널에 추가
		panel.add(txt1);
		panel.add(txt2);
		panel.add(txt3);
		panel.add(txt4);
		panel.add(txt5);
		panel.add(txt6);
		panel.add(btn1);
		panel.add(btn2);
		panel.add(L_txt1);
		panel.add(L_txt2);
		panel.add(L_txt3);
		panel.add(L_txt4);
		panel.add(L_txt5);
		panel.add(L_txt6);
		
		
		add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		//LoginUI
//		loginUI = new LoginUI();
//		loginUI.setVisible(false);
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1) {
			System.out.println("BACK_BTN");
			this.loginUI = new LoginUI();
			this.loginUI.setVisible(true);
			loginUI.setVisible(true);
			this.setVisible(false);
		}else if(e.getSource()==btn2) {
			System.out.println("JOIN_BTN");
		}
		
	}

//	public static void main(String[] args) {
//		new JoinUI();
//	}
	
}
