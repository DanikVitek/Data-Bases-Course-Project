package com.danikvitek.MCPluginMarketplace.data.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plugin_deny_reasons", schema = "course_project")
@IdClass(PluginDenyReasonPK.class)
public class PluginDenyReason {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "plugin_id", nullable = false)
    private Long pluginId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "plugin_version", nullable = false, length = 20)
    private String pluginVersion;
    @Basic
    @Column(name = "reason", nullable = true, length = 200)
    private String reason;

    public Long getPluginId() {
        return pluginId;
    }

    public void setPluginId(Long pluginId) {
        this.pluginId = pluginId;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    public void setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PluginDenyReason that = (PluginDenyReason) o;

        if (pluginId != null ? !pluginId.equals(that.pluginId) : that.pluginId != null) return false;
        if (pluginVersion != null ? !pluginVersion.equals(that.pluginVersion) : that.pluginVersion != null)
            return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pluginId != null ? pluginId.hashCode() : 0;
        result = 31 * result + (pluginVersion != null ? pluginVersion.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        return result;
    }
}
