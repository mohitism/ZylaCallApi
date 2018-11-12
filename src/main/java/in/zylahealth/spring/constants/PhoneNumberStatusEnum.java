package in.zylahealth.spring.constants;

public enum PhoneNumberStatusEnum {
	
	INVALID(-1,"INVALID"),
	INACTIVE(0, "INACTIVE"),
	ACTIVE(1, "ACTIVE"),;

    private final Integer value;
    private final String code;

    /**
     * @param value
     * @param code
     */
    private PhoneNumberStatusEnum(final Integer value, final String code) {
        this.value = value;
        this.code = code;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getCode() {
        return this.code;
    }

    /**
     * @param typeValue
     * @return
     */
    public static PhoneNumberStatusEnum fromInt(final Integer typeValue) {

        PhoneNumberStatusEnum enumeration = PhoneNumberStatusEnum.INVALID;
        switch (typeValue) {
            case 0:
                enumeration = PhoneNumberStatusEnum.INACTIVE;
            break;
            case 1:
                enumeration = PhoneNumberStatusEnum.ACTIVE;
            break;
        }
        return enumeration;
    }

    /**
     * @param typeCode
     * @return
     */
    public static PhoneNumberStatusEnum fromCode(final String typeCode) {

        PhoneNumberStatusEnum enumeration = PhoneNumberStatusEnum.INVALID;
        switch (typeCode) {
            case "ACTIVE":
                enumeration = PhoneNumberStatusEnum.ACTIVE;
            break;
            case "INACTIVE":
                enumeration = PhoneNumberStatusEnum.INACTIVE;
            break;

        }
        return enumeration;
    }
}
