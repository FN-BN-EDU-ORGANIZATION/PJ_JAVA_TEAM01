package src.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import src.Domain.Domain1.Dto.QnADto;
import src.Domain.Domain1.Service.MemberService;
import src.Domain.Domain1.Service.QnAService;
import src.ETC.BulletinBoardGUI;

public class QnAController{
private MemberService service;
private QnAService qnaService;
private BulletinBoardGUI bulletinBoardGUI;

	
	public QnAController(){
	service = MemberService.getInstance();
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

	
	//[              CRUD                 ]
	//[1 Select 2 Insert 3 Update 4 Delete]
	
	
//	public Map<String,Object> execute(int serviceNo, Map<String, Object> param) {
//			
//			if(serviceNo==1) {
//				//1 파라미터 추출(생략)
//				String sid = (String)param.get("sid");
//				String id = (String)param.get("id");
//				//2 입력값 검증(생략)
//				if(sid==null||id==null) {
//					System.out.println("[ERROR]Data Validation Check Error!");
//					return null;
//				}
//				//3 서비스 실행(서비스모듈작업 이후 처리)
//				boolean result = false;
//				try {
//					result = qnaService.memService(sid,id);
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//				
//				//4 View로 전달
//				System.out.println("Member_Service Block!");
//				Map<String,Object> resultMap = new HashMap<>();
//				resultMap.put("result", result);
//				return resultMap;
//			}
//			else if(serviceNo==2) {
//				//1 파라미터 추출
//				String id = (String)param.get("id");
//				Date qnadate = (Date)param.get("qnadate");
//				String contents = (String)param.get("contents");
//				String title = (String)param.get("title");
//				
//				//2 입력값 검증
//				if(id==null||qnadate==null||contents==null||title==null) {
//					System.out.println("[ERROR] Data Validation Check Error !");
//					return null;
//				}
//				//3 서비스 실행
//				QnADto dto = new QnADto(serviceNo, id, qnadate, contents, title);
//				Boolean success = qnaService.postService.createPost(dto);
//				
//				//4 View로 전달
//				System.out.println("QnA_Insert Block!");
//				Map<String,Object> result = new HashMap();
//				result.put("result", success);
//				return result;
//			}
//			else if(serviceNo==3) {
//				//1 파라미터 추출
//				Integer no = (Integer)param.get("no");
//				String id = (String)param.get("id");
//				Date qnadate = (Date)param.get("qnadate");
//				String contents = (String)param.get("contents");
//				String title = (String)param.get("title");
//				
//
//				//2 입력값 검증
//				if(no==null||id==null||qnadate==null||contents==null||title==null) {
//					System.out.println("[ERROR] Data Validation Check Error !");
//					return null;
//				}
//				//3 서비스 실행
//				QnADto dto = new QnADto(no,id,qnadate,contents,title);
//				Boolean success = qnaService.postService.updatePost(no, dto);
//				
//				
//				//4 View로 전달
//				System.out.println("QnA_Update Block!");
//				Map<String,Object> result = new HashMap();
//				result.put("result", success);
//				return result;
//			}
//			else if(serviceNo==4) {
//				//1 파라미터 추출
//				Integer no = (Integer)param.get("no");
//
//				//2 입력값 검증
//				if(no==null) {
//					System.out.println("[ERROR] Data Validation Check Error !");
//					return null;
//				}
//				//3 서비스 실행
//				Boolean success = qnaService.postService.deletePost(no);
//				//4 View로 전달
//				System.out.println("Member_Delete Block!");
//				Map<String,Object> result = new HashMap();
//				result.put("result", result);
//				return result;
//			}
//			return null;
//		}
//	글생성수정삭제
}