package src.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import src.Controller.FrontController;

public class Log_MainGUI extends JFrame implements ActionListener, KeyListener, MouseListener {


	private FrontController controller;
	
	JTable table;
	JScrollPane tableScroll;
	JTextField txt; //검색버튼
	JButton btn1; //로그인
	JButton btn2; //게시판
	JButton btn3; //검색버튼
	JButton btn4; //검색기록확인
	JLabel label; //유저 확인
		
	//
	LoginUI loginUI;
	MainGUI maingui;
	
	//테이블 열이름
	String[] columnNames = {"TITLE", "ARTIST", "URL"};
			
	//테이블 모델
	public DefaultTableModel model = new DefaultTableModel(columnNames,0);
	
	public Log_MainGUI() {

		
		
		super("MAIN MENU_LOGIN SUCCESS!");
		setBounds(100, 100, 1000, 400);
		
		controller = new FrontController();
		
		// 패널
		JPanel panel = new JPanel();
		panel.setLayout(null);

		//loginUI
		loginUI = new LoginUI();
		loginUI.setVisible(false);
//		loginUI.setMainGUI(this);
		
		// 컴포넌트
		table = new JTable(model);
		tableScroll = new JScrollPane(table);
		
		txt = new JTextField("Search Keyword를 입력하세요.");
		btn1 = new JButton("로그아웃");
		btn2 = new JButton("QnA");
		btn3 = new JButton("검색");
		btn4 = new JButton("검색기록");
		label = new JLabel();
		
		
		
		// 위치 조정
		btn1.setBounds(40,30,100,30); //로그아웃버튼
		btn2.setBounds(160,30,100,30); //QnA게시판버튼
		btn3.setBounds(890,80,60,30); //검색버튼
		btn4.setBounds(850,30,100,30); //검색기록
		label.setBounds(350,30,250,30); //label
		txt.setBounds(40,80,820,30); //검색창
		tableScroll.setBounds(40,130,910,200); //테이블
		
		//EVENT
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		txt.addKeyListener(this);
		table.addMouseListener(this);
		
		
		// 컴포넌트를 패널에 추가
		panel.add(txt);
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);
		panel.add(label);
		panel.add(tableScroll);
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		
		

		//검색창 클릭 시 기본문구 없어지게 하기
		txt.addFocusListener(new FocusListener() {
		@Override
		public void focusGained(FocusEvent e) {
		   if (txt.getText().equals("Search Keyword를 입력하세요.")) {
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
	public void showSearchHistory() {
	    // 검색 기록을 가져옵니다.
	    List<String> searchHistory = controller.getSearchHistory();
	    
	    // 검색 기록을 보여주는 간단한 창 생성
	    JFrame historyFrame = new JFrame("검색 기록");
	    historyFrame.setSize(400, 300);
	    
	    // 검색 기록을 표시하는 JTextArea 생성
	    JTextArea historyTextArea = new JTextArea();
	    JScrollPane scrollPane = new JScrollPane(historyTextArea);
	    scrollPane.setBounds(10, 10, 380, 280);
	    
	    // 검색 기록을 JTextArea에 추가
	    for (String history : searchHistory) {
	        historyTextArea.append(history + "\n");
	    }
	    // 컴포넌트를 창에 추가
	    historyFrame.add(scrollPane);
	    
	    // 창 표시
	    historyFrame.setVisible(true);
	    
	}
	
	public void performSearch() {
        // 검색 기능을 실행하기 위해 FrontController의 execute() 메서드 호출
		String searchText = txt.getText();
        controller.searchTracks(searchText);
        updateTable(controller.getTableModel());
    }
	@Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == txt) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                btn3.doClick(); // 검색 버튼을 누름
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    @Override
	public void mouseClicked(MouseEvent e) {
    	if (e.getSource() == table && e.getClickCount() == 1) {
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();

            // URL 주소 열 클릭 시 URL 주소 열기
            String url = (String) table.getValueAt(row, 2);
            controller.openWebpage(url);
        }
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btn1) { //로그아웃화면
			System.out.println("BTN1 CLICK ");
			loginUI = new LoginUI();
			loginUI.setVisible(true);
			this.setVisible(false);
		}else if(e.getSource()==btn2) { //QnA화면
			System.out.println("BTN2 CLICK ");
		}else if(e.getSource()==btn3) { //검색
			System.out.println("BTN3 CLICK ");
			performSearch();
		}else if(e.getSource()==btn4) {
			System.out.println("BTN4 CLICK ");
			showSearchHistory();
		}
		
	}
	
	// 테이블 업데이트 메소드
	private void updateTable(DefaultTableModel tableModel) {
	    table.setModel(tableModel);
	    TableColumnModel columnModel = table.getColumnModel();
	    columnModel.getColumn(0).setPreferredWidth(100);
	    columnModel.getColumn(1).setPreferredWidth(100);
	    columnModel.getColumn(2).setPreferredWidth(400);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//	public static void main(String[] args) {
//		new Log_MainGUI();
//	}
	

}