package src.Controller;

import java.util.HashMap;
import java.util.Map;

public class FrontController {

	private Map<String,Object> map = new HashMap();
	
	
	void init() {
		map.put("/member", new MemberController());
		map.put("/music", new MusicController());
		map.put("/qna", new QnAController());
		
	}
	
	Map<String,Object> execute(String uri,int ServiceNo , Map<String,Object>param)
	{
		
		
		
		return param;
		
	}
}
