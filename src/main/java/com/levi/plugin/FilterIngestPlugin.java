package com.levi.plugin;

import java.util.HashMap;
import java.util.Map;

import com.levi.FilterWordProcessor;
import org.elasticsearch.ingest.Processor;
import org.elasticsearch.plugins.IngestPlugin;
import org.elasticsearch.plugins.Plugin;

/**
 * 插件类，继承Plugin，实现IngestPlugin，因为我们是做管道处理，所以要实现IngestPlugin
 * 这样的类才会被ES加载
 */
public class FilterIngestPlugin extends Plugin implements IngestPlugin {

    @Override
    public Map<String, Processor.Factory> getProcessors(Processor.Parameters parameters) {
        Map<String, Processor.Factory> processors = new HashMap<>();
        processors.put(FilterWordProcessor.TYPE, new FilterWordProcessor.Factory());
        return processors;
    }
}