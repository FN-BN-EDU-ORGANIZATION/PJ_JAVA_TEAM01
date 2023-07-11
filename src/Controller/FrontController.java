package src.Controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class FrontController {
	
	private Map<String,SubController> map = new HashMap();

	public FrontController() {
		init();
	}
	
	void init() {
		map.put("/member", new MemberController());
		map.put("/music", new MusicController());
		map.put("/qna", new QnAController());
	}


	
	public Map<String,Object> execute(String request,int ServiceNo ,Map<String,Object> param) {
		SubController controller = map.get(request);
		
		Map<String,Object> result = new HashMap();
		result = controller.execute(ServiceNo, param);
		return result;
		
	}
	

}