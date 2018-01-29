import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/*
LANDMARK_DETECTION
FACE_DETECTION
LOGO_DETECTION
LALEL_DETECTION
TEXT_DETECTION

"type": "WEB_DETECTION",
          "maxResults": 2

*/

public class Run{
	private static final String TARGET_URL =
            "https://vision.googleapis.com/v1/images:annotate?";
	private static final String API_KEY =
            "key=AIzaSyCRxstIgjv3bTG6bLi1FbcKItcQqu3Xs80";
    public static void main(String[]args) throws IOException{
    	URL serverUrl = new URL(TARGET_URL + API_KEY);
    	URLConnection urlConnection = serverUrl.openConnection();
    	HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
    	httpConnection.setRequestMethod("POST");
    	httpConnection.setRequestProperty("Content-Type", "application/json");
    	httpConnection.setDoOutput(true);
    	BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
        OutputStreamWriter(httpConnection.getOutputStream()));
		httpRequestBodyWriter.write
		("{\"requests\":  [{ \"features\":  [ {\"type\": \"WEB_DETECTION\""
		+"}], \"image\": {\"source\": { \"imageUri\":"
		+" \"http://cdn2-www.dogtime.com/assets/uploads/gallery/golden-retriever-dogs-and-puppies/golden-retriever-dogs-puppies-10.jpg\"}}}]}");
		 httpRequestBodyWriter.close();
		 String response = httpConnection.getResponseMessage();
		 if (httpConnection.getInputStream() == null) {
			   System.out.println("No stream");
			   return;
			}

			Scanner httpResponseScanner = new Scanner (httpConnection.getInputStream());
			String resp = "";
			while (httpResponseScanner.hasNext()) {
			   String line = httpResponseScanner.nextLine();
			   resp += line;
			   System.out.println(line);  //  alternatively, print the line of response
			}
			httpResponseScanner.close();
    }
}
