package com.whammy.article.usecase

import com.whammy.article.domain.Article
import com.whammy.article.domain.Articles
import com.whammy.article.domain.Comment
import com.whammy.article.domain.Comments
import org.springframework.stereotype.Service

@Service
class ArticleUsecase(private val repository: IArticleRepository) {
    fun getArticlesOrderedByUpdatedDateTime(): Articles {
        return repository.getArticles().sortByUpdatedAt()
    }

    fun getArticle(slug: String): Article {
        return repository.getArticle(slug)
    }

    fun getCommentsOfArticle(slug: String): Comments {
        return repository.getCommentsOfArticle(slug)
    }

    fun addComment(email: String, slug: String, body: String): Comment {
        val comments = repository.getCommentsOfArticle(slug)
        val newComments = comments.add(email, body)
        repository.saveComments(slug, newComments)
        return newComments.getLatestComment()
    }

    fun toggleFavorite(slug: String, email: String): Article {
        val article = repository.getArticle(slug)
        return repository.saveArticle(article.toggleFavoriteFrom(email))
    }

    fun createNewArticle(email: String, title: String, body: String): Article {
        val article = Article.of(email, title, body)
        if (repository.articleExists(article.slug)) {
            val newArticle = article.assignNewSlug()
            return repository.saveArticle(newArticle)
        } else {
            return repository.saveArticle(article)
        }
    }

    fun updateArticle(slug: String, authorEmailAddress: String, title: String?, body: String?): Article {
        val article = repository.getArticle(slug)
        return repository.updateArticle(slug, article.update(title, body))
    }

    fun delete(slug: String) {
        if (repository.articleExists(slug)) repository.deleteArticle(slug)
    }
}
