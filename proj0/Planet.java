public class Planet {


	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;


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


	public double calcDistance(Planet target){
		double distance = (target.xxPos - this.xxPos) * (target.xxPos - this.xxPos) + (target.yyPos - this.yyPos) * (target.yyPos - this.yyPos);
        return Math.sqrt(distance);
	}

	public double calcForceExertedBy(Planet target){
		double force=G * this.mass * target.mass / (this.calcDistance(target)*this.calcDistance(target));
		return force;
	}

	public double calcForceExertedByX(Planet target){
		double forceX=this.calcForceExertedBy(target)*(target.xxPos-this.xxPos)/this.calcDistance(target);
		return forceX;
	}

	public double calcForceExertedByY(Planet target){
        double forceY = calcForceExertedBy(target) * (target.yyPos - this.yyPos) / calcDistance(target);
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
    	double allforceX = 0.0;
    	for(Planet p: allPlanets){
    		if(p.equals(this)){
                continue;
            }
    		allforceX += this.calcForceExertedByX(p);
    	}
    	return allforceX;

    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
    	double allforceY = 0.0;
    	for(Planet p: allPlanets){
    		if(p.equals(this)){
                continue;
            }
    		allforceY += this.calcForceExertedByY(p);
    	}
    	return allforceY;
    }

    public void update(double dt, double forcex, double forcey) {
		double ax = forcex / this.mass;
		double ay = forcey / this.mass;
		this.xxVel += dt * ax;
		this.yyVel += dt * ay;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

    public void draw(){
    	StdDraw.picture(this.xxPos,this.yyPos,"images/" + this.imgFileName);
    }

    
}