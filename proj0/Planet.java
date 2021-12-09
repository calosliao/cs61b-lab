public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Planet() {
        xxPos = 0;
        yyPos = 0;
        xxVel = 0;
        yyVel = 0;
        mass = 0;
        imgFileName = null;
    }
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(this.xxPos-p.xxPos, 2)+Math.pow(this.yyPos-p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        return (G*this.mass*p.mass)/Math.pow(this.calcDistance(p),2);
    }

    public double calcForceExertedByX(Planet p) {
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for(Planet planet:allPlanets) {
            if(!this.equals(planet)) {
                netForceX += this.calcForceExertedByX(planet);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for(Planet planet:allPlanets) {
            if(!this.equals(planet)) {
                netForceY += this.calcForceExertedByY(planet);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        this.xxVel = this.xxVel + (fX / this.mass) * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyVel = this.yyVel + (fY / this.mass) * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }
}
