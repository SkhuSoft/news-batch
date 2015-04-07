package com.soft.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.news.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
