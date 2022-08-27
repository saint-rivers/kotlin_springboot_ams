package com.kshrd.amsfull.model

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@MappedSuperclass
open class Document(title: String, description: String, isPublished: Boolean) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "title")
    open var title: String = title

    @Column(name = "description")
    open var description: String = description

    @Column(name = "is_published", nullable = false)
    open var isPublished: Boolean? = false
}