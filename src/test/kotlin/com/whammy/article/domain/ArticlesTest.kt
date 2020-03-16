package com.whammy.article.domain

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class ArticlesTest {

    @Test
    fun testSortByUpdatedAt() {
        val articles = Articles(
            listOf(
                Article(
                    "title-1",
                    "title1",
                    "body1",
                    LocalDateTime.of(2019, 1, 1, 0, 0),
                    emptyList(),
                    emptyList()
                ),
                Article(
                    "title-2",
                    "title2",
                    "body2",
                    LocalDateTime.of(2020, 1, 1, 0, 0),
                    emptyList(),
                    emptyList()
                )
            )
        )
        val expected = Articles(
            listOf(
                Article(
                    "title-2",
                    "title2",
                    "body2",
                    LocalDateTime.of(2020, 1, 1, 0, 0),
                    emptyList(),
                    emptyList()
                ),
                Article(
                    "title-1",
                    "title1",
                    "body1",
                    LocalDateTime.of(2019, 1, 1, 0, 0),
                    emptyList(),
                    emptyList()
                )
            )
        )

        assertEquals(expected, articles.sortByUpdatedAt())
    }
}