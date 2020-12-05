package model.Berries;
// // participant: Observer

// //      Observer defines an updating interface for objects that should be
// //                  be notified of changes in a subject

import model.Shooter;

public interface Observer {

    void actionPerformed(Shooter shooter);
}
