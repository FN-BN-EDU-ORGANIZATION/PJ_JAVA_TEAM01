package src.ETC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class QnA extends JFrame implements ActionListener {
	 private JTextArea textArea;
	    private JComboBox<String> titleComboBox;
	    private JButton createButton;
	    private JButton updateButton;
	    private JButton deleteButton;
	    private Map<String, String> articles;

    public QnA() {
        setTitle("글 관리");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 400);
        
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        titleComboBox = new JComboBox<>();
        titleComboBox.addActionListener(this);

        
        createButton = new JButton("글생성");
        updateButton = new JButton("수정");
        deleteButton = new JButton("삭제");

        createButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titleComboBox, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        articles = new HashMap<>();
        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            // 글 생성 버튼 동작
            String content = textArea.getText();
            if (!content.isEmpty()) {
                String title = JOptionPane.showInputDialog(this, "제목을 입력하세요.");
                if (title != null && !title.isEmpty()) {
                    articles.put(title, content);
                    titleComboBox.addItem(title);
                    JOptionPane.showMessageDialog(this, "글이 생성되었습니다.");
                    textArea.setText("");
                }
            }
        } else if (e.getSource() == updateButton) {
            // 글 수정 버튼 동작
            String selectedTitle = (String) titleComboBox.getSelectedItem();
            if (selectedTitle != null) {
                String newContent = textArea.getText();
                if (!newContent.isEmpty()) {
                    articles.put(selectedTitle, newContent);
                    JOptionPane.showMessageDialog(this, "글이 수정되었습니다.");
                }
            }
        } else if (e.getSource() == deleteButton) {
            // 글 삭제 버튼 동작
        	
            String selectedTitle = (String) titleComboBox.getSelectedItem();
            if (selectedTitle != null) {
                int choice = JOptionPane.showConfirmDialog(this, "글을 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    articles.remove(selectedTitle);
                    titleComboBox.removeItem(selectedTitle);
                    JOptionPane.showMessageDialog(this, "글이 삭제되었습니다.");
                    textArea.setText("");
                }
            }
        } else if (e.getSource() == titleComboBox) {
            // 제목 선택 동작
            String selectedTitle = (String) titleComboBox.getSelectedItem();
            if (selectedTitle != null) {
                String content = articles.get(selectedTitle);
                if (content != null) {
                    textArea.setText(content);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QnA());
    }
}