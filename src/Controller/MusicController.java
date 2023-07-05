package src.Controller;


import javax.swing.table.DefaultTableModel;

import src.Domain.Domain1.Service.MusicService;

public class MusicController {
    private MusicService musicService;

    public MusicController() {
        this.musicService = new MusicService(null);
    }

    public void searchTracks(String searchText) {
        
        musicService.searchTracks(searchText);
    }

    public void openWebpage(String url) {
        musicService.openWebpage(url);
    }

	public DefaultTableModel getTableModel() {
		// TODO Auto-generated method stub
		return musicService.getTableModel();
	}

}
