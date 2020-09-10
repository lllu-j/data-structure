public class NBody{
	public static double readRadius(String filename)
	{
		int numRadius = 2;
		In universe = new In(filename);
		double Radius = 0.0;
		for(int i=0;i<numRadius; i++)
		{
			Radius = universe.readDouble();
		}
		return Radius;
	}
	public static Body[] readBodies(String filename)
	{
		In universe = new In(filename);
		int NumofPlanet = universe.readInt();
		double Radius = universe.readDouble();
		Body[] Bodies = new Body[NumofPlanet];
		for(int i=0; i<NumofPlanet; i++)
		{
			Bodies[i] = new Body(universe.readDouble(), universe.readDouble(), 
				universe.readDouble(),universe.readDouble(),universe.readDouble(),
				universe.readString());
		}
		return Bodies;
	}
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double Radius = readRadius(filename);
		Body[] Bodies = readBodies(filename);
		int NumofPlanet = Bodies.length;

		StdDraw.enableDoubleBuffering();

		//animate
		for (double time=0;time<T;time=time+dt) {
			double[] xForces = new double[NumofPlanet];
			double[] yForces = new double[NumofPlanet];
			for(int i=0;i<NumofPlanet;i++){
				xForces[i]=Bodies[i].calcNetForceExertedByX(Bodies);
				yForces[i]=Bodies[i].calcNetForceExertedByY(Bodies);
			}
			for(int i=0;i<NumofPlanet;i++){
				Bodies[i].update(dt,xForces[i],yForces[i]);
			}
			//draw background
			String imageToDraw = "images/starfield.jpg";
			StdDraw.setScale(-Radius, Radius);
			StdDraw.picture(0,0,imageToDraw);
			//draw planet
			for(int i=0;i<NumofPlanet;i++){
				Bodies[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", Bodies.length);
		StdOut.printf("%.2e\n", Radius);
		for (int i = 0; i < Bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
		                  Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);   
		}
	}
}