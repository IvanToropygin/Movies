package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    public static final String TAG = "MainViewModel";
    private int page = 1;

    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void loadMovies() {
        Disposable disposable = ApiFactory.apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<MovieResponse>() {
                            @Override
                            public void accept(MovieResponse movieResponse) throws Throwable {
                                List<Movie> newMovies = movieResponse.getMovies(); // Получаем новые фильмы
                                List<Movie> currentMovies = movies.getValue() != null
                                        ? new ArrayList<>(movies.getValue()) // Копируем существующие
                                        : new ArrayList<>(); // Создаем новый список при первом запросе

                                currentMovies.addAll(newMovies);
                                movies.setValue(currentMovies); // Всегда не-null список
                                page++;
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(TAG, throwable.toString());
                            }
                        }
                );
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
