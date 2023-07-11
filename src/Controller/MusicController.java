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
			List<MusicDto> list = null;
			list = musicService.searchTracks(searchText, memberId);
			result.put("result", list);

			// 뷰로 전달
			return result;

		} else if (serviceNo == 2) {
			// 파라미터 추출
			String url = (String) param.get("url");

			// 입력값 검증

			// 서비스 실행
			List<MusicDto> list = openWebpage(url);
		    Map<String, Object> result = new HashMap<>();
		    result.put("result", list);
			
			// 뷰로 전달
		    return result;
		} 
//		else if (serviceNo == 3) {
//			// 파라미터 추출
//			// 입력값 검증
//			// 서비스 실행
//			// 뷰로 전달
//			DefaultTableModel tableModel = getTableModel();
//		} 

		return null;
	}

	public void searchTracks(String searchText) {
		musicService.searchTracks(searchText, memberId);
	}

	public List<MusicDto> openWebpage(String url) {
		return musicService.openWebpage(url);
	}
//
//	public DefaultTableModel getTableModel() {
//		return musicService.getTableModel();
//	}

	
}