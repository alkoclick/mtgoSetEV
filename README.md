# MTGOSetEV

A super-simple EV calculator for newer MTGO sets using https://www.mtgowikiprice.com/ and http://mtgjson.com/

How to use:

* You download the project and build the jar yourself 
OR 
* You download the jar from the jars folder
* You run java -jar MTGOWikiEVPricer.jar MMA MM3 KLD
* In the above example I am getting 3 sets: MMA, MM3 and Kaladesh. You can use any of the recent 3 letter set codes, if mtgowikiprice or mtgjson haven't done anything dumb with them. Separate by single space
* It will tell you the EV for each set

How it works:

* It fetches the json file containing all the info you want for a given set by using mtgjson.com
* It uses those card jsons to build basic info for all the cards of the set
* With that info, it can create an mtgowikiprice link for any such card
* It will visit each link and get the official sell price
* It will apply mythic percentage (0.15) and rare percentage calculations
* It will determine set EV using Mythics and Rares - No foils, unc, commons or weird stuff
