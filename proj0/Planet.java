public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double r;
        double dx;
        double dy;
        dx = p.xxPos - this.xxPos;
        dy = p.yyPos - this.yyPos;
        r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }
    
    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        double f = 6.67E-11 * this.mass * p.mass / (r * r);
        return f;
    }

    public double calcForceExertedByX(Planet p){
        double dx;
        dx = p.xxPos - this.xxPos;
        double Fx = this.calcForceExertedBy(p) * dx / this.calcDistance(p);
        return Fx;
    }

    public double calcForceExertedByY(Planet p){
        double dy;
        dy = p.yyPos - this.yyPos;
        double Fy = this.calcForceExertedBy(p) * dy / this.calcDistance(p);
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] p_array){
        double net_force_x = 0;
        for (int i = 0; i < p_array.length; i = i + 1){
            if (this.equals(p_array[i])){
                continue;
            }
            net_force_x = net_force_x + this.calcForceExertedByX(p_array[i]);
        }
        return net_force_x;
    }

    
    public double calcNetForceExertedByY(Planet[] p_array){
        double net_force_y = 0;
        for (int i = 0; i < p_array.length; i = i + 1){
            if (this.equals(p_array[i])){
                continue;
            }
            net_force_y = net_force_y + this.calcForceExertedByY(p_array[i]);
        }
        return net_force_y;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}