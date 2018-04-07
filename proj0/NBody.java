public class NBody{
    /**read the number of the planets*/
    public static double readRadius(String s){
        In in = new In(s);
        double radius = in.readDouble();
        radius = in.readDouble();
        return radius;
    }
    /** return an array of planets*/
    public static Planet[] readPlanets(String s){
        int i = 0;
        In in = new In(s);
        Planet[] P = new Planet[5];
        in.readDouble();
        in.readDouble();
        for (i = 0; i < 5; i++)
            P[i] = new Planet(in.readDouble(), in.readDouble(),
            in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        return P;
    }
    /** drawing the initial Universe State*/
    public static void main(String[] args) {
        //collecting all needed input;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] P = readPlanets(filename);
        //drawing the background;
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
        //drawing all of the planets;
        for (Planet s : P) {
            s.draw();
        }
        //Creating an animation;
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < 1000000000){
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for (int i = 0; i < 5; i++){
                xForces[i] = P[i].calcNetForceExertedByX(P);
                yForces[i] = P[i].calcNetForceExertedByY(P);
            }
            for (int i = 0; i < 5; i++) {
                P[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
            //drawing all of the planets;
            for (Planet s : P) {
                s.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time = time + dt;
        }
        //print the Universe
        StdOut.printf("%d\n", P.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < P.length; i++) {
            StdOut.printf("11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            P[i].xxPos, P[i].yyPos, P[i].xxVel, P[i].yyVel, P[i].mass, P[i].imgFileName);
        }
    }
}
