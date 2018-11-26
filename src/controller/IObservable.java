package controller;

public interface IObservable {
	void attach(IObserver o);
	void detach(IObserver o);
	void notifyObservers();
}
