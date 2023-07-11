package src.Controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import src.Domain.Domain1.Dto.QnADto;
import src.Domain.Domain1.Service.MemberService;
import src.Domain.Domain1.Service.MemberServiceImpl;
import src.Domain.Domain1.Service.QnAServiceImpl;
import src.View.BulletinBoardGUI;

public class QnAController implements SubController {
	private MemberService service;
	private QnAServiceImpl qnaService;
	private BulletinBoardGUI bulletinBoardGUI;

	
	public QnAController(){
	service = MemberServiceImpl.getInstance();
	qnaService = QnAServiceImpl.getInstance();
	qnaService.setMemberService(service);
	}
	
	
	//[              CRUD                 ]
	//[1 Select 2 Insert 3 Update 4 Delete]
	
	
	public Map<String,Object> execute(int serviceNo, Map<String, Object> param) {
			
			if(serviceNo==1) {
				//1 파라미터 추출(생략)
				String sid = (String)param.get("sid");
				String id = (String)param.get("id");
				//2 입력값 검증(생략)
				if(sid==null||id==null) {
					System.out.println("[ERROR]Data Validation Check Error!");
					return null;
				}
				//3 서비스 실행(서비스모듈작업 이후 처리)
				boolean result = false;
				try {
					result = qnaService.memService(sid,id);
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				//4 View로 전달
				System.out.println("Member_Service Block!");
				Map<String,Object> resultMap = new HashMap<>();
				resultMap.put("result", result);
				return resultMap;
			}
			else if(serviceNo==2) {
				//1 파라미터 추출
				String id = (String)param.get("id");
				Date qnadate = (Date)param.get("qnadate");
				String contents = (String)param.get("contents");
				String title = (String)param.get("title");
				
				//2 입력값 검증
				if(id==null||qnadate==null||contents==null||title==null) {
					System.out.println("[ERROR] Data Validation Check Error !");
					return null;
				}
				//3 서비스 실행
				QnADto dto = new QnADto(serviceNo, id, qnadate, contents, title);
				Boolean success = qnaService.postService.createPost(dto);
				
				//4 View로 전달
				System.out.println("QnA_Insert Block!");
				Map<String,Object> result = new HashMap();
				result.put("result", success);
				return result;
			}
			else if(serviceNo==3) {
				//1 파라미터 추출
				Integer no = (Integer)param.get("no");
				String id = (String)param.get("id");
				Date qnadate = (Date)param.get("qnadate");
				String contents = (String)param.get("contents");
				String title = (String)param.get("title");
				

				//2 입력값 검증
				if(no==null||id==null||qnadate==null||contents==null||title==null) {
					System.out.println("[ERROR] Data Validation Check Error !");
					return null;
				}
				//3 서비스 실행
				QnADto dto = new QnADto(no,id,qnadate,contents,title);
				Boolean success = qnaService.postService.updatePost(no, dto);
				
				
				//4 View로 전달
				System.out.println("QnA_Update Block!");
				Map<String,Object> result = new HashMap();
				result.put("result", success);
				return result;
			}
			else if(serviceNo==4) {
				//1 파라미터 추출
				Integer no = (Integer)param.get("no");

				//2 입력값 검증
				if(no==null) {
					System.out.println("[ERROR] Data Validation Check Error !");
					return null;
				}
				//3 서비스 실행
				Boolean success = qnaService.postService.deletePost(no);
				//4 View로 전달
				System.out.println("Member_Delete Block!");
				Map<String,Object> result = new HashMap();
				result.put("result", result);
				return result;
			}
			return null;
		}
//	글생성수정삭제
}