package src.Domain.Domain1.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import src.Domain.Domain1.Dao.MemberDao;
import src.Domain.Domain1.Dto.MemberDto;
import src.Domain.Domain1.Service.Auth.Session;


public class MemberService {
	//세션정보저장
		public Map<String,Session> sessionMap;
		
		private MemberDao dao;
		
		//싱글톤
		private static MemberService instance;
		public static MemberService getInstance() {
			if(instance == null)
				instance = new MemberService();
			return instance;
		}
		private MemberService() {
			dao = MemberDao.getInstance();
			sessionMap = new HashMap();
		}
		//회원 가입하기
		public boolean memberJoin(MemberDto dto) throws Exception{
			int result = dao.insert(dto);
			if(result>0)
				return true;
			return false;
		}
		//회원 조회하기(전체조회) - 관리자
		public List<MemberDto> memberSearch(String sid) throws Exception{
			String role = this.getRole(sid);
			if(role.equals("ROLE_MANAGER")) 
				return dao.select();			
			return null;
		}
		//회원 조회하기(한명) - 관리자
		public MemberDto memberSearchOne(String role, String id) throws Exception{
			if(role.equals("ROLE_MANAGER")) 
				return dao.select(id);			
			return null;
		}
		//회원 조회하기(한 회원) - 로그인한 회원만
		public MemberDto memberSearch(String id, String sid) throws Exception{
			Session session = sessionMap.get(sid);
			if(session!=null && session.getId().equals(id))
				return dao.select(id);
			return null;
		}
		//회원 수정하기 - 본인 확인
		public boolean memberUpdate(MemberDto dto, String sid) throws Exception{
			Session session = sessionMap.get(sid);
			if(session!=null && session.getId().equals(dto.getId())) 
			{
				int result = dao.update(dto);
				if(result>0)
					return true;			
			}
			return false;
		}
		//회원 삭제하기
		public boolean memberDelete(String id, String sid) throws Exception{
			
			Session session = sessionMap.get(sid);
			if(session!=null && session.getId().equals(id))
			{
				int result = dao.delete(id);
				if(result>0)
					return true;			
			}
			return false;
		}
		//로그인
		public Map<String,Object> login(String id, String pw) throws Exception{
			//1 ID/PW 체크 -> Dao 전달받은 id와 일치하는 정보를 가져와서 pw일치 확인
			MemberDto dbDto = dao.select(id);
			if(dbDto==null) {
				System.out.println("[ERROR] Login Fail.. 아이디가 일치하지 않습니다");
				return null;
			}
			if(!pw.equals(dbDto.getPw())) {
				System.out.println("[ERROR] Login Fail.. 패스워드가 일치하지 않습니다");
				return null;
			}
			//2 사용자에 대한 정보(Session)를 MemberService에 저장
			String sid = UUID.randomUUID().toString();
			Session session = new Session(sid,dbDto.getId(),dbDto.getRole());
			sessionMap.put(sid, session);
			//3 세션에 대한 정보를 클라이언트가 접근할수 있도록 하는 세션 구별ID(Session Cookie) 전달
			Map<String,Object> result = new HashMap();
			result.put("sid", sid);
			result.put("role", dbDto.getRole());
			return result;
		}
		
		//로그아웃
		public boolean logout(String id,String sid) {
			Session session = sessionMap.get(sid);
			if(! session.getId().equals(id)) {
				System.out.println("[ERROR] ID가 일치하지 않습니다.");
				return false;
			}
			sessionMap.remove(sid);
			return true;
		}
		
		//역할반환함수
		public String getRole(String sid) {
			Session session = sessionMap.get(sid);
			System.out.println("getRole's Session : " + session);
			if(session != null)
				return session.getRole();
			
			return null;
		}
}
