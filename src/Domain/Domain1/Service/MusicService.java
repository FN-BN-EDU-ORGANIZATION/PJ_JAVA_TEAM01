package src.Domain.Domain1.Service;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MusicService {
	
	private DefaultTableModel model;

    public DefaultTableModel getTableModel() {
        return model;
    }

    public MusicService(DefaultTableModel model) {
        this.model = model;
    }

    public void searchTracks(String searchText) {
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

            model = new DefaultTableModel(1, 0);

            model.setColumnCount(0);
            model.setRowCount(0);

            model.addColumn("TITLE");
            model.addColumn("ARTIST");
            model.addColumn("URL");

            for (JsonNode trackNode : trackMatches) {
                String name = trackNode.path("name").asText();
                String artist = trackNode.path("artist").asText();
                String url = trackNode.path("url").asText();

                Object[] rowData = { name, artist, url };
                model.addRow(rowData);

            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void openWebpage(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}