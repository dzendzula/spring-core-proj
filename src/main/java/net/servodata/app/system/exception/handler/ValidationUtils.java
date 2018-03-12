package net.servodata.app.system.exception.handler;

import java.time.LocalDate;

import com.google.common.base.Strings;




/**
 * @author <a href="mailto:spilar@servodata.net">Martin Spilar</a>
 */
public class ValidationUtils {

    public static boolean isEmailValid(String email) {
        if (Strings.isNullOrEmpty(email)) {
            return false;
        }
        try {
            if (StringToEmailAddressConverter.INSTANCE.convert(email) == null) {
                return false;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        //anotacni validatory emailu opomijeji nektere moznosti, proto regex
        return email.matches(Constants.EMAIL_PATTERN);
    }

    public static boolean isBirthDateValid(LocalDate birthDate) {
        if (birthDate == null) {
            return false;
        }
        LocalDate bottomLimit = LocalDate.of(1900, 1, 1);
        return birthDate.isAfter(bottomLimit) && birthDate.isBefore(LocalDate.now());
    }

    public static boolean isLoginValid(String login) {
        if (Strings.isNullOrEmpty(login)) {
            return false;
        }

        return login.matches(Constants.LOGIN_PATTERN);
    }

    public static boolean isPasswordValid(String password) {
        if (Strings.isNullOrEmpty(password)) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;

        for (char ch : password.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                hasLowerCase = true;
            } else if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            }
            if (hasLowerCase && hasUpperCase && hasDigit) {
                return true;
            }
        }
        return false;
    }
}
