package com.TechsCode.Backend.config.fields;

import java.io.File;

public class FrontendField {

    private final File path;

    public FrontendField(File path) {
        this.path = path;
    }

    public File getPath() {
        return path;
    }
}
