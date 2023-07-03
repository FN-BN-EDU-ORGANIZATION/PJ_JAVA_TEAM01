package src.View;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MainGUI extends JFrame implements ActionListener {

	JTable table;
	JScrollPane tableScroll;
	JTextField txt; //검색버튼
	JButton btn1; //로그인
	JButton btn2; //게시판
	JButton btn3; //검색버튼
	
	
	//
	LoginUI loginUI;
	
	//테이블 열이름
	String[] columnNames = {"TITLE", "ARTIST", "URL"};
			
	//테이블 모델
	DefaultTableModel model = new DefaultTableModel(columnNames,0);
	
	MainGUI() {

		super("MAIN MENU");
		setBounds(100, 100, 1000, 400);

		// 패널
		JPanel panel = new JPanel();
		panel.setLayout(null);

		
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
	
	
	
	private void searchTracks() {
        try {
            String apiKey = "354ad741231e3c7ae853e84460461072";
            String encodedTrack = URLEncoder.encode(txt.getText(), "UTF-8");

            String apiUrl = "http://ws.audioscrobbler.com/2.0/?method=track.search&track=" + encodedTrack
                    + "&limit=300&api_key=" + apiKey + "&format=json";
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode trackMatches = root.path("results").path("trackmatches").path("track");

            model.setColumnCount(0);
            model.setRowCount(0);

            model.addColumn("TITLE");
            model.addColumn("ARTIST");
            model.addColumn("URL");

            for (JsonNode trackNode : trackMatches) {
                String name = trackNode.path("name").asText();
                String artist = trackNode.path("artist").asText();
                String url = trackNode.path("url").asText();

                Object[] rowData = { name, artist, url };
                model.addRow(rowData);
                
                TableColumnModel columnModel = table.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(100);
                columnModel.getColumn(1).setPreferredWidth(100);
                columnModel.getColumn(2).setPreferredWidth(400);
                
            }

            // 타이틀 클릭 시 URL로 이동하는 기능 추가
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());
                    if (col == 0) { // Title column 클릭 시
                        String url = (String) table.getValueAt(row, 2);
                        if (url != null && !url.isEmpty()) {
                            openWebpage(url);
                        }
                    }
                }
            });
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    // URL로 이동하는 메소드
    private void openWebpage(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btn1) { //로그인화면
			System.out.println("BTN1 CLICK ");
			loginUI.setVisible(true);
			this.setVisible(false);
		}else if(e.getSource()==btn2) { //QnA화면
			System.out.println("BTN2 CLICK ");
		}else if(e.getSource()==btn3) { //검색
			System.out.println("BTN3 CLICK ");
			searchTracks();

		}
		
	}

	
}
