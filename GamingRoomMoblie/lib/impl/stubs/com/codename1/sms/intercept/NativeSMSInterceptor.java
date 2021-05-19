package com.codename1.sms.intercept;


/**
 *  This is a low level API to bind the SMS interception code
 *  @deprecated internal implementation detail
 */
public interface NativeSMSInterceptor extends com.codename1.system.NativeInterface {

	public void bindSMSListener();

	public void unbindSMSListener();
}
