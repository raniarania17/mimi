package ConnectionDB;

public class LogicTools {

    /*
     * Negative < 0
     * 0 - 99 MIN_TRIP
     * 100 - 499 SHORT1_TRIP
     * 500 - 999 SHORT2_TRIP
     * 1000 - 1999 MEAN1_TRIP
     * 2000 - 2999 MEAN2_TRIP
     * >3000 LONG_TRIP
     * */
    public static TripTimeClass calculateIntervalTrip(int tripTime) {
        TripTimeClass tripTimeClass = TripTimeClass.DEFAULT;

        if (tripTime < 0) {
            tripTimeClass = TripTimeClass.NEGATIVE;
        } else if (tripTime < 100) {
            tripTimeClass = TripTimeClass.MIN_TRIP;
        } else if (tripTime < 499) {
            tripTimeClass = TripTimeClass.SHORT1_TRIP;
        } else if (tripTime < 999) {
            tripTimeClass = TripTimeClass.SHORT2_TRIP;
        } else if (tripTime < 1999) {
            tripTimeClass = TripTimeClass.MEAN1_TRIP;
        } else if (tripTime < 2999) {
            tripTimeClass = TripTimeClass.MEAN2_TRIP;
        } else if (tripTime > 2999) {
            tripTimeClass = TripTimeClass.LONG_TRIP;
        }
        return tripTimeClass;
    }

    public enum TripTimeClass {
        DEFAULT, NEGATIVE, MIN_TRIP, SHORT1_TRIP, SHORT2_TRIP, MEAN1_TRIP, MEAN2_TRIP, LONG_TRIP
    }
}
