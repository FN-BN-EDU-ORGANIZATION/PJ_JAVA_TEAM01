package src.Domain.Domain1.Service;

import java.util.List;
import java.util.Map;

import src.Domain.Domain1.Dto.MemberDto;

public interface MemberService {

	//회원 가입하기
	boolean memberJoin(MemberDto dto) throws Exception;

	//회원 조회하기(전체조회) - 사서
	List<MemberDto> memberSearch(String sid) throws Exception;

	//회원 조회하기(한명) - 사서
	MemberDto memberSearchOne(String role, String id) throws Exception;

	//회원 조회하기(한 회원) - 로그인한 회원만
	MemberDto memberSearch(String id, String sid) throws Exception;

	//회원 수정하기 - 본인 확인
	boolean memberUpdate(MemberDto dto, String sid) throws Exception;

	//회원 삭제하기
	boolean memberDelete(String id, String sid) throws Exception;

	//로그인
	Map<String, Object> login(String id, String pw) throws Exception;

	// 검색 기록 추가
	void addSearchHistory(String memberId, String searchText);

	// 검색 기록 조회
	List<String> getSearchHistory(String memberId);

	//로그아웃
	boolean logout(String id, String sid);

	//중복확인
	boolean idcheck(String id) throws Exception;

	//역할반환함수
	String getRole(String sid);

}