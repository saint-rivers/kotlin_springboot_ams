package com.kshrd.amsfull.service.article

import com.kshrd.amsfull.model.entity.Article
import com.kshrd.amsfull.model.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CommentRepository : JpaRepository<Comment, UUID> {

    fun findAllByArticle(article: Article): List<Comment>
}