import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;



/**
 * Instagram does not allow users to save pictures in a normal way, doing so requires
 * digging through the HTML source code. This is automated with the following script
 *
 */
public class instaImageGetter {
	
	public void saveImage(String extension, String path, String name) {
		
		String url = "https://www.instagram.com/p/" + extension;
		String pathToSave = path + name + ".jpg";
		
		try {
			BufferedReader br = 
					new BufferedReader(
					new InputStreamReader(
					new URL(url).openStream()));
			
			String line;
			while(!(line = br.readLine()).startsWith("    <meta property=\"og:image\" content="));
			
			url = line.substring(
					line.indexOf("\"og:image\" content=")+"\"og:image\" content=".length()+1,
					line.indexOf("\" />"));
			
			InputStream in = new URL(url).openStream();
			Files.copy(in, Paths.get(pathToSave));
			br.close();
			in.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
