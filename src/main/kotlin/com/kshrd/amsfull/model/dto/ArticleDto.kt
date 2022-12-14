package com.kshrd.amsfull.model.dto

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class ArticleDto(
    val id: UUID,
    val title: String,
    val description: String? = null,
    val isPublished: Boolean,
    var categories: Set<CategoryDto> = emptySet(),
    var teacher: AppUserDto,
    val comments: Set<CommentDto> = emptySet(),
    val thumbnail: String,
    val createdDate: LocalDateTime,
    val lastModified: LocalDateTime
) : Serializable
