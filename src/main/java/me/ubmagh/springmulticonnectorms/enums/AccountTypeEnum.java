package me.ubmagh.springmulticonnectorms.enums;

import jakarta.xml.bind.annotation.XmlEnum;

@XmlEnum()
public enum AccountTypeEnum {
    ORGANISATION, USER;

    public String value() {
        return name();
    }

    public static AccountTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
