package src.Domain.Domain1.Service;

import src.View.BulletinBoardGUI;

public interface QnAService {

	void setMemberService(MemberService memService);

	void setQnAUI(BulletinBoardGUI qnaUI);

	boolean memService(String sid, String id) throws Exception;

}