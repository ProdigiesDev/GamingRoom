package com.codename1.sms.activation;


/**
 *  The SMS verification class can be extended to enhance the capabilities of 
 *  this API
 * 
 *  @author Shai Almog
 */
public class SMSVerification {

	/**
	 *  Subclasses that don't need twilio support can use this constructor
	 */
	protected SMSVerification() {
	}

	/**
	 *  This API can be used with Twilio local verification
	 *  @param twilio the instance of Twilio SMS support
	 */
	public SMSVerification(com.codename1.sms.twilio.TwilioSMS twilio) {
	}

	public void sendSMSCode(String phone) {
	}

	public void verifySmsCode(String code, com.codename1.util.SuccessCallback s) {
	}
}
