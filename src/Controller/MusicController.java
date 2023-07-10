package src.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import src.Domain.Domain1.Dto.MusicDto;
import src.Domain.Domain1.Service.MemberService;
import src.Domain.Domain1.Service.MemberServiceImpl;
import src.Domain.Domain1.Service.MusicService;

public class MusicController {
	private MusicService musicService;
	private MemberService memberService;
	private String memberId;

	public MusicController() {
		this.musicService = new MusicService(null);
		this.memberService = MemberServiceImpl.getInstance();
	}

	public Map<String, Object> execute(int serviceNo, Map<String, Object> param) {

		if (serviceNo == 1) {
			// 파라미터 추출
			String searchText = (String) param.get("searchText");
			String memberId = (String) param.get("memberId");

			// 입력값 검증

			// 서비스 실행
			Map<String, Object> result = new HashMap();
			List<MusicDto> list=null;
			list = musicService.searchTracks(searchText, memberId);
			result.put("result", list);
			System.out.println("MusicController's List<MusicDto> : " + list);
			// 뷰로 전달
			return result;
			
		} else if (serviceNo == 2) {

		} else if (serviceNo == 3) {

		} else if (serviceNo == 4) {

		} else if (serviceNo == 5) {

		} else if (serviceNo == 6) {

		}

		return null;
	}

	// 1
	public void searchTracks(String searchText) {
		musicService.searchTracks(searchText, memberId);
	}

	// 2
	public void openWebpage(String url) {
		musicService.openWebpage(url);
	}

	// 3
	public DefaultTableModel getTableModel() {
		return musicService.getTableModel();
	}

	// 4
	public List<String> getUserSearchHistory(String userId) {
		return memberService.getSearchHistory(userId);
	}

	// 5
	public void addSearchHistory(String userId, String searchText) {
		memberService.addSearchHistory(userId, searchText);
	}

	// 6
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}