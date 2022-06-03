public class PhoneNumber {

    public static final int MAX_LENGTH_PHONE_NUMBER = 12;
    public static final String PHONE_START = "05", ISRAELI_AREA_CODE = "9725";

    private String phoneNum;

    public PhoneNumber(String phoneNum) {
        if (isValidPhoneNumber(phoneNum))
            this.phoneNum = phoneNum;
    }

    public static boolean isOnlyNumbers(String phoneNum) {
        int counter = 0;
        boolean onlyNumbers = false;
        for (int i = 0; i < phoneNum.length(); i++) {
            if (Character.isDigit(phoneNum.charAt(i)))
                counter++;
        }
        if (counter == phoneNum.length())
            onlyNumbers = true;
        return onlyNumbers;
    }


    public static boolean isValidAreaCode(String phoneNum, String start) {
        boolean isValid = false;
        if (phoneNum.substring(0, start.length()).equals(start))
            isValid = true;
        return isValid;
    }


    public static String formatPhoneNumber(String phoneNum) {
        String format = "";
        if (isOnlyNumbers(phoneNum))
            if (isValidAreaCode(phoneNum, ISRAELI_AREA_CODE))
                format = phoneNum;
            else {
                if (isValidAreaCode(phoneNum, PHONE_START))
                    format = ISRAELI_AREA_CODE + phoneNum.substring(PHONE_START.length());
            }
        return format;
    }

    public static boolean isValidPhoneNumber(String phoneNum) {
        boolean isValid = false;
        if (phoneNum.length() > 0) {
            phoneNum = formatPhoneNumber(phoneNum);
            if (phoneNum.length() == MAX_LENGTH_PHONE_NUMBER)
                isValid = true;
        }
        return isValid;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }
}