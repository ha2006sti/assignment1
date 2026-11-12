public class CarTest {
    private static void reportResult(String testName, boolean success) {
        IO.println((success ? "SUCCESS: " : "FAILURE: ") + testName);
    }

    /**
     * Tests whether a Saab95 with turbo enabled
     * gains more speed than one without.
     */
    private static void testTurbo() {
        Saab95 slowSaab = new Saab95();
        Saab95 fastSaab = new Saab95();
        fastSaab.setTurboOn();
        slowSaab.setTurboOff();
        fastSaab.gas(0.5);
        slowSaab.gas(0.5);
        boolean result = slowSaab.getCurrentSpeed() < fastSaab.getCurrentSpeed();
        reportResult("Turbo yields more speed", result);
    }

    private static void testStartEngine() {
        Volvo240 car = new Volvo240();
        car.startEngine();

        boolean result = car.getCurrentSpeed() == 0.1;
        reportResult("Engine starts correctly", result);
    }

    private static void testStopEngine() {
        Saab95 testSaab = new Saab95();
        testSaab.startEngine();
        testSaab.gas(0.5);
        testSaab.stopEngine();

        boolean result = testSaab.getCurrentSpeed() == 0;
        reportResult("Engine stops the car", result);
    }

    public static void testMove(){
        Volvo240 car = new Volvo240();
        car.startEngine();
        car.move();

        boolean result = car.getY() == 0.1;
        reportResult("Car moves forward", result);
    }

    private static void testMoveUp() {
        Saab95 testSaab = new Saab95();
        testSaab.startEngine();
        testSaab.gas(0.5);
        testSaab.move();
        boolean result = testSaab.getY() > 0 && testSaab.getX() == 0;
        reportResult("Car moves up without moving sideways", result);
    }

    private static void testTurnRight() {
        Volvo240 car = new Volvo240();
        car.startEngine();
        car.turnRight();
        car.move();

        boolean result = car.getX() == 0.1 && car.getY() == 0;
        reportResult("Car turns right correctly", result);
    }

    private static void testTurnLeft() {
        Saab95 testSaab = new Saab95();
        testSaab.startEngine();
        testSaab.gas(0.5);
        testSaab.turnLeft();
        testSaab.move();
        boolean result = testSaab.getX() < 0 && testSaab.getY() == 0;
        reportResult("Car moves left", result);
    }

    private static void testBrake() {
        Saab95 testSaab = new Saab95();
        testSaab.startEngine();
        testSaab.gas(0.5);
        double speedBeforeBrake = testSaab.getCurrentSpeed();
        testSaab.brake(0.5);
        boolean result = testSaab.getCurrentSpeed() < speedBeforeBrake;
        reportResult("Braking reduces speed", result);
    }

    private static void testSpeedNeverNegative() {
        Saab95 testSaab = new Saab95();
        testSaab.startEngine();
        testSaab.brake(1.0);
        boolean result = testSaab.getCurrentSpeed() >= 0;
        reportResult("Speed never negative", result);
    }


    private static void testInvalidGasAmount() {
        Saab95 testSaab = new Saab95();
        double speedBefore = testSaab.getCurrentSpeed();
        testSaab.gas(1.5);
        boolean result = testSaab.getCurrentSpeed() == speedBefore;
        reportResult("Invalid gas amount is rejected", result);
    }

    private static void testInvalidBrakeAmount() {
        Saab95 testSaab = new Saab95();
        testSaab.startEngine();
        testSaab.gas(0.5);
        double speedBefore = testSaab.getCurrentSpeed();
        testSaab.brake(1.5);
        boolean result = testSaab.getCurrentSpeed() == speedBefore;
        reportResult("Invalid brake amount is rejected", result);
    }


    private static void testSpeedNeverAboveEnginePower() {
        Saab95 testSaab = new Saab95();
        testSaab.startEngine();
        for (int i = 0; i < 300; i++) {
            testSaab.gas(1);
        }
        boolean result = testSaab.getCurrentSpeed() <= testSaab.getEnginePower();
        reportResult("Speed never above engine power", result);
    }

    private static void testGasDoesNotDecreaseSpeed() {
        Saab95 testSaab = new Saab95();
        double speedBeforeGas = testSaab.getCurrentSpeed();

        testSaab.gas(0.5);

        boolean result = testSaab.getCurrentSpeed() >= speedBeforeGas;
        reportResult("Gas does not decrease speed", result);
    }

    private static void testBrakeDoesNotIncreaseSpeed() {
        Saab95 testSaab = new Saab95();
        testSaab.gas(0.5);
        double speedBeforeBrake = testSaab.getCurrentSpeed();

        testSaab.brake(0.5);

        boolean result = testSaab.getCurrentSpeed() <= speedBeforeBrake;
        reportResult("Brake does not increase speed", result);
    }

    public static void testNegativeBrake(){
        Volvo240 car = new Volvo240();
        car.startEngine();
        double before = car.getCurrentSpeed();
        car.brake(-0.5);

        boolean result = before == car.getCurrentSpeed();
        reportResult("Negative brake does not change speed", result);
    }

    private static void testNegativeGas() {
        Volvo240 car = new Volvo240();
        car.startEngine();

        double before = car.getCurrentSpeed();

        car.gas(-0.5);

        boolean result = car.getCurrentSpeed() == before;

        reportResult("Negative gas does not change speed", result);
    }

    static void main() {
        testTurbo();
        testStartEngine();
        testStopEngine();
        testMove();
        testMoveUp();
        testTurnRight();
        testTurnLeft();
        testBrake();
        testInvalidGasAmount();
        testInvalidBrakeAmount();
        testSpeedNeverNegative();
        testSpeedNeverAboveEnginePower();
        testGasDoesNotDecreaseSpeed();
        testBrakeDoesNotIncreaseSpeed();
        testNegativeBrake();
        testNegativeGas();
    }
