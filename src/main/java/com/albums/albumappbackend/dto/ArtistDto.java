package com.albums.albumappbackend.dto;

import jakarta.validation.constraints.NotBlank;


public record ArtistDto(
        long id,
        @NotBlank
        String title
) {


}
