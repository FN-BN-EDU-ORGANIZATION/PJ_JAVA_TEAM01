package src.Controller;

import java.util.HashMap;
import java.util.Map;
<<<<<<< HEAD
=======

public class FrontController {
>>>>>>> branch 'ycy' of https://github.com/FN-BN-EDU-ORGANIZATION/PJ_JAVA_TEAM01.git

<<<<<<< HEAD
import src.Controller.MusicController;

public class FrontController {
	
	private Map<String,Object> map = new HashMap();
	private MusicController musicController;
		
	void init() {
		map.put("/member", new MemberController());
		map.put("/music", new MusicController());
		map.put("/qna", new QnAController());
		
	}
	
	public FrontController() {
		init();
	}
	
	public Map<String,Object> execute(String uri,int ServiceNo , Map<String,Object>param)
	{
		Object controller = map.get(uri);
		Map<String, Object> result = new HashMap();
		
		if(controller instanceof MemberController) {
			
			MemberController down = (MemberController)controller;
			result = down.execute(ServiceNo, param);	
			System.out.println("MemberController");
		}else if(controller instanceof MusicController) {
			
			MusicController down = (MusicController)controller;
			down.searchTracks("검색어");
		}
=======
	private Map<String,Object> map = new HashMap();
	
	
	void init() {
		map.put("/member", new MemberController());
		map.put("/music", new MusicController());
		map.put("/qna", new QnAController());
		
	}
	
	Map<String,Object> execute(String uri,int ServiceNo , Map<String,Object>param)
	{
		
>>>>>>> branch 'ycy' of https://github.com/FN-BN-EDU-ORGANIZATION/PJ_JAVA_TEAM01.git
		
		
		return param;
		
	}
}
