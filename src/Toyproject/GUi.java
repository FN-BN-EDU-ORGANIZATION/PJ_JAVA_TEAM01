package Toyproject;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

class GUI extends JFrame implements ActionListener, KeyListener {
    // Components
    JButton searchButton; // 검색 버튼
    JTextField searchField;
    JTable table;
    DefaultTableModel model;
    JScrollPane scrollPane;

    ImageIcon backgroundImage;
    
    GUI() {
        super(); // 상위 클래스 생성자 호출

        setTitle("MUSIC SEARCHING...");
        
       
        
        // 패널 추가
        JPanel panel = new JPanel();

        // 배경 이미지 설정
        try {
            String imagePath = "C:\\Users\\personal\\Desktop\\38672151_m-1000x400.png";
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
           //hi            
        // Components
        searchButton = new JButton("검색"); 
        searchField = new JTextField();
        model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);       
        
        // Component 위치 지정
        searchButton.setBounds(890, 50, 60, 30);
        searchField.setBounds(40, 50, 840, 30);
        scrollPane.setBounds(40, 100, 910, 200);

        // Event 처리
        searchButton.addActionListener(this);
        searchField.addKeyListener(this);

        // 패널에 Component 추가
        panel.add(searchButton);
        panel.add(searchField);
        panel.add(scrollPane);

        // 레이아웃 null
        panel.setLayout(null);
        
        
        
        
        // 패널을 프레임에 추가
        add(panel);
        setBounds(100, 100, 1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    // 배경
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(backgroundImage !=null) {
        // 배경화면 그리기
        g.drawImage(backgroundImage.getImage(), 0, 0, null);
    }
    }
                    
    @Override
    public void paintComponents(Graphics g) {
    	super.paintComponents(g);
    	// 배경화면 그리기
    	g.drawImage(backgroundImage.getImage(), 0,0, null);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == searchField) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                searchButton.doClick(); // 검색 버튼을 누름
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchTracks();
        }
    }
    

    private void searchTracks() {
        try {
            String apiKey = "354ad741231e3c7ae853e84460461072";
            String encodedTrack = URLEncoder.encode(searchField.getText(), "UTF-8");

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

    public static void main(String[] args) {
        new GUI();
    }
}