package AlienInvader.model.ObserverPattern;

public interface Subject {

    void addListener(Observer o);

    void removeListener(Observer o);

    void notifyListeners();
}
