package src.ETC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BulletinBoardGUI extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextField contentField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    public BulletinBoardGUI() {
        super("Bulletin Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // 게시물 목록을 표시할 JTable
        tableModel = new DefaultTableModel(new Object[]{"Title", "Content"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 게시물 추가, 수정, 삭제를 위한 입력 필드와 버튼이 있는 패널
        titleField = new JTextField(20);
        contentField = new JTextField(20);
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        JPanel inputPanel = new JPanel();
        inputPanel.add(titleField);
        inputPanel.add(contentField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);
        add(inputPanel, BorderLayout.SOUTH);

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
        } else if (e.getSource() == deleteButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                titleField.setText("");
                contentField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new BulletinBoardGUI();
    }
}
