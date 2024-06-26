package com.cherryjava.mockserver;

import com.github.jknack.handlebars.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.helpers.HandlebarsHelper;
import com.mifmif.common.regex.Generex;

import java.io.IOException;

public class GenerexHandleHelper extends HandlebarsHelper<Object> {

    private Generex generex;

    public GenerexHandleHelper(String regex) {
        this.generex = new Generex(regex);
    }

    @Override
    public Object apply(Object o, Options options) throws IOException {
        return this.generex.random();
    }

    public Generex getGenerex() {
        return generex;
    }

    public void setGenerex(Generex generex) {
        this.generex = generex;
    }
}

