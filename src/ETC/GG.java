package src.ETC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GG extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextArea contentField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton1;
    private JComboBox<String> titleComboBox;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private Map<String, String> articles;

    public GG() {
        super("Bulletin Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);

        // 게시물 목록을 표시할 JTable
        tableModel = new DefaultTableModel(new Object[]{"Title", "Content"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 게시물 추가, 수정, 삭제를 위한 입력 필드와 버튼이 있는 패널
        titleField = new JTextField(10);
        contentField = new JTextArea(10, 40);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton1 = new JButton("Delete");

        JPanel inputPanel = new JPanel();
        inputPanel.add(titleField);
        inputPanel.add(new JScrollPane(contentField)); // JScrollPane로 감싸서 스크롤 기능 추가
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton1);
        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton1.addActionListener(this);

        // 글 관리 기능을 위한 컴포넌트
        titleComboBox = new JComboBox<>();
        titleComboBox.addActionListener(this);

        createButton = new JButton("글 생성");
        updateButton = new JButton("수정");
        deleteButton1 = new JButton("삭제");

        createButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton1.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton1);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titleComboBox, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        articles = new HashMap<>();

        setVisible(true);
    }

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

        
        