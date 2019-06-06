package com.yx.model.Base;

import lombok.Getter;
import lombok.Setter;

public class KeyValuePair {
    public KeyValuePair(String _key, String _value){
        key = _key;
        value = _value;
    }
    @Getter @Setter
    private String key;

    @Getter @Setter
    private String value;
}
