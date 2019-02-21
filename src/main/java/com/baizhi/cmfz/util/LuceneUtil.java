package com.baizhi.cmfz.util;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author Mlonrin
 * @date 2019/1/16 20:33
 */
public class LuceneUtil {

    /**
     *  日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(LuceneUtil.class);

    /**
     *  索引库对象
     */
    private static FSDirectory directory;
    /**
     * 索引写入配置对象
     */
    private static IndexWriterConfig indexWriterConfig;
    /**
     * 分词器（智能中文分词器）
     */
    public static SmartChineseAnalyzer analyzer;
    /**
     *  索引库读入对象
     */
    private static DirectoryReader reader;

    static {
        try {
            //创建分词器，参数版本
            analyzer = new SmartChineseAnalyzer(Version.LUCENE_44);
            //创建索引库，参数索引库文件夹
            directory = FSDirectory.open(new File("E://lucentest"));
            //创建索引写入对象的配置对象，参数1 版本 参数2 对文档对象建立索引时用的分词器
            indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44,analyzer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return 索引写入对象
     */
    public static IndexWriter getIndexWriter(){
        IndexWriter indexWriter = null;
        try {
            //创建索引写入对象，参数1 要写入到哪个索引库 参数2 建立索引的配置对象
            indexWriter = new IndexWriter(directory,indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    /**
     *
     * @return  索引搜索对象
     * @throws IOException
     */
    public static IndexSearcher getIndexSearcher() throws IOException {
        //创建索引库读入对象，参数 要读取的索引库
        reader = DirectoryReader.open(directory);
        //创建索引库的索引搜索对象
        return new IndexSearcher(reader);
    }

    /**
     * 将文档对象的索引提交到索引库，并释放资源
     * @param indexWriter 索引写入对象
     */
    public static void indexCommit(IndexWriter indexWriter){
        try {
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  将文档对象的索引回滚，不提交到索引库，并释放资源
     * @param indexWriter
     */
    public static void indexRollback(IndexWriter indexWriter){
        try {
            indexWriter.rollback();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断索引库中有没有索引文件 如果没有 返回false  如果有返回true
     * @return
     */
    public static boolean judgeIndexDB(){
        IndexSearcher indexSearcher = null;
        /**
         * MatchAllDocsQuery  查询全部document信息
         */
        Query query = new MatchAllDocsQuery();
        TopDocs topDocs = null;
        try {
            indexSearcher = getIndexSearcher();
            topDocs = indexSearcher.search(query, 10000);
        } catch (Exception e) {
            return false;
        }
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        logger.info("目前索引库中有："+scoreDocs.length+"条数据");
        if (scoreDocs.length==0){
            return false;
        }
        return true;
    }
}
