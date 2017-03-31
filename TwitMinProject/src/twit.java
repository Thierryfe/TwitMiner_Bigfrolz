import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class twit {

	public static void main(String[] args) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey("v3Cxy44X499RfxsFhq6u653Xd");
		cb.setOAuthConsumerSecret("i5YNlwmsBG1y1Ek8qArzJ1KVGUK0v24A338K8aQ8Oe6psdMZXP");
		cb.setOAuthAccessToken("847791630681858049-fgXzqVz2I0rqa54tNrjnuTQNcCE9rv3");
		cb.setOAuthAccessTokenSecret("smKdHCBXvjzb6KiZHEWKkMTe3AsO50eX5mIPsgjTaBI2S");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		System.out.println("MDR");
		
	    Status status;
		try {
			status = twitter.updateStatus("e");
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
