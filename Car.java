import java.awt.Color;

public abstract class Car implements Movable {
    private int nrDoors;
    private double enginePower;
    private  double currentSpeed;
    private Color color;
    private String modelName;
        public String getModelName() {
            return modelName;
        }
    private double x = 0;
    private double y = 0;
    private int direction = 0;

        public Car(int doors, double power, Color clr, String model){
        this.nrDoors = doors;
       this.enginePower = power;
       this.color = clr;
       this.modelName = model;
        }

    public int getNrDoors()
    {
        return nrDoors;
    }
    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    public abstract double speedFactor();
    private void incrementSpeed(double amount) {
        currentSpeed = Math.min(enginePower, getCurrentSpeed() + speedFactor() * amount);
        }
    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(0, getCurrentSpeed() - speedFactor() * amount);
        }
        public void gas(double amount) {if (amount < 0 || amount > 1) {
            return;
        }
            incrementSpeed(amount);

        }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            return;
        }
        decrementSpeed(amount);
    }
    @Override
    public void turnRight() {
        direction++;

        if (direction == 4) {
        direction = 0;
        }
    }

    @Override
    public void turnLeft() {
        direction--;

        if (direction == -1) {
        direction = 3;
        }
    }
    
// 0 = North, 1 = East, 2 = South, 3 = West
    @Override
    public void move(){
        if (direction==0){
            y+=currentSpeed;}

        else if (direction==1){
            x+=currentSpeed;
        }
        else if (direction==2){
            y-=currentSpeed;
        }
        else if (direction==3){
            x-=currentSpeed;
        }
    }
    }




