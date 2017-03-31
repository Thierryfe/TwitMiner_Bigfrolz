import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;


public class twitCrawler {
	private static final String FILE_HEADER = "id,firstName,lastName,gender,age";
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";


	public static void main(String[] args) throws FileNotFoundException {
		TwitterFactory twitterFactory;
		Twitter twitter;
		RequestToken requestToken;
		
		Query requete;
		QueryResult resultat;
		
		ArrayList tweets;
		String msg, user, pseudo;

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("v3Cxy44X499RfxsFhq6u653Xd");
		cb.setOAuthConsumerSecret("i5YNlwmsBG1y1Ek8qArzJ1KVGUK0v24A338K8aQ8Oe6psdMZXP");
		cb.setOAuthAccessToken("847791630681858049-fgXzqVz2I0rqa54tNrjnuTQNcCE9rv3");
		cb.setOAuthAccessTokenSecret("smKdHCBXvjzb6KiZHEWKkMTe3AsO50eX5mIPsgjTaBI2S");

		twitterFactory = new TwitterFactory(cb.build());
		twitter = twitterFactory.getInstance(); 
		
		  try {
			    // Requête de recherche, ici le hashtag prestige
			    requete = new Query("#brice");
			    //Récupération des résultats de requête
			    resultat = twitter.search(requete);

			    tweets = (ArrayList) resultat.getTweets();
			    
			
			    PrintWriter pw = new PrintWriter(new File("test.csv"));
		        StringBuilder sb = new StringBuilder();
		        
		        sb.append("Date");
		        sb.append(',');
		        sb.append("@username");
		        sb.append(',');
		        sb.append("text");
		        sb.append('\n');


			    for (int i = 0; i < tweets.size(); i++) {
			      // ...obtenir le statut du tweet
			      Status t=(Status) tweets.get(i);
			      // ...obtenir l'utilisateur du tweet
			      User u=(User) t.getUser();
			      // ...obtenir le nom de l'utilisateur du tweet
			      user=u.getName();
			     // ...obtenir le texte du message du tweet
			      msg = t.getText();
			      //obtenir le pseudo de l'utilisateur du tweet
			      pseudo = t.getUser().getScreenName();
			      // ...obtenir la date du message du tweet
			      Date date = t.getCreatedAt();
			      sb.append(date);
			      sb.append(',');
			      sb.append(pseudo);
			      sb.append(',');
			      sb.append(t.getText());
			      sb.append('\n');

			      System.out.println(date);
			      

			    }
			       
		        pw.write(sb.toString());
		        pw.close();
			  }
			  // si l'essai ne fonctionne pas, on imprime dans la console "Couldn't connect:"
			  catch (TwitterException e) {
			    System.out.println("Couldn't connect: " + e);
			  }
		  
		  
	}
}
