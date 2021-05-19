package com.codename1.sms.activation;


/**
 * Simple UI that uses SMS to verify the phone number of the device
 *  @author Shai Almog
 */
public class ActivationForm {

	public static final String[] COUNTRY_NAMES;

	public static final String[] COUNTRY_CODES;

	public static final String[] COUNTRY_FLAGS;

	public static final String[] COUNTRY_ISO2;

	public static final String[] COUNTRY_ISO3;

	/**
	 *  Builder style factory method
	 *  @param title the title
	 *  @return the instance of the activation form
	 */
	public static ActivationForm create(String title) {
	}

	/**
	 *  When true includes a "next" button in the title bar
	 *  @param tb true to include the titlebar next
	 *  @return instance of this class so creation can be chained
	 */
	public ActivationForm includeTitleBarNext(boolean tb) {
	}

	/**
	 *  When true includes a floating action button to go to the next step
	 *  @param fab true to include the fab
	 *  @return instance of this class so creation can be chained
	 */
	public ActivationForm includeFab(boolean fab) {
	}

	/**
	 *  Sets the text for the enter number label
	 *  @param t the text for that label
	 *  @return instance of this class so creation can be chained
	 */
	public ActivationForm enterNumberLabel(String t) {
	}

	/**
	 *  Sets the number of digits in the SMS verification code
	 *  @param d the number of digits
	 *  @return instance of this class so creation can be chained
	 */
	public ActivationForm codeDigits(int d) {
	}

	/**
	 *  Shows the activation form process and callback with the phone number when activated
	 *  @param phoneNumber a callback that will be invoked with the phone number when verification succeeds 
	 *  @param twilio sms sending gateway
	 */
	public void show(com.codename1.util.SuccessCallback phoneNumber, com.codename1.sms.twilio.TwilioSMS twilio) {
	}

	/**
	 *  Shows the activation form process and callback with the phone number when activated
	 *  @param phoneNumber a callback that will be invoked with the phone number when verification succeeds 
	 *  @param verify SMS verification
	 */
	public void show(com.codename1.util.SuccessCallback phoneNumber, SMSVerification verify) {
	}
}
