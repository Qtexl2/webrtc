package com.example.webrtc.webrtc.model.janus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PluginData {
    private PluginType plugin;
    private DataResponse data;

    public PluginType getPlugin() {
        return plugin;
    }

    public void setPlugin(PluginType plugin) {
        this.plugin = plugin;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }
}
