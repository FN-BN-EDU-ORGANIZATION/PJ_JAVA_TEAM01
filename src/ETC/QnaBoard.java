package src.ETC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class QnaBoard extends JFrame {
    private DefaultListModel<String> questionListModel;
    private JList<String> questionList;
    private JTextArea answerTextArea;
    private JButton replyButton;

    public QnaBoard() {
        // 윈도우 설정
        setTitle("Q&A Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // 질문 목록
        questionListModel = new DefaultListModel<>();
        questionListModel.addElement("question 1");
        questionListModel.addElement("question 2");
        questionListModel.addElement("question 3");
        questionListModel.addElement("question 4");

        questionList = new JList<>(questionListModel);
        JScrollPane questionScrollPane = new JScrollPane(questionList);

        // 답변 텍스트 영역
        answerTextArea = new JTextArea();
        answerTextArea.setEditable(false);
        JScrollPane answerScrollPane = new JScrollPane(answerTextArea);

        // 답변 버튼
        replyButton = new JButton("답변하기");
        replyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedQuestion = questionList.getSelectedValue();
                if (selectedQuestion != null) {
                    String answer = JOptionPane.showInputDialog(QnaBoard.this, "질문에 대한 답변을 입력하세요:");
                    if (answer != null && !answer.isEmpty()) {
                        answerTextArea.setText("질문: " + selectedQuestion + "\n답변: " + answer);
                    }
                }
            }
        });

        // 레이아웃 설정
        setLayout(new BorderLayout());
        add(questionScrollPane, BorderLayout.WEST);
        add(answerScrollPane, BorderLayout.CENTER);
        add(replyButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                QnaBoard qnaBoard = new QnaBoard();
                qnaBoard.setVisible(true);
            }
        });
    }
}
