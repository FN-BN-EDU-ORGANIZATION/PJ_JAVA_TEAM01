package src.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import src.Domain.Domain1.Service.MemberService;
import src.Domain.Domain1.Service.MemberServiceImpl;
import src.Domain.Domain1.Service.MusicService;
import src.Domain.Domain1.Service.MusicServiceImpl;

public class MusicController implements SubController{
    private MusicService musicService;
    private MemberService memberService;
    private String memberId;
 
    public MusicController() {
        this.musicService = new MusicServiceImpl(null);
        this.memberService = MemberServiceImpl.getInstance();
    }

    public void searchTracks(String searchText) {
        musicService.searchTracks(searchText, memberId);
    }

    public void openWebpage(String url) {
        musicService.openWebpage(url);
    }

    public DefaultTableModel getTableModel() {
        return musicService.getTableModel();
    }

    public List<String> getUserSearchHistory(String userId) {
        return memberService.getSearchHistory(userId);
    }

    public void addSearchHistory(String userId, String searchText) {
        memberService.addSearchHistory(userId, searchText);
    }
    
    public void setMemberId(String memberId) {
    	this.memberId = memberId;
    }

	
	public Map<String, Object> execute(int serviceNo, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}
}
