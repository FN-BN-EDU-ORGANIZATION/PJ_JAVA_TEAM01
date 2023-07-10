package src.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JOptionPane;

import src.Domain.Domain1.Dto.QnADto;
import src.Domain.Domain1.Service.MemberService;
import src.Domain.Domain1.Service.MemberServiceImpl;
import src.Domain.Domain1.Service.QnAService;
import src.ETC.BulletinBoardGUI;

public class QnAController {
private MemberService service;
private QnAService qnaService;
private BulletinBoardGUI bulletinBoardGUI;

	
	public QnAController(){
	service = MemberServiceImpl.getInstance();
	qnaService = QnAService.getInstance();
	qnaService.setMemberService(service);
	
	bulletinBoardGUI = new BulletinBoardGUI();
	bulletinBoardGUI.setPostService(qnaService.postService);
    bulletinBoardGUI.addActionListener(new BulletinBoardGUIActionListener());
	}
	
	// BulletinBoardGUI의 ActionListener 구현 클래스
	class BulletinBoardGUIActionListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == bulletinBoardGUI.getAddButton()) {
	            // '생성' 버튼 클릭 시 동작 구현
	            String title = bulletinBoardGUI.getTitleField().getText();
	            String content = bulletinBoardGUI.getContentField().getText();

	         
	            
	            // QnAService를 통해 게시물 생성 요청
	            QnADto dto = new QnADto();
	            dto.setTitle(title);
	            dto.setContents(content);
	            boolean success = qnaService.postService.createPost(dto);

	            if (success) {
	                // 생성 성공 시 메시지 출력 및 필드 초기화
	                JOptionPane.showMessageDialog(bulletinBoardGUI, "게시물이 생성되었습니다.");
	                bulletinBoardGUI.getTitleField().setText("");
	                bulletinBoardGUI.getContentField().setText("");
	            } else {
	                // 생성 실패 시 에러 메시지 출력
	                JOptionPane.showMessageDialog(bulletinBoardGUI, "게시물 생성에 실패했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	            
	        } else if (e.getSource() == bulletinBoardGUI.getEditButton()) {
	            // '수정' 버튼 클릭 시 동작 구현
	            int selectedIndex = bulletinBoardGUI.getPostList().getSelectedIndex();
	            if (selectedIndex != -1) {
	                // 선택한 게시물이 있는 경우
	                String newTitle = bulletinBoardGUI.getTitleField().getText();
	                String newContent = bulletinBoardGUI.getContentField().getText();

	                // QnAService를 통해 게시물 수정 요청
	                QnADto selectedPost = bulletinBoardGUI.getPostList().getSelectedValue();
	                selectedPost.setTitle(newTitle);
	                selectedPost.setContents(newContent);
	                boolean success = qnaService.postService.updatePost(selectedPost.getNo(), selectedPost);

	                if (success) {
	                    // 수정 성공 시 메시지 출력 및 필드 초기화
	                    JOptionPane.showMessageDialog(bulletinBoardGUI, "게시물이 수정되었습니다.");
	                    bulletinBoardGUI.getTitleField().setText("");
	                    bulletinBoardGUI.getContentField().setText("");
	                } else {
	                    // 수정 실패 시 에러 메시지 출력
	                    JOptionPane.showMessageDialog(bulletinBoardGUI, "게시물 수정에 실패했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                // 선택한 게시물이 없는 경우 에러 메시지 출력
	                JOptionPane.showMessageDialog(bulletinBoardGUI, "수정할 게시물을 선택해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else if (e.getSource() == bulletinBoardGUI.getDeleteButton()) {
	            // '삭제' 버튼 클릭 시 동작 구현
	            int selectedIndex = bulletinBoardGUI.getPostList().getSelectedIndex();
	            if (selectedIndex != -1) {
	                // 선택한 게시물이 있는 경우
	                QnADto selectedPost = bulletinBoardGUI.getPostList().getSelectedValue();
	                boolean success = qnaService.postService.deletePost(selectedPost.getNo());

	                if (success) {
	                    // 삭제 성공 시 메시지 출력 및 필드 초기화
	                    JOptionPane.showMessageDialog(bulletinBoardGUI, "게시물이 삭제되었습니다.");
	                    bulletinBoardGUI.getTitleField().setText("");
	                    bulletinBoardGUI.getContentField().setText("");
	                } else {
	                    // 삭제 실패 시 에러 메시지 출력
	                    JOptionPane.showMessageDialog(bulletinBoardGUI, "게시물 삭제에 실패했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else {
	                // 선택한 게시물이 없는 경우 에러 메시지 출력
	                JOptionPane.showMessageDialog(bulletinBoardGUI, "삭제할 게시물을 선택해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	}

	@Override
	public Map<String, Object> execute(int serviceNo, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}
}