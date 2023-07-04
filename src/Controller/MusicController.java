package src.Controller;


import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import src.Domain.Domain1.Service.MemberService;
import src.Domain.Domain1.Service.MusicService;

public class MusicController {
    private MusicService musicService;
    private MemberService memberService;
    private String memberId;
 
    public MusicController() {
        this.musicService = new MusicService(null);
        this.memberService = MemberService.getInstance();
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
}