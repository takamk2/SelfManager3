package local.takamk2.selfmanager3

import io.reactivex.Observable
import io.reactivex.observables.ConnectableObservable
import io.reactivex.subjects.PublishSubject

class SampleSubject {

    private val subject: PublishSubject<Int> = PublishSubject.create()
    private val observable: ConnectableObservable<Int>

    init {
        observable = subject.publish()
    }

    fun putData(data: Int) {
        subject.onNext(data)
    }

    fun changes(): Observable<Int> {
        return observable.refCount()
    }
}