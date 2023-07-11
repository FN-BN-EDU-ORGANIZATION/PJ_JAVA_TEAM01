package src.Domain.Domain1.Dto;

public class MusicDto {
	private String name;
	private String artist;
	private String url;
	
	
	@Override
	public String toString() {
		return "MusicDto [name=" + name + ", artist=" + artist + ", url=" + url + "]";
	}
	public MusicDto(String name, String artist, String url) {
		super();
		this.name = name;
		this.artist = artist;
		this.url = url;
	}
	
	public MusicDto(String url) {
		super();
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
