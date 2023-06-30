package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class MainGUI extends JFrame implements ActionListener  {

	JTable table;
	JScrollPane tableScroll;
	JTextField txt; //검색버튼
	JButton btn1; //로그인
	JButton btn2; //게시판
	JButton btn3; //검색버튼
	
	ImageIcon backgroundImage;
	
	//
	LoginUI loginUI;
	
	MainGUI() {

		super("MAIN MENU");
		setBounds(100, 100, 1000, 400);

		// 패널
		JPanel panel = new JPanel();
		panel.setLayout(null);

		//배경이미지 설정
		 try {
	            String imagePath = "C:\\Users\\sori2\\OneDrive\\바탕 화면\\back.png";
	            File imageFile = new File(imagePath);

	            if (imageFile.exists()) {
	                BufferedImage image = ImageIO.read(imageFile);
	                if (image != null) {
	                    backgroundImage = new ImageIcon(image);
	                } else {
	                    // 이미지를 로드할 수 없는 경우 처리
	                }
	            } else {
	                // 파일이 존재하지 않는 경우 처리
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
		//테이블 열이름
		String[] columnNames = {"TITLE", "ARTIST", "URL"};
		// 테이블 데이터 x 
		//테이블 모델
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		
		
		// 컴포넌트
		table = new JTable(model);
		tableScroll = new JScrollPane(table);
		
		txt = new JTextField("Search Keyword를 입력하세요.");
		btn1 = new JButton("로그인");
		btn2 = new JButton("QnA");
		btn3 = new JButton("검색");
		
		
		// 위치 조정
		btn1.setBounds(40,30,100,30); //로그인버튼
		btn2.setBounds(160,30,100,30); //QnA게시판버튼
		btn3.setBounds(890,80,60,30); //검색버튼
		txt.setBounds(40,80,820,30); //검색창
		tableScroll.setBounds(40,130,910,200); //테이블
		
		//EVENT
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		// 컴포넌트를 패널에 추가
		panel.add(txt);
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(tableScroll);
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		//loginUI
		loginUI = new LoginUI();
		loginUI.setVisible(false);
		loginUI.setMainGUI(this);
		

		//검색창 클릭 시 기본문구 없어지게 하기
		txt.addFocusListener(new FocusListener() {
		@Override
		public void focusGained(FocusEvent e) {
		   if (txt.getText().equals("Search Keyword를 입력하세요!")) {
		       txt.setText("");
		       }
		    }

		@Override
		public void focusLost(FocusEvent e) {
		   if (txt.getText().isEmpty()) {
		       txt.setText("Search Keyword를 입력하세요.");
		       }
		    }
		});
		
		
		
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btn1) {
			System.out.println("BTN1 CLICK ");
			loginUI.setVisible(true);
			this.setVisible(false);
		}else if(e.getSource()==btn2) {
			System.out.println("BTN2 CLICK ");
		}else if(e.getSource()==btn3) {
			System.out.println("BTN3 CLICK ");
			
			loginUI.setVisible(true);
			this.setVisible(false);
		}
		
	}
	
	
//	// 배경
//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//		if(backgroundImage !=null) {
//			// 배경화면 그리기
//			g.drawImage(backgroundImage.getImage(), 0, 0, null);
//		}
//	}
//	
//	@Override
//	public void paintComponents(Graphics g) {
//		super.paintComponents(g);
//		// 배경화면 그리기
//		g.drawImage(backgroundImage.getImage(), 0,0, null);
//	}
	
	
}
