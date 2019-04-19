public class Body {
    /** Represent the Body in space */

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;

    /** Initialize the Body class */
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;

    }

    /** Initialize the Body class with an existing Body */
    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    /** Return the distance between two Bodys */
    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow((this.xxPos - b.xxPos), 2) + Math.pow((this.yyPos - b.yyPos), 2));
    }

    /** Return the force between two Bodys */
    public double calcForceExertedBy(Body b) {
        return G * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
    }

    /** Return the force between two Bodys on the x axis */
    public double calcForceExertedByX(Body b) {
        return (b.xxPos - this.xxPos) / this.calcDistance(b) * this.calcForceExertedBy(b);
    }

    /** Return the force between two Bodys on the y axis */
    public double calcForceExertedByY(Body b) {
        return (b.yyPos - this.yyPos) / this.calcDistance(b) * this.calcForceExertedBy(b);
    }

    /** Return the net force on the x axis */
    public double calcNetForceExertedByX(Body[] bodys) {
        double sum = 0;
        for (Body b : bodys) {
            if (b.equals(this)) {
                continue;
            }
            sum = sum + (b.xxPos - this.xxPos) / this.calcDistance(b) * this.calcForceExertedBy(b);
        }
        return sum;
    }

    /** Return the net force on the y axis */
    public double calcNetForceExertedByY(Body[] bodys) {
        double sum = 0;
        for (Body b : bodys) {
            if (b.equals(this)) {
                continue;
            }
            sum = sum + (b.yyPos - this.yyPos) / this.calcDistance(b) * this.calcForceExertedBy(b);
        }
        return sum;
    }

    /** Update the position and velocity of the body */
    public void update(double time, double ForceX, double ForceY) {
        double Ax = ForceX / this.mass;
        double Ay = ForceY / this.mass;
        this.xxVel += (Ax * time);
        this.yyVel += (Ay * time);
        this.xxPos += (this.xxVel * time);
        this.yyPos += (this.yyVel * time);
    }

    public void draw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        StdDraw.show();
        StdDraw.pause(1);
    }
}