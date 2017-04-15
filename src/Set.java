import java.util.ArrayList;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Set {

	private HashSet<Card> mythic;
	private HashSet<Card> rare;
	private HashSet<Card> uncommon;
	private HashSet<Card> common;

	public Set(Collection<Card> cards) {
		mythic = new HashSet<>();
		rare = new HashSet<>();
		uncommon = new HashSet<>();
		common = new HashSet<>();

		mythic.addAll(
				cards.stream().filter(card -> card.getRarity().equals("Mythic Rare")).collect(Collectors.toSet()));
		rare.addAll(
				cards.stream().filter(card -> card.getRarity().equals("Rare")).collect(Collectors.toSet()));
		uncommon.addAll(
				cards.stream().filter(card -> card.getRarity().equals("Uncommon")).collect(Collectors.toSet()));
		common.addAll(
				cards.stream().filter(card -> card.getRarity().equals("Common")).collect(Collectors.toSet()));
	}

	public double calcEV() {
		DoubleSummaryStatistics mythicEV = mythic.stream()
				.collect(Collectors.summarizingDouble(card -> card.getPrice()));

		DoubleSummaryStatistics rareEV = rare.stream().collect(Collectors.summarizingDouble(card -> card.getPrice()));

		DoubleSummaryStatistics uncommonEV = uncommon.stream()
				.collect(Collectors.summarizingDouble(card -> card.getPrice()));

		DoubleSummaryStatistics commonEV = common.stream()
				.collect(Collectors.summarizingDouble(card -> card.getPrice()));

		return mythicEV.getAverage() * 0.15 + rareEV.getAverage() * 0.85 + uncommonEV.getAverage() * 4
				+ commonEV.getAverage() * 10;
	}

	public HashSet<Card> getMythic() {
		return mythic;
	}

	public void setMythic(HashSet<Card> mythic) {
		this.mythic = mythic;
	}

	public HashSet<Card> getRare() {
		return rare;
	}

	public void setRare(HashSet<Card> rare) {
		this.rare = rare;
	}

	public HashSet<Card> getUncommon() {
		return uncommon;
	}

	public void setUncommon(HashSet<Card> uncommon) {
		this.uncommon = uncommon;
	}

	public HashSet<Card> getCommon() {
		return common;
	}

	public void setCommon(HashSet<Card> common) {
		this.common = common;
	}

}
