package com.danikvitek.MCPluginMarketplace.api.controller;

import com.danikvitek.MCPluginMarketplace.api.dto.TagDto;
import com.danikvitek.MCPluginMarketplace.data.model.entity.Tag;
import com.danikvitek.MCPluginMarketplace.service.PluginService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scala.util.Try;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tags")
public final class TagController {
    private final PluginService pluginService;

    @GetMapping
    public @NotNull ResponseEntity<List<TagDto>> index() {
        return ResponseEntity.ok(pluginService.fetchAllTags().stream().map(pluginService::tagToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> show(@PathVariable long id) {
        return Try.apply(() -> {
            TagDto tag = pluginService.tagToDto(pluginService.fetchTagById(id));
            return ResponseEntity.ok(tag);
        }).getOrElse(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public @NotNull ResponseEntity<Void> create(@Valid @RequestBody @NotNull TagDto tag) {
        return Try.apply(() -> {
            Try<Tag> existingTag = 
                    Try.apply(() -> pluginService.fetchTagByTitle(tag.getTitle()));
            long id = existingTag.isFailure()
                    ? pluginService.createTag(tag.getTitle()).getId()
                    : existingTag.get().getId();
            String location = String.format("/tags/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        }).getOrElse(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public @NotNull ResponseEntity<Void> delete(@PathVariable long id) {
        pluginService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
