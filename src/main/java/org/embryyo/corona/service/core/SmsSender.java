package org.embryyo.corona.service.core;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class SmsSender {

    public static final String ACCOUNT_SIG = "ACc9d7c1fd2aaa4971fbd253c13ce71093";
    public static final String AUTH_TOKEN = "142bf500bb617f590db3b4786aa99e19";
    public static final String ACCOUNT_NUMBER = "+12058464540";

    public void send(String otp, String number) {
        Twilio.init(ACCOUNT_SIG,AUTH_TOKEN);
        String otpMsg = String.format("Your CoronaConnect verification code is: %s", otp);
        Message message = Message.creator(
                new PhoneNumber(number),
                new PhoneNumber(ACCOUNT_NUMBER),
                otpMsg
        ).create();
        System.out.println(message.getSid());
    }
}
