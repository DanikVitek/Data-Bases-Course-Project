package com.danikvitek.MCPluginMarketplace.data.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plugin_tags", schema = "course_project")
@IdClass(PluginTagPK.class)
public class PluginTag {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "plugin_id", nullable = false)
    private Long pluginId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tag_id", nullable = false)
    private Long tagId;

    public Long getPluginId() {
        return pluginId;
    }

    public void setPluginId(Long pluginId) {
        this.pluginId = pluginId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PluginTag pluginTag = (PluginTag) o;

        if (pluginId != null ? !pluginId.equals(pluginTag.pluginId) : pluginTag.pluginId != null) return false;
        if (tagId != null ? !tagId.equals(pluginTag.tagId) : pluginTag.tagId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pluginId != null ? pluginId.hashCode() : 0;
        result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
        return result;
    }
}
