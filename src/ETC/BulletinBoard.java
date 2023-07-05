package src.ETC;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BulletinBoard extends JFrame {
    private List<Post> posts;
    private JTextField titleTextField;
    private JTextArea contentTextArea;
    private DefaultListModel<String> postListModel;
    private JList<String> postList;

    public BulletinBoard() {
        // 게시글 목록 초기화
        posts = new ArrayList<>();

        // 윈도우 설정
        setTitle("Bulletin Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // 제목 입력 필드
        JLabel titleLabel = new JLabel("Title:");
        titleTextField = new JTextField(20);

        // 내용 입력 영역
        JLabel contentLabel = new JLabel("Content:");
        contentTextArea = new JTextArea(5, 20);
        JScrollPane contentScrollPane = new JScrollPane(contentTextArea);

        // 게시글 등록 버튼
        JButton postButton = new JButton("Post");
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText();
                String content = contentTextArea.getText();
                if (!title.isEmpty() && !content.isEmpty()) {
                    Post post = new Post(title, content);
                    posts.add(post);
                    postListModel.addElement(post.getTitle());
                    titleTextField.setText("");
                    contentTextArea.setText("");
                }
            }
        });

        // 게시글 목록
        postListModel = new DefaultListModel<>();
        postList = new JList<>(postListModel);
        JScrollPane postScrollPane = new JScrollPane(postList);

        // 게시글 선택 이벤트 처리
        postList.addListSelectionListener(e -> {
            int selectedIndex = postList.getSelectedIndex();
            if (selectedIndex != -1) {
                Post selectedPost = posts.get(selectedIndex);
                JOptionPane.showMessageDialog(BulletinBoard.this, selectedPost.getContent(), "Post", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 레이아웃 설정
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(titleLabel);
        inputPanel.add(titleTextField);
        inputPanel.add(contentLabel);
        inputPanel.add(contentScrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(postButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(postScrollPane, BorderLayout.SOUTH);
    }

    private class Post {
        private String title;
        private String content;

        public Post(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BulletinBoard bulletinBoard = new BulletinBoard();
                bulletinBoard.setVisible(true);
            }
        });
    }
}
