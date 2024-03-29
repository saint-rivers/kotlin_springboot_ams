package com.kshrd.amsfull.model.entity

import com.kshrd.amsfull.model.dto.ArticleDto
import com.kshrd.amsfull.model.dto.BookmarkDto
import org.hibernate.Hibernate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "articles")
open class Article : Document {

    constructor() : super()

    constructor(title: String, description: String, isPublished: Boolean = false, thumbnail: String) :
            super(title = title, description = description, isPublished = isPublished, thumbnail = thumbnail) {
    }

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH])
    @JoinTable(
        name = "article_categories",
        joinColumns = [JoinColumn(name = "article_id", foreignKey = ForeignKey(name = "fk_article_id"))],
        inverseJoinColumns = [JoinColumn(name = "category_id", foreignKey = ForeignKey(name = "fk_category_id"))]
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    open var categories: MutableSet<Category> = mutableSetOf()

    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.DETACH, CascadeType.REFRESH])
    @JoinColumn(name = "teacher_id", nullable = false, foreignKey = ForeignKey(name = "fk_teacher_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    open var teacher: AppUser? = null

    @OneToMany(mappedBy = "article", orphanRemoval = true)
    open var comments: MutableSet<Comment> = mutableSetOf()

    @Column(name = "created_date", nullable = false)
    open var createdDate: LocalDateTime? = null

    @Column(name = "last_modified", nullable = false)
    open var lastModified: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Article

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    fun toDto() = id?.let {
        ArticleDto(
            id = it,
            title = title!!,
            description = description,
            categories = categories.map { cat -> cat.toDto() }.toSet(),
            isPublished = isPublished == true,
            teacher = teacher?.toDto()!!,
            comments = comments.map { comment -> comment.toDto()!! }.toSet(),
            thumbnail = thumbnail!!,
            createdDate = createdDate!!,
            lastModified = lastModified!!
        )
    }

    fun toBookmarkDto(): BookmarkDto {
        return BookmarkDto(
            articleId = id!!,
            title = title!!,
            description = description!!,
            teacher = teacher?.toDto()!!,
            thumbnail = thumbnail!!
        )
    }

}