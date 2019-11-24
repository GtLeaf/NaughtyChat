package com.cmd.hit.adwebview;

import com.cmd.hit.adwebview.jsBridge.jsBridgeInterface.IJsBridgeMethod;
import com.cmd.hit.adwebview.jsBridge.jsBridgeMethod.TestJsBridgeMethod;

import java.util.HashMap;
import java.util.Map;

public class CodeTest {
    private Map<String, Class<? extends IJsBridgeMethod>> jsMap = new HashMap<>();

    private void add() {
        jsMap.put("test", TestJsBridgeMethod.class);
    }
}
