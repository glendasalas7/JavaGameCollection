package model.ObserverPattern;

// knows its observers and provides an interface for attaching
// and detaching Observer objects.
public interface Subject {

    void addListener(Observer o);

    void removeListener(Observer o);

    void notifyListeners();
}
