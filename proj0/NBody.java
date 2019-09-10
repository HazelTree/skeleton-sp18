class NBody {

	private static String starfield = "./images/starfield.jpg";

	public static double readRadius(String filename){

		In input = new In(filename);
		int numberOfPlanets = input.readInt();
		double radius = input.readDouble();
		return radius;

	}

	public static Planet[] readPlanets(String filename){

		In input = new In(filename);
		int numberOfPlanets = input.readInt();
		Planet[] planets = new Planet[numberOfPlanets];
		input.readDouble();
		for(int j=0;j<numberOfPlanets;j++){
			planets[j] = new Planet(input.readDouble(),input.readDouble(),input.readDouble(),input.readDouble(),input.readDouble(),input.readString());
		}
		

        return planets;

	}

	public static void main(String[] args){

		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename =args[2];
		double radius = readRadius(filename);
		Planet[] planets= readPlanets(filename);

		

		StdDraw.setScale(-radius,radius);

		double timer = 0.0;

		while(timer < T){
			double[] forceX = new double[planets.length];
			double[] forceY = new double[planets.length];

			for (int x=0;x<planets.length;x++){
				forceX[x] = planets[x].calcNetForceExertedByX(planets); 
				forceY[x] = planets[x].calcNetForceExertedByY(planets);
			}

			for(int x=0;x<planets.length;x++){
				planets[x].update(dt,forceX[x],forceY[x]);
			}

			StdDraw.picture(0, 0, starfield);

			for (Planet p: planets){
				p.draw();
			}


            	StdDraw.show();
            	StdDraw.pause(40);

            	StdDraw.clear();

            	timer = timer + dt;
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