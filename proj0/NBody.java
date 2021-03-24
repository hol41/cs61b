public class NBody{
    public static double readRadius(String filename){
        In in = new In(filename);
        int a = in.readInt();
        double uni_radius = in.readDouble();
        return uni_radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int n_planet = in.readInt();
        in.readDouble();
        Planet[] p_array = new Planet[n_planet];
        for (int i = 0; i < n_planet; i = i + 1){
            p_array[i] = new Planet(in.readDouble(), in.readDouble(),in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return p_array;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        
        /* Sets up the universe */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i = 0; i < planets.length; i = i + 1){
            planets[i].draw();
        }
        StdDraw.show();
        StdDraw.pause(10);
        StdDraw.enableDoubleBuffering();

        for (double uni_time = 0; uni_time < T; uni_time = uni_time + dt){
            double[] xForce_arr = new double[planets.length];
            double[] yForce_arr = new double[planets.length];
            for (int i = 0; i < planets.length; i = i + 1){
                xForce_arr[i] = planets[i].calcNetForceExertedByX(planets);
                yForce_arr[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i = i + 1){
                planets[i].update(dt, xForce_arr[i], yForce_arr[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < planets.length; i = i + 1){
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
    }
}
