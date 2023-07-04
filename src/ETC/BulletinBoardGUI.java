package src.ETC;              

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BulletinBoardGUI extends JFrame implements ActionListener {
    private JTable table;
    private JLabel ContentLabel;  // 변경된 부분     1
    private JLabel Title;
    private DefaultTableModel tableModel;	
    private JTextField titleField;
    private JTextArea contentField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
	private JLabel TitleLabel;
    




    public BulletinBoardGUI() {
        super("Bulletin Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);

        // 게시물 목록을 표시할 JTable
        tableModel = new DefaultTableModel(new Object[]{"Title", "Content"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 게시물 추가, 수정, 삭제를 위한 입력 필드와 버튼이 있는 패널
        ContentLabel = new JLabel("Content");  // 변경된 부분    2
        
        titleField = new JTextField(8);   
        contentField = new JTextArea(20,100);
        addButton = new JButton("생성");
        editButton = new JButton("수정");
        deleteButton = new JButton("삭제");

        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new BorderLayout());  // 제목 패널 추가
        JPanel contentPanel = new JPanel(new BorderLayout()); // 내용 패널 추가
        JPanel buttonPanel = new JPanel(new BorderLayout()); // 버튼 패널 추가


        // Increase the font size of the buttons
        Font buttonFont = addButton.getFont().deriveFont(Font.PLAIN, 16f);
        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);

        JPanel inputPanel1 = new JPanel(new BorderLayout());
        JPanel buttonPanel1 = new JPanel(new BorderLayout()); // 버튼을 담을 패널 생성
        buttonPanel1.setPreferredSize(new Dimension(20, 50)); // Increase the height of the button panel

        // Set preferred button sizes
        addButton.setPreferredSize(new Dimension(420, 30));
        editButton.setPreferredSize(new Dimension(80, 10));
        deleteButton.setPreferredSize(new Dimension(420, 30));
        
        buttonPanel1.add(addButton, BorderLayout.WEST);
        buttonPanel1.add(editButton, BorderLayout.CENTER);
        buttonPanel1.add(deleteButton, BorderLayout.EAST);


        inputPanel1.add(ContentLabel, BorderLayout.WEST);  // 변경된 부분

        inputPanel1.add(titleField, BorderLayout.NORTH); // 제목 필드를 위에 배치
        inputPanel1.add(new JScrollPane(contentField), BorderLayout.CENTER); // 내용 필드를 가운데 배치
        inputPanel1.add(buttonPanel1, BorderLayout.SOUTH); // 버튼 패널을 아래에 배치
        
        JLabel titleLabel = new JLabel("Title");  // Title 라벨 추가
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));  
        ContentLabel.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));// 왼쪽 여백 추가
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(titleField, BorderLayout.CENTER);
        


        contentPanel.add(new JScrollPane(contentField), BorderLayout.CENTER);

        inputPanel1.add(titlePanel, BorderLayout.NORTH);  // 제목 패널을 위에 배치
        inputPanel1.add(contentPanel, BorderLayout.CENTER); // 내용 패널을 가운데 배치
        inputPanel1.add(buttonPanel1, BorderLayout.SOUTH); // 버튼 패널을 아래에 배치

        add(inputPanel1, BorderLayout.SOUTH);

        // ...

        add(inputPanel1, BorderLayout.SOUTH);

        addButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);

        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String title = titleField.getText();
            String content = contentField.getText();
            if (!title.isEmpty() && !content.isEmpty()) {
                tableModel.addRow(new Object[]{title, content});
                titleField.setText("");
                contentField.setText("");
            }
        } else if (e.getSource() == editButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String title = titleField.getText();
                String content = contentField.getText();
                if (!title.isEmpty() && !content.isEmpty()) {
                    tableModel.setValueAt(title, selectedRow, 0);
                    tableModel.setValueAt(content, selectedRow, 1);
                    titleField.setText("");
                    contentField.setText("");
                
                }
        }
        }
    }
        public static void main(String[] args) {
            // BulletinBoardGUI 인스턴스 생성
            BulletinBoardGUI gui = new BulletinBoardGUI();
        }

		public JLabel ContentLabel() {
			return ContentLabel;
		}

		public void ContentLabel(JLabel ContentLabel) {
			this.ContentLabel = ContentLabel;
		}
		
    }
//안녕하세요 

    

