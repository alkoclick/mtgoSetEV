import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class Card {
	public double price = 0;
	Pattern regex = Pattern.compile("<td>(\\d+\\.\\d+)<\\/td>");
	private String name;
	private String rarity;
	private int number;
	private String set;
	private static final String BASE_URL = "https://www.mtgowikiprice.com/";

	public Card(JSONObject json, String set) 
	{
		name = json.getString("name");
		rarity = json.getString("rarity");
		number = json.getInt("number");
		this.set = set;
	}

	public double getPrice() {
		return price;
	}
	
	public String getLink(){
		return BASE_URL + "card/" + set + "/" + number + "/" + name.replace(" ", "_");
	}

	public Runnable fetchPrice() {
		Runnable fetcher = new Runnable() {

			@Override
			public void run() {
				String result;
				try {
					String web = Network.get(getLink());
					Matcher matcher = regex.matcher(web);
					matcher.find();
					result = matcher.group(1);
					price = Double.valueOf(result);
//					System.out.println(price);
				} catch (IOException e) {
					e.printStackTrace();
				}catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		};

		return fetcher;
	}

	public String getRarity() {
		return rarity;
	}
}
