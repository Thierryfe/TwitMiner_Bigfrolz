package phase0;
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


	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
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
		PrintWriter pw = new PrintWriter(new File("test.csv"));
		
		while (true){
		for (int e = 0; e <180;e++) {
			
			  try {
				    requete = new Query("#Trump");
				    resultat = twitter.search(requete);

				    tweets = (ArrayList) resultat.getTweets();
				    
				
				    
			        StringBuilder sb = new StringBuilder();
			        
			        sb.append("Date");
			        sb.append(",");
			        sb.append("@username");
			        sb.append(",");
			        sb.append("text");
			        sb.append("\n");


				    for (int i = 0; i < tweets.size(); i++) {

				      Status t=(Status) tweets.get(i);
				      User u=(User) t.getUser();
				      user=u.getName();
				      msg = t.getText();
				      msg = msg.replaceAll("\n", ";");
				      msg = msg.replaceAll("\t", ";");
				      msg = msg.replaceAll("\r", ";");
				      msg = msg.replaceAll(" ", ";");
				      pseudo = t.getUser().getScreenName();
				      Date date = t.getCreatedAt();
				      
				      sb.append(date);
				      sb.append(";");
				      sb.append(pseudo);
				      sb.append(";");
				      sb.append(msg);
				      sb.append("\n");


				    }
				       
			        pw.write(sb.toString());
			        System.out.println(e);
			        
				  }
				  
				  catch (TwitterException ex) {
				    System.out.println("Couldn't connect: " + ex.getErrorMessage());
				  }
			
		}
		Thread.sleep(900000);
		}
	
		  
		  
	}
}
