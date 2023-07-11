package src.Domain.Domain1.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import src.Domain.Domain1.Dao.MemberDao;
import src.Domain.Domain1.Dao.MemberDaoImpl;
import src.Domain.Domain1.Dto.MemberDto;
import src.Domain.Domain1.Service.Auth.Session;



public class MemberServiceImpl implements MemberService {
		//세션정보저장
		public Map<String,Session> sessionMap;
		
		private MemberDao dao;
		
		private Map<String, List<String>> memberSearchHistoryMap;
	
		//싱글톤
		private static MemberService instance;
		public static MemberService getInstance() {
			if(instance == null)
				instance = new MemberServiceImpl();
			return instance;
		}
		public MemberServiceImpl() {
			dao = MemberDaoImpl.getInstance();
			sessionMap = new HashMap();
			memberSearchHistoryMap = new HashMap<>();
		}
		//회원 가입하기
		@Override
		public boolean memberJoin(MemberDto dto) throws Exception{
			int result = dao.insert(dto);
			if(result>0)
				return true;
			return false;
		}
		//회원 조회하기(전체조회) - 사서
		@Override
		public List<MemberDto> memberSearch(String sid) throws Exception{
			String role = this.getRole(sid);
			if(role.equals("ROLE_MANAGER")) 
				return dao.select();			
			return null;
		}
		//회원 조회하기(한명) - 사서
		@Override
		public MemberDto memberSearchOne(String role, String id) throws Exception{
			if(role.equals("ROLE_MANAGER")) 
				return dao.select(id);			
			return null;
		}
		//회원 조회하기(한 회원) - 로그인한 회원만
		@Override
		public MemberDto memberSearch(String id, String sid) throws Exception{
			Session session = sessionMap.get(sid);
			if(session!=null && session.getId().equals(id))
				return dao.select(id);
			return null;
		}
		//회원 수정하기 - 본인 확인
		@Override
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
		@Override
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
		@Override
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
			
			// 3. 검색 기록 리스트 생성 및 맵에 연결
		    List<String> searchHistory = new ArrayList<>();
		    memberSearchHistoryMap.put(dbDto.getId(), searchHistory);

		    // 4. 세션에 대한 정보를 클라이언트가 접근할 수 있도록 하는 세션 구별 ID(Session Cookie) 전달
		    Map<String, Object> result = new HashMap<>();
		    result.put("sid", sid);
		    result.put("role", dbDto.getRole());
		    result.put("memberId", dbDto.getId());
		    return result;
			
		}
		
		// 검색 기록 추가
		@Override
		public List<String> addSearchHistory(String memberId, String searchText) {
		    List<String> searchHistory = memberSearchHistoryMap.get(memberId);
		    if (searchHistory == null) {
	            searchHistory = new ArrayList<>();
	            memberSearchHistoryMap.put(memberId, searchHistory);
	        }
	        searchHistory.add(searchText);
	        System.out.println("MemberServiceImpl's addSearchHistory : " + searchHistory);
	        
	        return memberSearchHistoryMap.get(memberId);
		}

		// 검색 기록 조회
		@Override
		public List<String> getSearchHistory(String memberId) {
			return memberSearchHistoryMap.get(memberId);
		}
	    
		//로그아웃
		@Override
		public boolean logout(String id,String sid) {
			Session session = sessionMap.get(sid);
			if(! session.getId().equals(id)) {
				System.out.println("[ERROR] ID가 일치하지 않습니다.");
				return false;
			}
			sessionMap.remove(sid);
			return true;
		}
		
		//중복확인
		@Override
		public boolean idcheck(String id) throws Exception {
			MemberDto dbDto = dao.select(id);
			if(dbDto==null) {
				System.out.println("[INFO] 사용가능한 아이디입니다.");
				return true;
			}
			System.out.println("[ERROR] 이미 사용중인 아이디입니다.");
			return false;
		}
		
		//역할반환함수
		@Override
		public String getRole(String sid) {
			Session session = sessionMap.get(sid);
			System.out.println("getRole's Session : " + session);
			if(session != null)
				return session.getRole();
			
			return null;
		}
}