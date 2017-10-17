package cn.zhendless.endlessstudy.rxjava;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhanglei on 16/10/17.
 */

public class RxJavaStudy {

    private static final String TAG = RxJavaStudy.class.getSimpleName();

    private void createObserver() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "observer, onNext s = " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "observer, onError s = ");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "observer, onComplete");
            }
        };
    }

    private void createSubscriber() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "subscriber, onNext s = " + s);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "subscriber, onComplete");
            }
        };
    }

    private void createObservable() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello");
                e.onNext("Hi");
                e.onNext("Endless!!");
                e.onComplete();
            }
        });


    }


}
