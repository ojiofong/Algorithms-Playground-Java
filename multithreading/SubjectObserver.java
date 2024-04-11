import java.util.HashSet;
import java.util.Set;

interface Observer {
    void update(String message);
}

// Subject is responsible for notifying all observers
class Subject {
    private Set<Observer> observers;

    public Subject() {
        this.observers = new HashSet<>();
    }

    // Synchronized method to add an observer
    public synchronized void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Synchronized method to remove an observer
    public synchronized void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Synchronized method to notify all observers
    public synchronized void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
