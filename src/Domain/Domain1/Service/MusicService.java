package src.Domain.Domain1.Service;

import javax.swing.table.DefaultTableModel;

public interface MusicService {

	DefaultTableModel getTableModel();

	void searchTracks(String searchText, String memberId);

	void openWebpage(String url);

}