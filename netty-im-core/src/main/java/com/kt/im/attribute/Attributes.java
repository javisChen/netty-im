package com.kt.im.attribute;

import com.kt.im.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

    AttributeKey<String> CURRENT_USER = AttributeKey.newInstance("current_user");

}
