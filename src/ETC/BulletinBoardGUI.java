package src.ETC;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import src.Domain.Domain1.Dto.QnADto;
import src.Domain.Domain1.Service.QnAService.PostService;


public class BulletinBoardGUI extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextArea contentField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel contentLabel;
    private ActionListener actionListener;
    private PostService postService;
    private JList<QnADto> qnaList;
    private int selectedQnaIndex;
   
    
    public void setSelectedQnaIndex(int index) {
        selectedQnaIndex = index;
    }
    public int getSelectedQnaIndex() {
        return selectedQnaIndex;
    }
    public QnADto getSelectedQna() {
        if (selectedQnaIndex >= 0 && selectedQnaIndex < qnaList.size()) {
            return qnaList.get(selectedQnaIndex);
        } else {
            return null;
        }
    }

    public void setPostService(PostService postService) {
    	this.postService = postService;
    }
    public void addActionListener(PostService postService2) {
        this.actionListener = (ActionListener) postService2;
    }
    public List<QnADto> getPostList() {
        if (postService != null) {
            return postService.getPostList();
        }
        return null;
    }
    public BulletinBoardGUI() {
        super("Bulletin Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);

        tableModel = new DefaultTableModel(new Object[]{"Title", "Content"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        titleLabel = new JLabel("Title");
        contentLabel = new JLabel("Content");

        titleField = new JTextField(8);
        contentField = new JTextArea(20, 100);
        addButton = new JButton("생성");
        editButton = new JButton("수정");
        deleteButton = new JButton("삭제");

        JPanel inputPanel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new BorderLayout());
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new BorderLayout());

        Font buttonFont = addButton.getFont().deriveFont(Font.PLAIN, 16f);
        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);

        addButton.setPreferredSize(new Dimension(420, 30));
        editButton.setPreferredSize(new Dimension(80, 10));
        deleteButton.setPreferredSize(new Dimension(420, 30));

        buttonPanel.add(addButton, BorderLayout.WEST);
        buttonPanel.add(editButton, BorderLayout.CENTER);
        buttonPanel.add(deleteButton, BorderLayout.EAST);

        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(titleField, BorderLayout.CENTER);

        contentPanel.add(contentLabel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(contentField), BorderLayout.CENTER);

        inputPanel.add(titlePanel, BorderLayout.NORTH);
        inputPanel.add(contentPanel, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this);
        editButton.addActionListener(this);
        deleteButton.addActionListener(this);

        setVisible(true);
    	}
    	public JButton getAddButton() {
    		return addButton;
    	}
    	
    	public JButton getEditButton() {
    		return editButton;
    	}

    	public JButton getDeleteButton() {
    		return deleteButton;
    	}

    	public JTextField getTitleField() {
    		return titleField;
    	}

    	public JTextArea getContentField() {
    		return contentField;
    	}

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (actionListener != null) {
            actionListener.actionPerformed(e);
        }
    }

    public static void main(String[] args) {
        BulletinBoardGUI gui = new BulletinBoardGUI();
    }
}