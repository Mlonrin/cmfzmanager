package com.baizhi.cmfz.lucene.impl;

import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.lucene.LuceneDao;
import com.baizhi.cmfz.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mlonrin
 * @date 2019/1/17 14:58
 */
@Component
public class LuceneDaoImpl implements LuceneDao {

    @Override
    public boolean createIndex(List<Article> cmfzArticleList) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            for (Article article : cmfzArticleList) {
                try {
                    Document document = new Document();
                    document.add(new StringField("articleId",article.getArticleId().toString(), Field.Store.YES));
                    document.add(new TextField("articleName",article.getArticleName(), Field.Store.YES));
                    document.add(new TextField("articleContent",article.getArticleContent(), Field.Store.YES));
                    indexWriter.addDocument(document);
                    indexWriter.commit();
                } catch (IOException e) {
                    LuceneUtil.indexRollback(indexWriter);
                    throw new RuntimeException(e);
                }
            }
            indexWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addIndex(Article article) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();

        try {
            Document document = new Document();
            document.add(new StringField("articleId",article.getArticleId().toString(), Field.Store.YES));
            document.add(new TextField("articleName",article.getArticleName(), Field.Store.YES));
            document.add(new TextField("articleContent",article.getArticleContent(), Field.Store.YES));
            indexWriter.addDocument(document);
            LuceneUtil.indexCommit(indexWriter);
            return true;
        } catch (Exception e) {
            LuceneUtil.indexRollback(indexWriter);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean resettingIndex(List<Article> cmfzArticleList) {
        return deleteAllIndex()&&createIndex(cmfzArticleList);
    }

    @Override
    public boolean deleteAllIndex() {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteAll();
            LuceneUtil.indexCommit(indexWriter);
            return true;
        } catch (IOException e) {
            LuceneUtil.indexRollback(indexWriter);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteIndexById(int articleId) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            String[] fields = {"articleId"};
            MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_44,fields,LuceneUtil.analyzer);
            Query query = queryParser.parse(articleId + "");

            indexWriter.deleteDocuments(query);

            LuceneUtil.indexCommit(indexWriter);
            return true;
        } catch (Exception e) {
            LuceneUtil.indexRollback(indexWriter);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateIndexById(Article article) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();

        try {
            Document document = new Document();
            document.add(new StringField("articleId",article.getArticleId().toString(), Field.Store.YES));
            document.add(new TextField("articleName",article.getArticleName(), Field.Store.YES));
            document.add(new TextField("articleContent",article.getArticleContent(), Field.Store.YES));

            indexWriter.updateDocument(new Term("articleId",article.getArticleId().toString()),document);

            LuceneUtil.indexCommit(indexWriter);
            return true;
        } catch (IOException e) {
            LuceneUtil.indexRollback(indexWriter);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> luceneSeleteByKeyword(String keyword) {
        List<Article> articleList = new ArrayList<>();

        try {
            IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();

            String[] fields = {"articleId","articleName","articleContent"};
            MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_44, fields, LuceneUtil.analyzer);
            Query query = queryParser.parse(keyword);

            TopDocs topDocs = indexSearcher.search(query, 20);

            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            for (ScoreDoc scoreDoc : scoreDocs) {
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);

                String articleId = document.get("articleId");
                String articleName = document.get("articleName");
                String articleContent = document.get("articleContent");

                Article article = new Article();
                article.setArticleId(Integer.parseInt(articleId));
                article.setArticleName(articleName);
                article.setArticleContent(articleContent);
                articleList.add(article);
            }
            return articleList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
