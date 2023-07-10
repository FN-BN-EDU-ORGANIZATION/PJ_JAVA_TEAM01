package src.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import src.Domain.Domain1.Dto.MemberDto;
import src.Domain.Domain1.Service.MemberService;
import src.Domain.Domain1.Service.MemberServiceImpl;

public class MemberController implements SubController {
	private MemberService service;
	
	public MemberController(){
	service = MemberServiceImpl.getInstance();
	}
	
		//[              CRUD                 ]
		//[1 Select 2 Insert 3 Update 4 Delete]  5 로그인  6 로그아웃  7 중복확인  
	@Override
	public Map<String,Object> execute(int serviceNo, Map<String, Object> param) {
			
			if(serviceNo==1) {
				//1 파라미터 추출(생략)
				String sid = (String)param.get("sid");
				//2 입력값 검증(생략)
				
				//3 서비스 실행(서비스모듈작업 이후 처리)
				List<MemberDto> list = null;
				try {
					list = service.memberSearch(sid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//4 View로 전달
				System.out.println("Member_Select Block!");
				Map<String,Object> result = new HashMap();
				result.put("result", list);
				return result;
			}
			else if(serviceNo==2) {
				//1 파라미터 추출
				String id = (String)param.get("id");
				String pw = (String)param.get("pw");
				String name = (String)param.get("name");
				String addr = (String)param.get("addr");
				String phone = (String)param.get("phone");
				String role = (String)param.get("role");
				//2 입력값 검증
				if(id==null||pw==null||name==null||addr==null||phone==null) {
					System.out.println("[ERROR] Data Validation Check Error !");
					return null;
				}
				//3 서비스 실행
				MemberDto dto = new MemberDto(id,pw,name,addr,phone,role);
					System.out.println("Dto : " + dto);
				Boolean rValue = false;
				try {
					rValue = service.memberJoin(dto);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//4 View로 전달
				System.out.println("Member_Insert Block!");
				Map<String,Object> result = new HashMap();
				result.put("result", rValue);
				return result;
			}
			else if(serviceNo==3) {
				//1 파라미터 추출
				String id = (String)param.get("id");
				String pw = (String)param.get("pw");
				String name = (String)param.get("name");
				String addr = (String)param.get("addr");
				String phone = (String)param.get("phone");
				String role = (String)param.get("role");
				String sid = (String)param.get("sid");

				//2 입력값 검증
				if(id==null||pw==null||name==null||role==null) {
					System.out.println("[ERROR] Data Validation Check Error !");
					return null;
				}
				//3 서비스 실행
				MemberDto dto = new MemberDto(id,pw,name,addr,phone,role);
				System.out.println("Dto : " + dto);
				Boolean rValue = false;
				try {
					rValue = service.memberUpdate(dto, sid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//4 View로 전달
				System.out.println("Member_Update Block!");
				Map<String,Object> result = new HashMap();
				result.put("result", rValue);
				return result;
			}
			else if(serviceNo==4) {
				//1 파라미터 추출
				String id = (String)param.get("id");
				String pw = (String)param.get("pw");
				String sid = (String)param.get("sid");

				//2 입력값 검증
				if(id==null||pw==null) {
					System.out.println("[ERROR] Data Validation Check Error !");
					return null;
				}
				//3 서비스 실행
				Boolean rValue = false;
				try {
					rValue = service.memberDelete(id, sid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//4 View로 전달
				System.out.println("Member_Delete Block!");
				Map<String,Object> result = new HashMap();
				result.put("result", rValue);
				return result;
				
			}else if(serviceNo==5) {
				//1 파라미터 추출
				String id = (String)param.get("id");
				String pw = (String)param.get("pw");
				//2 입력값 검증
				if(id==null||pw==null) {
					System.out.println("[ERROR] Data Validation Check Error !");
					return null;
				}
				//3 서비스 실행
//				MemberDto dto = new MemberDto(id,pw,null,null,null,null);
				Map<String,Object> result = new HashMap();
				try {
					result = service.login(id, pw);
				} catch (Exception e) {	
					e.printStackTrace();
				}
				
				//4 View로 전달
				return result;
			}else if(serviceNo==6) {
				//1 파라미터 추출
				//2 입력값 검증
				//3 서비스 실행
				//4 View로 전달			
				
			}else if(serviceNo==7) { //id 중복확인
				//1 파라미터 추출
				String id = (String)param.get("id");	//3 서비스 실행
				Boolean rValue = false;
				
				//2 입력값 검증
				if(id==null) {
					System.out.println("[ERROR] Data Validation Check Error !");
					return null;
				}
			
				try {
					rValue = service.idcheck(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//4 View로 전달	
				System.out.println("id_Check Block!");
				Map<String,Object> result = new HashMap();
				result.put("result", rValue);
				return result;
				
			}else if (serviceNo == 8) {
	            //1 파라미터 추출
	            String memberId = (String) param.get("memberId");
	            String searchText = (String) param.get("searchText");

	            //2 입력값 검증
	            if (memberId == null || searchText == null) {
	                System.out.println("[ERROR] Data Validation Check Error !");
	                return null;
	            }

	            //3 서비스 실행
	            service.addSearchHistory(memberId, searchText);

	            //4 View로 전달
	            System.out.println("Add Search History Block!");
	            Map<String, Object> result = new HashMap<>();
	            result.put("result", "Search history added successfully");
	            return result;
	        }
			
			
			return null;
		}
}
