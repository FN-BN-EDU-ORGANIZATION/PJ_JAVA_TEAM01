package src.ETC;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminBulletinBoard<Post> extends JFrame {
    private List<Post> posts;
    private JTextField titleTextField;
    private JTextArea contentTextArea;
    private DefaultListModel<String> postListModel;
    private JList<String> postList;

    public AdminBulletinBoard() {
        // 게시글 목록 초기화
        posts = new ArrayList<>();

        // 윈도우 설정
        setTitle("Admin Bulletin Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
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
                JOptionPane.showMessageDialog(AdminBulletinBoard.this, selectedPost.getContent(), "Post", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 게시글 수정 버튼
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = postList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Post selectedPost = posts.get(selectedIndex);
                    String newTitle = JOptionPane.showInputDialog(AdminBulletinBoard.this, "Enter new title:", selectedPost.getTitle());
                    String newContent = JOptionPane.showInputDialog(AdminBulletinBoard.this, "Enter new content:", selectedPost.getContent());
                    if (newTitle != null && newContent != null) {
                        selectedPost.setTitle(newTitle);
                        selectedPost.setContent(newContent);
                        postListModel.set(selectedIndex, newTitle);
                    }
                }
            }
        });

        // 게시글 삭제 버튼
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = postList.getSelectedIndex();
                if (selectedIndex != -1) {
                    posts.remove(selectedIndex);
                    postListModel.remove(selectedIndex);
                } ); 
            
