package src.Controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class FrontController {
	
	private Map<String,SubController> map = new HashMap();
	private MusicController musicController;
	private List<String> searchHistory;
	
	void init() {
		map.put("/member", new MemberController());
		map.put("/music", new MusicController());
//		map.put("/qna", new QnAController());
		musicController = new MusicController();
	}
	
	public FrontController() {
		init();
		musicController = new MusicController();
		searchHistory = new ArrayList<>();
	}
	
	public void searchTracks(String searchText) {
        musicController.searchTracks(searchText);
        searchHistory.add(searchText);
    }

	public DefaultTableModel getTableModel() {
        return musicController.getTableModel();
    }

	
//	public Map<String,Object> execute(String uri,int ServiceNo , Map<String,Object>param,String memberId)
//	{
//		Object controller = map.get(uri);
//		Map<String, Object> result = new HashMap();
////		result = controller.execute(ServiceNo, param);
////		  return result;
//		
//		if(controller instanceof MemberController) {
//			
//			SubController down = (SubController)controller;
//			result = down.execute(ServiceNo, param);	
//			System.out.println("MemberController");
//			return result;
//		}else if(controller instanceof MusicController) {
//			MusicController down = (MusicController)controller;
//			// MusicController의 기능 활용
//            String searchText = (String) param.get("searchText");
//            down.setMemberId(memberId); // memberId 설정
//            down.searchTracks(searchText);
//            DefaultTableModel tableModel = down.getTableModel();
//            // 테이블 모델을 이용하여 필요한 작업 수행
//
//            // 검색 기록 추가
//            searchHistory.add(searchText);
//            
//            System.out.println("MusicController");
//            
//            
//			
//		}
		
		
//		return param;
//		
//	}
	public Map<String,Object> execute(String request,int ServiceNo ,Map<String,Object> param) {
		SubController controller =map.get(request);
		
		Map<String,Object> result = new HashMap();
		result = controller.execute(ServiceNo, param);
		return result;
		
	}

	public void openWebpage(String url) {
		musicController.openWebpage(url);
		
	}

	public List<String> getSearchHistory() {
		
		return searchHistory;
	}
	
}
