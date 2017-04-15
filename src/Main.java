import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class Main {

	private static final int THREAD_COUNT = 10;

	public static void main(String[] args) {

		for (String arg : args) {
			calculateAndPrintValue(arg);
		}
	}

	private static void calculateAndPrintValue(String setName) {
		try {
			System.out.println("Fetching cardlist for " + setName);
			JSONObject jsonObject = new JSONObject(Network.get("https://mtgjson.com/json/" + setName + ".json"));
			ArrayList<Card> cards = new ArrayList<>();

			for (Object json : jsonObject.getJSONArray("cards")) {
				cards.add(new Card((JSONObject) json, setName));
			}

			Set current = new Set(cards);

			System.out.println("Cardlist created, " + current.getMythic().size() + " mythics, "
					+ current.getRare().size() + " rares");

			ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
			current.getMythic().forEach(card -> executorService.execute(card.fetchPrice()));
			current.getRare().forEach(card -> executorService.execute(card.fetchPrice()));

			try {
				executorService.shutdown();
				executorService.awaitTermination(120, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(setName + " EV: " + current.calcEV());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
