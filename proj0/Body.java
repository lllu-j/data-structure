public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Body(double xP, double yP, double xV,
              double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b)
	{
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	public double calcDistance(Body particle)
	{
		double Dis_2 = (xxPos - particle.xxPos)* (xxPos - particle.xxPos) + (yyPos - particle.yyPos)*(yyPos - particle.yyPos);
		double Dis = Math.sqrt(Dis_2);
		return Dis;
	}
	public double calcForceExertedBy(Body particle)
	{
		double G = 6.67*Math.pow(10,-11);
		double Dis = this.calcDistance(particle);
		double Force = G*mass*particle.mass/Math.pow(Dis,2);
		return Force;
	}
	public double calcForceExertedByX(Body particle)
	{
		double dx = particle.xxPos-xxPos;
		double ForceX = this.calcForceExertedBy(particle)*dx/this.calcDistance(particle);
		return ForceX;
	}
	public double calcForceExertedByY(Body particle)
	{
		double dy = particle.yyPos-yyPos;
		double ForceY = this.calcForceExertedBy(particle)*dy/this.calcDistance(particle);
		return ForceY;
	}
	public double calcNetForceExertedByX(Body[] particles)
	{
		double NetForceX = 0;
		for(int i=0;i<particles.length;i++)
		{
			if (this.equals(particles[i])) {
				NetForceX = NetForceX;
			}
			else
			{
				NetForceX = NetForceX+this.calcForceExertedByX(particles[i]);
			}			
		}
		return NetForceX;
	}
	public double calcNetForceExertedByY(Body[] particles)
	{
		double NetForceY = 0;
		for(int i=0;i<particles.length;i++)
		{
			if (this.equals(particles[i])) {
				NetForceY = NetForceY;
			}
			else
			{
				NetForceY = NetForceY+this.calcForceExertedByY(particles[i]);
			}
		}
		return NetForceY;
	}
	public void update(double dt,double ForceX, double ForceY)
	{
		double ax = ForceX/mass;
		double ay = ForceY/mass;
		xxVel = xxVel+dt*ax;
		yyVel = yyVel+dt*ay;
		xxPos = xxPos+dt*xxVel;
		yyPos = yyPos+dt*yyVel;
	}
	public void draw()
	{
		String imgToDraw = "images/"+imgFileName;
		StdDraw.picture(xxPos,yyPos,imgToDraw);
	}
}