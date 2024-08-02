package com.levi;

import java.util.Map;

import org.elasticsearch.ingest.AbstractProcessor;
import org.elasticsearch.ingest.ConfigurationUtils;
import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.Processor;

public class FilterWordProcessor extends AbstractProcessor {

    // 我们的管道名称
    public static final String TYPE = "levi_filter_word";

    private String myfilterWord;

    private String myfield;

    public FilterWordProcessor(String tag, String description, String myfilterWord, String myfield) {
        super(tag, description);
        this.myfilterWord = myfilterWord;
        this.myfield = myfield;
    }

    @Override
    public IngestDocument execute(IngestDocument ingestDocument) throws Exception {
        IngestDocument document = ingestDocument;
        String value = document.getFieldValue(myfield, String.class);
        String clearedValue = value.replace(myfilterWord, "");
        document.setFieldValue(myfield, clearedValue);
        return document;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public static final class Factory implements Processor.Factory {

        @Override
        public Processor create(Map<String, Processor.Factory> processorFactories, String processorTag, String description, Map<String, Object> config) throws Exception {
            // 获取配置参数也就是你DSL里面的KEY值
            String field = ConfigurationUtils.readStringProperty(TYPE, processorTag, config, "myfield");
            String filterWord = ConfigurationUtils.readStringProperty(TYPE, processorTag, config, "myfilterWord");
            return new FilterWordProcessor(processorTag, description,filterWord, field);
        }
    }

}