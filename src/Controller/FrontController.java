package src.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class FrontController {
	
	private Map<String,Object> map = new HashMap();
	private MusicController musicController;
	private List<String> searchHistory;

	public FrontController() {
		init();
		musicController = new MusicController();
		searchHistory = new ArrayList<>();
	}
	
	void init() {
		map.put("/member", new MemberController());
		map.put("/music", new MusicController());
		map.put("/qna", new QnAController());
	}
	
	
	public void searchTracks(String searchText) {
        musicController.searchTracks(searchText);
        searchHistory.add(searchText);
    }
	
	public void openWebpage(String url) {
		musicController.openWebpage(url);
		
	}

	public List<String> getSearchHistory() {
		return searchHistory;
	}

    public DefaultTableModel getTableModel() {
        return musicController.getTableModel();
    }
	
	public Map<String,Object> execute(String uri,int ServiceNo , Map<String,Object>param)
	{
		Object controller = map.get(uri);
		Map<String, Object> result = null;
		
		if(controller instanceof MemberController) {
			
			MemberController down = (MemberController)controller;
			result = down.execute(ServiceNo, param);	
			System.out.println("MemberController");
			return result;
		}else if(controller instanceof MusicController) {

			MusicController down = (MusicController)controller;
			result = down.execute(ServiceNo, param);
	        
	         
           
            return result;
			
		}
		
		return param;
		
	}
	
	 public void processRequest(String command, String searchText, String memberId) {
	        if (command.equals("searchTracks")) {
	            musicController.setMemberId(memberId);
	            musicController.searchTracks(searchText);
	        } else if (command.equals("openWebpage")) {
	            musicController.openWebpage(searchText);
	        } else if (command.equals("getUserSearchHistory")) {
	            List<String> searchHistory = musicController.getUserSearchHistory(memberId);
	            // 검색 기록 처리
	        } else if (command.equals("addSearchHistory")) {
	            musicController.addSearchHistory(memberId, searchText);
	        }
	    }

}