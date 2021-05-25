open class ValueObservable {
    protected lateinit var valueObservers : MutableList<IValueObserver>
    protected var value : Double = -1.0

    public fun attachObserver(newValueObserver: IValueObserver){valueObservers.add(newValueObserver)}

    public fun detachObserver(targetObserver: IValueObserver){valueObservers.remove(targetObserver)}

    public fun notifyObservers(){
        for(observer in valueObservers){
            observer.update(value)
        }
    }
}