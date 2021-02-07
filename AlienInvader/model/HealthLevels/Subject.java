package AlienInvader.model.HealthLevels;

public interface Subject {

    void addListener(Observer o);

    void removeListener(Observer o);

    void notifyListeners();
}
