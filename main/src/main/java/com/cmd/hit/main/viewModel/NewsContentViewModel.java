package com.cmd.hit.main.viewModel;

import com.cmd.hit.main.model.bean.News;
import com.cmd.hit.main.model.repository.NewsRepository;

import io.reactivex.Observable;

/**
 * Created by PC-0775 on 2019/5/6.
 */

public class NewsContentViewModel {
    //News的数据仓库
    private NewsRepository repository;

    public NewsContentViewModel(NewsRepository repository){
        this.repository = repository;
    }

    public Observable<News> getNewsObservable(int newsId){
        return repository.getNewsItem(newsId);
    }

}
