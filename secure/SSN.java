package me.bottdev.fantasyapi.secure;

import static org.apache.commons.lang3.Validate.*;

public class SSN {

    private final String ssn;

    public SSN(String ssn) {

        notNull(ssn);
        inclusiveBetween(11, 11, ssn.length());
        isTrue(ssn.matches(
                "^(?!666|000|9\\d{2})\\d{3}" +
                "-(?!00)\\d{2}-" +
                "(?!0{4})\\d{4}$"));
        this.ssn = ssn;
    }
}
