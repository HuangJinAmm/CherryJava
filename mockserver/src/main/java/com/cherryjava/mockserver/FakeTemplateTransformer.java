package com.cherryjava.mockserver;

import com.github.jknack.handlebars.Helper;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

import java.util.HashMap;
import java.util.Map;

public class FakeTemplateTransformer extends ResponseDefinitionTransformer {

    private ResponseTemplateTransformer responseTemplateTransformer;

    private Map<String, Helper> helperMap;

    private boolean global;

    public FakeTemplateTransformer() {
        this(true);
    }

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource fileSource, Parameters parameters) {
        return responseTemplateTransformer.transform(request, responseDefinition, fileSource, parameters);
    }

    public FakeTemplateTransformer(boolean global) {
        this(global, new HashMap<>());
    }

    public FakeTemplateTransformer(boolean g, Map<String, Helper> helperMap) {
        this.global = g;
        this.helperMap = helperMap;
    }

    public FakeTemplateTransformer addHelpers(String name, Helper helper) {
        this.helperMap.put(name, helper);
        return this;
    }

    public void init() {
        this.responseTemplateTransformer = new ResponseTemplateTransformer(global, helperMap);
    }


    public Map<String, Helper> getHelperMap() {
        return helperMap;
    }

    public void setHelperMap(Map<String, Helper> helperMap) {
        this.helperMap = helperMap;
    }

    @Override
    public String getName() {
        return "FakeTemplateTransformer";
    }
}
