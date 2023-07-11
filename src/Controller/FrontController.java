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

	public List<String> getSearchHistory() {
		return searchHistory;
	}


	public Map<String,Object> execute(String uri,int ServiceNo , Map<String,Object>param)
	{
		SubController controller = (SubController) map.get(uri);
		Map<String, Object> result = new HashMap();
		
		result = controller.execute(ServiceNo, param);	
		
		return result;
	}
	//검색기능추가
	public void addSearchHistory(String searchText) {
	    searchHistory.add(searchText);
	}




}