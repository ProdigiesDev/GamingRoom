package com.codename1.sms.twilio;


/**
 *  Sends an SMS via the Twilio REST API
 * 
 *  @author Shai Almog
 */
public class TwilioSMS {

	/**
	 *  Create an instance of the SMS API
	 *  
	 *  @param accountSID the account id
	 *  @param authToken the authorization token
	 *  @param fromPhone the phone from which we are sending
	 *  @return twilio instance
	 */
	public static TwilioSMS create(String accountSID, String authToken, String fromPhone) {
	}

	/**
	 *  Sends an SMS using the twilio API asynchronously
	 *  @param phone the phone we are sending to
	 *  @param body the body of the sms
	 */
	public void sendSmsAsync(String phone, String body) {
	}

	/**
	 *  Sends an SMS using the twilio API synchronously
	 *  @param phone the phone we are sending to
	 *  @param fromPhone the phone from which we are sending
	 *  @param body the body of the sms
	 */
	public void sendSmsSync(String phone, String fromPhone, String body) {
	}
}
