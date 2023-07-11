package src.Domain.Domain1.Service;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import src.Domain.Domain1.Dto.MusicDto;

public class MusicService {
	
	private DefaultTableModel model;
	private MemberService memberService;

    public DefaultTableModel getTableModel() {
        return model;
    }
    
    public MusicService(DefaultTableModel model) {
        this.model = model;
    }

    public MusicService(DefaultTableModel model, MemberService memberService) {
        this.model = model;
        this.memberService = memberService;
    }

    public List<MusicDto> searchTracks(String searchText, String memberId) {
    	List<MusicDto> list = new ArrayList();
        try {
            String apiKey = "354ad741231e3c7ae853e84460461072";
            String encodedTrack = URLEncoder.encode(searchText, "UTF-8");

            String apiUrl = "http://ws.audioscrobbler.com/2.0/?method=track.search&track=" + encodedTrack
                    + "&limit=300&api_key=" + apiKey + "&format=json";
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode trackMatches = root.path("results").path("trackmatches").path("track");


            for (JsonNode trackNode : trackMatches) {
                String name = trackNode.path("name").asText();
                String artist = trackNode.path("artist").asText();
                String url = trackNode.path("url").asText();
                list.add(new MusicDto(name,artist,url));
            }
            

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        //리턴하기
     
        return list;
    }

    public List<MusicDto> openWebpage(String url) {
    	List<MusicDto> list = new ArrayList();
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
        list.add(new MusicDto(url));
        return list;
    }
}