package org.embryyo.corona.service.core;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SmsSender {

    public static final String ACCOUNT_NUMBER = "+12058464540";

    @Value( "${app.twilio.authToken}" )
    private String password;

    @Value( "${app.twilio.accountId}" )
    private String username;

    @Value( "${app.twilio.number}" )
    private String senderNumber;

    public void send(String otp, String number) {
        Twilio.init(username,password);
        String otpMsg = String.format("Your CoronaConnect verification code is: %s", otp);
        Message message = Message.creator(
                new PhoneNumber(number),
                new PhoneNumber(senderNumber),
                otpMsg
        ).create();
        System.out.println(message.getSid());
    }
}
