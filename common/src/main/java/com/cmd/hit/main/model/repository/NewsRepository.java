package com.cmd.hit.main.model.repository;

import android.util.Log;
import android.util.LruCache;

import com.cmd.hit.main.model.bean.BeforeNews;
import com.cmd.hit.main.model.bean.LatestNews;
import com.cmd.hit.main.model.bean.News;
import com.cmd.hit.main.model.dao.NewsDao;
import com.cmd.hit.main.model.remote.api.NewsService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by PC-0775 on 2019/4/29.
 */

public class NewsRepository {
    private NewsDao dao;
    private NewsService network;
    private static LruCache<String, BeforeNews> beforeNewsLruCache = new LruCache<>(1024*8);

    public NewsRepository(NewsDao dao, NewsService network){
        this.dao = dao;
        this.network = network;
    }

    //获取单个新闻
    public Observable<News> getNewsItem(final int newsId){
        //访问本地数据
        return dao.getCacheNews(newsId+"")
                .onErrorResumeNext(
                        network.getNews(newsId)//本地数据为空，获取网络数据
                                .doOnNext(news -> {
                                    Log.d("getNewsItem", "获取网络数据成功");
                                    dao.cacheData(news.getId()+"", news);
                                }));
    }

    //获取过往新闻摘要
    public Observable<BeforeNews> getBeforeNews(String key){
        String cacheKeyPrefix = "before:";
        //组装key
        return dao.getCache(cacheKeyPrefix+key, BeforeNews.class)
                .onErrorResumeNext(network.getBeforeNews(key)
                        .doOnNext(beforeNews -> dao.cacheData(cacheKeyPrefix+key, beforeNews))
                );
    }

    //获取当日新闻摘要
    public Observable<LatestNews> getLatestNews(){
        String cacheKeyPrefix = "latest:";
        String key = new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(new Date());
        //组装key
        return dao.getCache(cacheKeyPrefix+key, LatestNews.class)
                .onErrorResumeNext(network.getLatestNews()
                        .doOnNext(latestNews -> dao.cacheData(cacheKeyPrefix+latestNews.getDate(), latestNews))
                );
    }
}
