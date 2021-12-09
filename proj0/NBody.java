public class NBody {
    public static double readRadius(String path) {
        In in = new In(path);
        in.readInt();
        double res = in.readDouble();
        in.close();

        return res;
    }

    public static Planet[] readPlanets(String path) {
        Planet[] allPlanets = new Planet[5];
        In in = new In(path);
        in.readInt();
        in.readDouble();

        for(int i = 0; i < 5; i++) {
            allPlanets[i] = new Planet();
            allPlanets[i].xxPos = in.readDouble();
            allPlanets[i].yyPos = in.readDouble();
            allPlanets[i].xxVel = in.readDouble();
            allPlanets[i].yyVel = in.readDouble();
            allPlanets[i].mass = in.readDouble();
            allPlanets[i].imgFileName = in.readString();
        }
        in.close();
        return allPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-2 * radius, 2 * radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.enableDoubleBuffering();
        StdDraw.show();

        int count = 0;
        while(count < T) {
            StdDraw.clear();

            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for(int i = 0; i < 5; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i = 0; i < 5; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
                String starName = "images/" + planets[i].imgFileName;
                StdDraw.picture(planets[i].xxPos, planets[i].yyPos, starName);
            }
            StdDraw.show();
            StdDraw.pause(10);
            count += dt;
        }
    }
}
