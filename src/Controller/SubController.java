package src.Controller;

import java.util.Map;

public interface SubController {

	//[              CRUD                 ]
	//[1 Select 2 Insert 3 Update 4 Delete]  5 로그인  6 로그아웃  7 중복확인  
	Map<String, Object> execute(int serviceNo, Map<String, Object> param);

}