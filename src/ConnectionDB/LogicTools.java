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
     public static TripDistanceClass calculateIntervalDTrip(int tripDistance) {
        TripDistanceClass tripDistanceClass = TripDistanceClass.DEFAULT;

        if (tripDistance< 0) {
           tripDistanceClass = TripDistanceClass.NEGATIVE1;
        } else if (tripDistance < 0.1) {
            tripDistanceClass = TripDistanceClass.MIN_TRIP1;
        } else if (tripDistance < 5) {
            tripDistanceClass = TripDistanceClass.SHORT1_TRIP1;
        } else if (tripDistance < 10) {
            tripDistanceClass = TripDistanceClass.SHORT2_TRIP1;
        } else if (tripDistance < 15) {
           tripDistanceClass = TripDistanceClass.MEAN1_TRIP1;
        } else if (tripDistance < 20) {
            tripDistanceClass = TripDistanceClass.MEAN2_TRIP1;
        } else if (tripDistance > 30) {
            tripDistanceClass = TripDistanceClass.LONG_TRIP1;
        }
        return tripDistanceClass;
    }
 public enum TripDistanceClass {
        DEFAULT, NEGATIVE1, MIN_TRIP1, SHORT1_TRIP1, SHORT2_TRIP1, MEAN1_TRIP1, MEAN2_TRIP1, LONG_TRIP1
    }
  public static TotalAmountClass  calculateIntervalTAmount(int totalAmount) {
        TotalAmountClass   totalAmountClass  =  TotalAmountClass.DEFAULT;

        if (totalAmount< 0) {
           totalAmountClass =  TotalAmountClass.NEGATIVE2;
        } else if (totalAmount < 5) {
           totalAmountClass =  TotalAmountClass.MIN_TRIP2;
        } else if (totalAmount <8) {
            totalAmountClass=  TotalAmountClass.SHORT1_TRIP2;
        } else if (totalAmount < 15) {
           totalAmountClass =  TotalAmountClass.SHORT2_TRIP2;
        } else if (totalAmount < 50) {
          totalAmountClass =  TotalAmountClass.MEAN1_TRIP2;
        } else if (totalAmount < 70) {
           totalAmountClass =  TotalAmountClass.MEAN2_TRIP2;
        } else if (totalAmount > 100) {
           totalAmountClass =  TotalAmountClass.LONG_TRIP2;
        }
        return totalAmountClass;
    }
 public enum TotalAmountClass {
        DEFAULT, NEGATIVE2, MIN_TRIP2, SHORT1_TRIP2, SHORT2_TRIP2, MEAN1_TRIP2, MEAN2_TRIP2, LONG_TRIP2
    }
}
