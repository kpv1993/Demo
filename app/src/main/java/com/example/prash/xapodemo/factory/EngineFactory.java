package com.example.prash.xapodemo.factory;

import com.example.prash.xapodemo.engine.Engine;
import com.example.prash.xapodemo.engine.IEngine;

public class EngineFactory {

        public static IEngine getEngine(){
            return Engine.getInstance();
        }
}
